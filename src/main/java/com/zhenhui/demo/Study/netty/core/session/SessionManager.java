package com.zhenhui.demo.Study.netty.core.session;

public interface SessionManager {

    public void add(final Session session);

    public void bind(String unique, Session session);

    public Session getSession(final String unique);

    public String getUnique(final Session session);

    public void remove(final Session session);

}
