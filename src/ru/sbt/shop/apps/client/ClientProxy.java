package ru.sbt.shop.apps.client;

import ru.sbt.shop.Request;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import static ru.sbt.shop.apps.Utils.readFromSocket;
import static ru.sbt.shop.apps.Utils.writeToSocket;

public class ClientProxy implements InvocationHandler {
    private final String host;
    private final int port;

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = new Socket(host, port);
        writeToSocket(socket, new Request(method.getName(), args));
        Object result = readFromSocket(socket);
        socket.close();
        return result;
    }
}
