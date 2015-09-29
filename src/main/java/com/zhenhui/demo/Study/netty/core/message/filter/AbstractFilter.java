package com.zhenhui.demo.Study.netty.core.message.filter;


import com.zhenhui.demo.Study.netty.core.message.Message;
import com.zhenhui.demo.Study.netty.core.session.Session;
import io.netty.util.internal.TypeParameterMatcher;

public abstract class AbstractFilter<T extends Message> implements Filter<T> {

    private TypeParameterMatcher typeMatcher;
    private final boolean consumed;

    public AbstractFilter(boolean consumed) {
        typeMatcher = TypeParameterMatcher.find(this, AbstractFilter.class, "T");
        this.consumed = consumed;
    }

    @Override
    public final void filter(FilterChain chain, Session session, T message) {
        if (acceptInboundMessage(message)) {
            doFilter(chain, session, message);

            if (consumed) {
                return;
            }
        }

        chain.filter(chain, session, message);
    }

    private boolean acceptInboundMessage(T msg) {
        return typeMatcher.match(msg);
    }

    public abstract void doFilter(FilterChain chain, Session session, T message);
}
