package inbound;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

/**
 * @author weimingjie
 * @version 1.0
 * @date 2021/9/27 9:19 上午
 */
public class HttpInboundInitializer extends ChannelInitializer {

    private List<String> backEndServers;

    public HttpInboundInitializer(List<String> backEndServers) {
        this.backEndServers = backEndServers;
    }

    /**
     * 配置工作流水线
     * @param channel
     * @throws Exception
     */
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline p = channel.pipeline();
        //首先解析http请求
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024*1024));

    }
}
