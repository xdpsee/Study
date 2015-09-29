package com.zhenhui.demo.Study.netty.core.server;

import com.zhenhui.demo.Study.netty.core.codec.AbstractDecoder;
import com.zhenhui.demo.Study.netty.core.handler.AbstractHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;

@SuppressWarnings("unused")
public class Initializer extends ChannelInitializer<SocketChannel>{

    private Class<?> clazzFactory;

    public Initializer(Class<? extends Builder> clazzFactory) {
        this.clazzFactory = clazzFactory;
    }

    @Override
    protected final void initChannel(SocketChannel socketChannel) throws Exception {
        final Builder builder = (Builder)clazzFactory.newInstance();

        final AbstractDecoder decoder = builder.createDecoder();
        if (decoder != null) {
            socketChannel.pipeline().addLast("decoder", decoder);
        }

        final AbstractHandler handler = builder.createHandler();
        if (handler != null) {
            socketChannel.pipeline().addLast("handler", handler);
        }

        final MessageToByteEncoder encoder = builder.createEncoder();
        if (encoder != null) {
            socketChannel.pipeline().addLast("encoder", encoder);
        }
    }


}

