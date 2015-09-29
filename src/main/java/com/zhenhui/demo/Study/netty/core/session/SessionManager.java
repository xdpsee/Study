package com.zhenhui.demo.Study.netty.core.session;

public interface SessionManager {

    public Session getSession(final String key);

    public String getUnique(final Session session);

    public void add(final String key, final Session session) throws Exception;

    public void remove(final Session session);

}
