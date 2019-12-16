package ru.sbt.shop.apps.server;

import ru.sbt.shop.orderprocessors.processors.OneMoreProcessor;
import ru.sbt.shop.orderprocessors.processors.Processor;

public class ServerMain {
    public static void main(String[] args) {
        ServerFactory.expose(new Processor(), 8123);
        ServerFactory.expose(new OneMoreProcessor(), 8124);
    }
}
