package com.zhenhui.demo.Study.netty.cli.xt01;

import com.zhenhui.demo.Study.netty.core.codec.AbstractDecoder;
import com.zhenhui.demo.Study.netty.core.handler.AbstractHandler;
import com.zhenhui.demo.Study.netty.core.server.Builder;
import io.netty.handler.codec.MessageToByteEncoder;

public class Creator implements Builder {

    @Override
    public AbstractDecoder createDecoder() {
        return null;
    }

    @Override
    public AbstractHandler createHandler() {
        return null;
    }

    @Override
    public MessageToByteEncoder createEncoder() {
        return null;
    }
}
