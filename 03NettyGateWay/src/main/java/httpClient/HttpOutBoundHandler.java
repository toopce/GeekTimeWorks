package httpClient;

import filter.HeaderHttpResponseFilter;
import filter.HttpRequestFilter;
import filter.HttpResponseFilter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import router.HttpEndPointRouter;
import router.RandomHttpEndPointRouter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 *
 *@version 1.0
 *@author weimingjie
 *@date 2021/9/27 10:00 上午
 */
public class HttpOutBoundHandler {
    private CloseableHttpAsyncClient httpclient;
    private ExecutorService proxyServer;
    private List<String> backendUrls;

    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    HttpEndPointRouter router = new RandomHttpEndPointRouter();

    public HttpOutBoundHandler(List<String> backendUrls) {
        //首先对所有的url进行格式转换和
        this.backendUrls = backendUrls.stream().map(this::formatUrl).collect(Collectors.toList());
        //获取cpu的核心进程数
        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyServer = new ThreadPoolExecutor(cores,cores,keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),new NamedThreadFactory("proxyService"),handler);

        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();
        httpclient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy((httpResponse, httpContext) -> 6000)
                .build();
        httpclient.start();
    }

    public void handler(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx,HttpRequestFilter filter){
        //模拟负载均衡
        String backEndUrl = router.route(this.backendUrls);
        filter.filter(fullHttpRequest, ctx);
        //向服务端发送请求
        proxyServer.submit(() -> fetchGet(fullHttpRequest , ctx ,backEndUrl));
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx,final String url){
        //调用接口的url
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE,HTTP.CONN_KEEP_ALIVE);
        //获取在inbound的filter设置的头信息
//        httpGet.setHeader("mao",inbound.headers().get("mao"));
//        httpGet.setHeader("hello",inbound.headers().get("hello"));
        httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                try {
                    handleResponse(inbound, ctx,httpResponse);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {
                httpGet.abort();
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });

    }

    private void handleResponse(final FullHttpRequest fullHttpRequest,final ChannelHandlerContext ctx,final HttpResponse endPointResponse) throws IOException {
        FullHttpResponse response = null;

        try {
            //获取从服务端拿到的回应
            byte[] body = EntityUtils.toByteArray(endPointResponse.getEntity());
            //设置请求头
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type","application/json");
            //获取服务端的response中的请求头重的Content-Length
            response.headers().setInt("Content-length",Integer.parseInt(endPointResponse.getFirstHeader("Content-Length").getValue()));

            //模拟filter对相应再做一层处理
            filter.filter(response);
        }catch (Exception e){
            //做异常处理
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,HttpResponseStatus.NO_CONTENT);

        }finally {
            if (fullHttpRequest != null){
                if (!HttpUtil.isKeepAlive(fullHttpRequest)){
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                }else {
                    ctx.write(response);
                }
                ctx.flush();
            }
        }


    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }

    //url链接格式校验和转换
    private String formatUrl(String backEnd){
        return backEnd.endsWith("/")?backEnd.substring(0,backEnd.length() -1):backEnd;
    }
}
