package com.zhenhui.demo.Study.netty.core.session;

import com.zhenhui.demo.Study.netty.core.message.Message;

import java.net.SocketAddress;
import java.util.Map;

@SuppressWarnings("unused")
public interface Session {

    public Long getId();

    public Map<String, Object> attributes();

    public SocketAddress getPeerAddress();

    public void close() throws Exception;

    public void write(Message message, final Message.Callback callback) throws Exception;
}


