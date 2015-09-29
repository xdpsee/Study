package com.zhenhui.demo.Study.netty.core.session;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.net.SocketAddress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unused")
public class DefaultSessionManager implements SessionManager {

    // unique, ip:port, session
    private BiMap<String, Session> container = HashBiMap.create(256);
    private final Lock lock = new ReentrantLock();

    @Override
    public Session getSession(String unique) {
        lock.lock();

        try {
            return container.get(unique);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getUnique(Session session) {
        lock.lock();

        try {

            return container.inverse().get(session);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void add(String unique, Session session) throws Exception {
        lock.lock();

        try {

            container.put(unique, session);

        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(Session session) {
        lock.lock();

        try {

            container.inverse().remove(session);

        } finally {
            lock.unlock();
        }
    }
}
