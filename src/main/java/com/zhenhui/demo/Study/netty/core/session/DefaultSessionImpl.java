package com.zhenhui.demo.Study.netty.core.session;


import com.zhenhui.demo.Study.netty.core.message.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.lang.ref.WeakReference;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultSessionImpl implements Session {

    private ConcurrentHashMap<String, Object> attributes = new ConcurrentHashMap<String, Object>(2);
    private WeakReference<Channel> channelRef;
    private final SocketAddress socketAddress;
    private final long id;

    private static final AtomicLong ID = new AtomicLong(1);

    protected DefaultSessionImpl(Channel channel){
        channelRef = new WeakReference<Channel>(channel);
        socketAddress = channel.remoteAddress();
        id = ID.incrementAndGet();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Map<String, Object> attributes() {
        return attributes;
    }

    @Override
    public SocketAddress getPeerAddress() {
        return socketAddress;
    }

    @Override
    public void write(Message message, final Message.Callback callback) {
        Channel channel = channelRef.get();
        if (channel != null) {
            channel.writeAndFlush(message.getBytes()).addListener(new ChannelFutureListener(){
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (callback != null) {
                        callback.messageSended(future.isSuccess());
                    }
                }
            });
        } else {
            if (callback != null) {
                callback.messageSended(false);
            }
        }
    }

    @Override
    public void close() {
        final Channel channel = channelRef.get();
        if (channel != null) {
            channel.close();
            attributes.clear();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultSessionImpl)) return false;

        DefaultSessionImpl that = (DefaultSessionImpl) o;

        if (id != that.id) return false;

        if (!socketAddress.equals(that.socketAddress)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = socketAddress.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DefaultSessionImpl{" +
                "id = " + id +
                ", peer = " + socketAddress +
                '}';
    }
}
