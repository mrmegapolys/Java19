package ru.sbt.homework09.apps.server;

import ru.sbt.homework09.orderprocessors.processors.OneMoreProcessor;
import ru.sbt.homework09.orderprocessors.processors.Processor;

public class ServerMain {
    public static void main(String[] args) {
        ServerFactory.expose(new Processor(), 8123);
        ServerFactory.expose(new OneMoreProcessor(), 8124);
    }
}
