package com.zhenhui.demo.Study.netty.core.message.filter;

import com.zhenhui.demo.Study.netty.core.message.Message;
import com.zhenhui.demo.Study.netty.core.session.Session;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unused")
public class DefaultFilterChain implements FilterChain {

    private final List<Filter> filters = Collections.synchronizedList(new LinkedList<Filter>());
    private final AtomicInteger current = new AtomicInteger(0);
    private final Lock lock = new ReentrantLock();

    public DefaultFilterChain(Filter... filters) {
        for (Filter f : filters) {
            this.filters.add(f);
        }
    }

    public void reset() {
        lock.lock();
        try {
            current.set(0);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void filter(FilterChain chain, Session session, Message message) {
        lock.lock();
        try {
            if (current.get() >= filters.size()) return;

            Filter filter = filters.get(current.getAndIncrement());

            try {
                filter.filter(chain, session, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
}
