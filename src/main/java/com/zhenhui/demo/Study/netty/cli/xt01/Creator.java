package com.zhenhui.demo.Study.netty.cli.xt01;

import com.zhenhui.demo.Study.netty.cli.xt01.handler.XT01MessageHandler;
import com.zhenhui.demo.Study.netty.cli.xt01.msg.XT01MessageDecoder;
import com.zhenhui.demo.Study.netty.core.codec.AbstractDecoder;
import com.zhenhui.demo.Study.netty.core.handler.AbstractHandler;
import com.zhenhui.demo.Study.netty.core.server.Builder;
import io.netty.handler.codec.MessageToByteEncoder;

public class Creator implements Builder {

    @Override
    public AbstractDecoder createDecoder() {
        return new XT01MessageDecoder();
    }

    @Override
    public AbstractHandler createHandler() {
        return new XT01MessageHandler();
    }

    @Override
    public MessageToByteEncoder createEncoder() {
        return null;
    }
}
