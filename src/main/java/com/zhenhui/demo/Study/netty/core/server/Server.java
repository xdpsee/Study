package com.zhenhui.demo.Study.netty.core.server;

public interface Server<T extends Builder> {

    public void run() throws Exception;

    public void shutdown() throws Exception;

}
