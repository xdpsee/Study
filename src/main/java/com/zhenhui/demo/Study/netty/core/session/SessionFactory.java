package com.zhenhui.demo.Study.netty.core.session;

import io.netty.channel.Channel;

public class SessionFactory {

    public static Session create(Channel channel) {
        return new DefaultSessionImpl(channel);
    }

}
