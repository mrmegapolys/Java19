package ru.sbt.homework09.apps.client;

import java.lang.reflect.Proxy;

public class ClientProxyFactory {
    private final String host;

    public ClientProxyFactory(String host) {
        this.host = host;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz, int port) {
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{clazz},
                new ClientProxy(host, port)
        );
    }
}
