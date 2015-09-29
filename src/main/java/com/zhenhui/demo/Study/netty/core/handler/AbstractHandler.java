package com.zhenhui.demo.Study.netty.core.handler;

import com.zhenhui.demo.Study.netty.core.session.DefaultSessionManager;
import com.zhenhui.demo.Study.netty.core.session.Session;
import com.zhenhui.demo.Study.netty.core.message.Message;
import com.zhenhui.demo.Study.netty.core.session.SessionFactory;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.util.AttributeKey;

@SuppressWarnings("unused")
public abstract class AbstractHandler extends ChannelHandlerAdapter implements ChannelInboundHandler {

    private static final AttributeKey<Session> SESSION_KEY = AttributeKey.valueOf("channel.session.key");
    @Override
    public final void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelRegistered();
    }

    @Override
    public final void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelUnregistered();
    }

    @Override
    public final void channelActive(ChannelHandlerContext ctx) throws Exception {
        final Session session = SessionFactory.create(ctx.channel());
        ctx.attr(SESSION_KEY).set(session);

        DefaultSessionManager.instance().add(session);

        sessionOpened(session);
    }

    @Override
    public final void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final Session session = ctx.attr(SESSION_KEY).get();
        if (session != null) {
            DefaultSessionManager.instance().remove(session);
            sessionClosed(session);
        } else {
            ctx.fireChannelInactive();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof Message) {
            messageReceived((Message)msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public final void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelReadComplete();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        ctx.fireUserEventTriggered(evt);
    }

    @Override
    public final void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelWritabilityChanged();
    }

    @Override
    public final void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        sessionException(cause);
    }

    public abstract void sessionOpened(final Session session);

    public abstract void sessionClosed(final Session session);

    public abstract void sessionException(final Throwable cause);

    public abstract void messageReceived(final Message message);
}


