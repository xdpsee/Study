package com.zhenhui.demo.Study.netty.core.server;


import com.zhenhui.demo.Study.netty.core.codec.AbstractDecoder;
import com.zhenhui.demo.Study.netty.core.handler.AbstractHandler;
import io.netty.handler.codec.MessageToByteEncoder;

@SuppressWarnings("unused")
public interface Builder {

    public AbstractDecoder createDecoder();

    public AbstractHandler createHandler();

    public MessageToByteEncoder createEncoder();

}
