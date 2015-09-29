package com.zhenhui.demo.Study.netty.core.codec;

import com.zhenhui.demo.Study.netty.core.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public abstract class AbstractDecoder<T extends Message> extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        T msg = decode(in);
        if (msg != null) {
            out.add(msg);
        }

    }

    protected abstract T decode(ByteBuf buf);
}

