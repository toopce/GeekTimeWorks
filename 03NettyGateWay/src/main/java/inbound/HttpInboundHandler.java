package inbound;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import filter.HeaderHttpRequestFilter;
import filter.HttpRequestFilter;
import httpClient.HttpOutBoundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

/**
 * @author weimingjie
 * @version 1.0
 * @date 2021/9/27 9:22 上午
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final List<String> backEndServers;
    private HttpOutBoundHandler outBoundHandler;
    private HttpRequestFilter filter = new HeaderHttpRequestFilter();

    public HttpInboundHandler(List<String> backEndServers) {
        this.backEndServers = backEndServers;
        this.outBoundHandler =  new HttpOutBoundHandler(this.backEndServers);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) o;
            //inboundHandler调用outboundhandler进行服务端的调用
            outBoundHandler.handler(fullHttpRequest , channelHandlerContext ,filter);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(o);
        }

    }
}
