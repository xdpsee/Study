package com.zhenhui.demo.Study.netty.core.message;


import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.OutputStream;

public abstract class Response implements Message {

    protected final ByteOutputStream stream = new ByteOutputStream();

    public final OutputStream getOutputStream() {
        return stream;
    }

    @Override
    public byte[] getBytes() {
        return stream.getBytes();
    }
}
