package ru.sbt.shop.apps.server;


public class ServerFactory {
    public static void expose(Object delegate, int port) {
        new Thread(new Server(delegate, port)::start).start();
    }
}
