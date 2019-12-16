package ru.sbt.classwork06;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TimerProxy implements InvocationHandler {
    private final Object delegate;

    @SuppressWarnings("unchecked")
    public static <T> T timer(T delegate) {
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new TimerProxy(delegate)
        );
    }

    private TimerProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(delegate, args);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }
}
