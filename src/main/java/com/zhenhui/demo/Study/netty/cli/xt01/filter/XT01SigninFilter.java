package com.zhenhui.demo.Study.netty.cli.xt01.filter;

import com.zhenhui.demo.Study.netty.cli.xt01.msg.XT01SigninMessage;
import com.zhenhui.demo.Study.netty.core.message.filter.AbstractFilter;
import com.zhenhui.demo.Study.netty.core.message.filter.FilterChain;
import com.zhenhui.demo.Study.netty.core.session.Session;

public class XT01SigninFilter extends AbstractFilter<XT01SigninMessage> {

    public XT01SigninFilter() {
        super(true);
    }

    @Override
    public void doFilter(FilterChain chain, Session session, XT01SigninMessage message) {

        System.out.println(message);


    }
}
