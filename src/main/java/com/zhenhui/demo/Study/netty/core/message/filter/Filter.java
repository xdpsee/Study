package com.zhenhui.demo.Study.netty.core.message.filter;


import com.zhenhui.demo.Study.netty.core.message.Message;
import com.zhenhui.demo.Study.netty.core.session.Session;

public interface Filter<T extends Message> {

    public void filter(FilterChain chain, Session session, T message);

}
