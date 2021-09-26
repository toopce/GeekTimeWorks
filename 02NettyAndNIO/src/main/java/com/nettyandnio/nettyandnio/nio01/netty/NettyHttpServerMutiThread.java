package com.nettyandnio.nettyandnio.nio01.netty;

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

public class NettyHttpServerMutiThread {
    public static void main(String[] args) {
        //设置服务端都口号
        int port = 8810;

        //设置接待EventLoopGroup
//        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        //设置用于触发handler的EventLoopGroup，其中里面的参数是线程数，代表着有多少个NioEventLoop被创建去轮询,若不指定参数则认为是多线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //netty的启动入口
            ServerBootstrap b = new ServerBootstrap();
            //设置参数
            b.option(ChannelOption.SO_BACKLOG,128)
                    //设置tcp不会进行等待直接发送
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childOption(ChannelOption.SO_REUSEADDR,true)
                    .childOption(ChannelOption.SO_RCVBUF,32*1024)
                    .childOption(ChannelOption.SO_SNDBUF,32*1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT,true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            //这是一个主从结构的Reactor多线程模式，上面定义的group参数就是对应了多少线程会被创建
            b.group(workerGroup).channel(NioServerSocketChannel.class)
                    //handler
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInitailizer());
            //绑定端口
            Channel ch = b.bind(port).sync().channel();
            System.out.println("开启netty服务器位于端口"+port);
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
//            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
