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
    public void channelRead(ChannelHandlerContext ctx, final Object msg) throws Exception {
        if (msg instanceof Message) {
            final Session session = ctx.attr(SESSION_KEY).get();
            if (session != null) {
                if (ctx.executor().inEventLoop()) {
                    try {
                        messageReceived(session, (Message) msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ctx.executor().execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                messageReceived(session, (Message) msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } else {
                throw new Exception("Session shouldn't be null!");
            }
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
        final Session session = ctx.attr(SESSION_KEY).get();
        if (session != null) {
            sessionException(session, cause);
        } else {
            ctx.close();
        }
    }

    public abstract void sessionOpened(final Session session);

    public abstract void messageReceived(final Session session, final Message message);

    public abstract void sessionClosed(final Session session);

    public abstract void sessionException(final Session session, final Throwable cause);


}


