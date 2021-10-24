package nettyserver;

import inbound.HttpInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.List;
import java.util.Map;


/**
 * @author weimingjie
 * @version 1.0
 * @date 2021/9/27 9:11 上午
 */
public class NettyHttpServer {

    private int port;

    private List<String> backEndServers;


    public NettyHttpServer(int port, List<String> backEndServers) {
        this.port = port;
        this.backEndServers = backEndServers;
    }

    //启动netty主方法
    public void runNetty() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(10);
        EventLoopGroup workerGroup = new NioEventLoopGroup(20);

        try {
            //netty启动主类
            ServerBootstrap b = new ServerBootstrap();
            //配置netty
            b.option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    //首先调用日志服务
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //之后调用inbound服务
                    .childHandler(new HttpInboundHandler(backEndServers));
            Channel ch = b.bind(port).sync().channel();
            System.out.println("starting netty on port:"+port);
            //关闭回调函数
            ch.closeFuture().sync().channel();
        }finally {
            //杀死NioEventLoop线程，释放资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }
    }
}
