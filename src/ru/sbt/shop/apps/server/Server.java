package ru.sbt.shop.apps.server;

import ru.sbt.shop.Request;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import static ru.sbt.shop.apps.Utils.readFromSocket;
import static ru.sbt.shop.apps.Utils.writeToSocket;

public class Server {
    private final Object delegate;
    private final int port;

    public Server(Object delegate, int port) {
        this.delegate = delegate;
        this.port = port;
    }

    public void start() {
        try {
            startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(() -> {
                try {
                    process(clientSocket);
                } catch (IOException | ClassNotFoundException e) {
                    closeSocket(clientSocket);
                }
            }).start();
        }
    }

    private void closeSocket(Socket clientSocket) {
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(Socket clientSocket) throws IOException, ClassNotFoundException {
        Request request = (Request) readFromSocket(clientSocket);
        Object result = getResult(request);
        writeToSocket(clientSocket, result);
        clientSocket.close();
    }

    private Object getResult(Request request) {
        try {
            if (request.getArgs() == null) {
                Method method = delegate.getClass().getMethod(request.getMethodName());
                return method.invoke(delegate);
            } else {
                Class<?>[] classes = Arrays.stream(request.getArgs()).map(Object::getClass).toArray(Class[]::new);
                Method method = delegate.getClass().getMethod(request.getMethodName(), classes);
                return method.invoke(delegate, request.getArgs());
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return "Error while processing request.";
        }
    }
}
