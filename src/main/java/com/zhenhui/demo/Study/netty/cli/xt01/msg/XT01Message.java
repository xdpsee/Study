package com.zhenhui.demo.Study.netty.cli.xt01.msg;

import com.zhenhui.demo.Study.netty.core.message.Message;

public abstract class XT01Message implements Message {


    @Override
    public byte[] getBytes() {
        return new byte[0];
    }
}

