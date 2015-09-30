package com.zhenhui.demo.Study.netty.core.message;

public interface Message {

    public byte[] getBytes();

    public interface Callback {
        public void messageSended(boolean success);
    }

}
