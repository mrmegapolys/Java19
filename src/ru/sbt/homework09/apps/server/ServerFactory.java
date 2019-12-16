package ru.sbt.homework09.apps.server;


public class ServerFactory {
    public static void expose(Object delegate, int port) {
        new Thread(new Server(delegate, port)::start).start();
    }
}
