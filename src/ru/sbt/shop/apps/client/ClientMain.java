package ru.sbt.shop.apps.client;

import ru.sbt.shop.Order;
import ru.sbt.shop.orderprocessors.OrderProcessor;

public class ClientMain {
    public static void main(String[] args) {
        ClientProxyFactory factory = new ClientProxyFactory("0.0.0.0");
        OrderProcessor firstProcessor = factory.getProxy(OrderProcessor.class, 8123);
        OrderProcessor secondProcessor = factory.getProxy(OrderProcessor.class, 8124);
        Order order = new Order("name", 100);

        System.out.println(firstProcessor.healthCheck());
        System.out.println(firstProcessor.process(order));

        System.out.println(secondProcessor.healthCheck());
        System.out.println(secondProcessor.process(order));
    }
}
