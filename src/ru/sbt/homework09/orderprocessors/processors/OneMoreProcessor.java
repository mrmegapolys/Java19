package ru.sbt.homework09.orderprocessors.processors;

import ru.sbt.homework09.Order;
import ru.sbt.homework09.Result;
import ru.sbt.homework09.orderprocessors.OrderProcessor;

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
