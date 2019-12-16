package ru.sbt.shop.orderprocessors;

import ru.sbt.shop.Order;
import ru.sbt.shop.Result;

public interface OrderProcessor {
    String healthCheck();
    Result process(Order order);
}
