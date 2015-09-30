package com.zhenhui.demo.Study.netty.core.message;


import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.OutputStream;

public abstract class Response {

    private final ByteOutputStream stream = new ByteOutputStream();

    public final OutputStream getOutputStream() {
        return stream;
    }

    public byte[] toBytes() {
        return stream.getBytes();
    }
}
