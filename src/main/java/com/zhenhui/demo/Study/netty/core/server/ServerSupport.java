package com.zhenhui.demo.Study.netty.core.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("unused")
public abstract class ServerSupport<T extends Builder> implements Server<T>{
    private Class<T> clazz;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private final short port;
    private AtomicBoolean running = new AtomicBoolean(false);

    public ServerSupport(short port) {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        clazz = (Class) params[0];

        this.port = port;
    }

    public synchronized void run() throws Exception {
        if (running.get()) {
            throw new Exception("Server is already running!");
        }

        final int processors = Runtime.getRuntime().availableProcessors();
        bossGroup = new NioEventLoopGroup(processors);
        workerGroup = new NioEventLoopGroup(processors);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new Initializer(clazz))
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            b.bind(port).sync(); // (7)

            running.set(true);

        } catch (Exception e){
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public synchronized void shutdown() {
        if (running.get()) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

            running.set(false);
        }
    }

}
