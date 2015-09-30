package com.zhenhui.demo.Study.netty.cli.xt01.handler;

import com.zhenhui.demo.Study.netty.cli.xt01.filter.XT01SigninFilter;
import com.zhenhui.demo.Study.netty.core.handler.AbstractHandler;
import com.zhenhui.demo.Study.netty.core.message.Message;
import com.zhenhui.demo.Study.netty.core.message.filter.DefaultFilterChain;
import com.zhenhui.demo.Study.netty.core.session.Session;

@SuppressWarnings("unused")
public class XT01MessageHandler extends AbstractHandler {

    private DefaultFilterChain filterChain;


    public XT01MessageHandler() {
        filterChain = new DefaultFilterChain(new XT01SigninFilter());
    }

    @Override
    public void sessionOpened(final Session session) {
        System.out.println("---> " + session);
    }

    @Override
    public void messageReceived(final Session session, final Message message) {
        filterChain.reset();
        filterChain.filter(filterChain, session, message);
    }

    @Override
    public void sessionClosed(final Session session) {
        System.out.println("<--- " + session);
    }

    @Override
    public void sessionException(final Session session, final Throwable cause) {
        System.out.println(cause.getMessage());

        session.close();
    }


}
