package ru.sbt.shop.orderprocessors.processors;

import ru.sbt.shop.Order;
import ru.sbt.shop.Result;
import ru.sbt.shop.orderprocessors.OrderProcessor;

public class OneMoreProcessor implements OrderProcessor {
    @Override
    public String healthCheck() {
        return "OK";
    }

    @Override
    public Result process(Order order) {
        return new Result(order.getPrice() < 200);
    }
}
