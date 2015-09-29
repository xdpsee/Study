package com.zhenhui.demo.Study.netty.core.session;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unused")
public final class DefaultSessionManager implements SessionManager {

    // unique, session
    private BiMap<String, Session> sessionUniqueMap = HashBiMap.create(256);
    private HashSet<Session> sessions = new HashSet<Session>(256);

    private final Lock lock = new ReentrantLock();

    private final static DefaultSessionManager defaultSessionManager = new DefaultSessionManager();

    public static DefaultSessionManager instance() {
        return defaultSessionManager;
    }

    public void add(final Session session) {
        if (!sessions.contains(session)) {
            sessions.add(session);
        }
    }

    @Override
    public void bind(final String unique, final Session session) {
        lock.lock();

        try {
            if (null == unique || "".equals(unique) || null == session) {
                return;
            }

            if (!sessions.contains(session)) {
                sessions.add(session);
            }

            sessionUniqueMap.put(unique, session);

        } finally {
            lock.unlock();
        }
    }

    @Override
    public Session getSession(String unique) {
        lock.lock();

        try {
            return sessionUniqueMap.get(unique);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getUnique(final Session session) {
        lock.lock();

        try {
            return sessionUniqueMap.inverse().get(session);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(final Session session) {
        lock.lock();

        try {
            sessions.remove(session);
            sessionUniqueMap.inverse().remove(session);

        } finally {
            lock.unlock();
        }
    }

    private DefaultSessionManager(){}
}
