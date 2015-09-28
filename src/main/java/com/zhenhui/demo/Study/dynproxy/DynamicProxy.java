package com.zhenhui.demo.Study.dynproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings("unused")
public class DynamicProxy {

    static class Client {
        public static void main(String[] args) {

            Service business = (Service)new ServiceProxy().bind(new ServiceImpl());
            System.out.println(business.service());

        }
    }
}

interface Service {
    public String service();
}

class ServiceImpl implements Service {
    @Override
    public String service() {
        return "hello, dynamic proxy!";
    }
}

final class ServiceProxy implements InvocationHandler {

    private Object target;

    public Object bind(Object obj) {
        this.target = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // TODO: do anything you want

        System.out.println(System.currentTimeMillis());
        // logging ......

        Object result = method.invoke(target, args);

        System.out.println(System.currentTimeMillis());
        // logging ......

        return result;
    }
}


