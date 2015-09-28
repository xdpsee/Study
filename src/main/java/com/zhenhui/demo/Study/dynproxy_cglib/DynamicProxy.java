package com.zhenhui.demo.Study.dynproxy_cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class DynamicProxy {

    static class Client {
        public static void main(String[] args) {

            Business business = (Business) new Proxy().getProxy(Business.class);
            System.out.println(business.service());
        }
    }
}

class Business {
    public String service() {
        return "we didn't implement Service interface!";
    }
}

class Proxy implements MethodInterceptor {

    public Object getProxy(Class clazz) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // TODO: do anything you want

        System.out.println(System.currentTimeMillis());
        // logging ......

        Object result = proxy.invokeSuper(o, args);

        System.out.println(System.currentTimeMillis());
        // logging ......

        return result;
    }
}


