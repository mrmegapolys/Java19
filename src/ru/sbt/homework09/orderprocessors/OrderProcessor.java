package ru.sbt.homework09.orderprocessors;

import ru.sbt.homework09.Order;
import ru.sbt.homework09.Result;

public interface OrderProcessor {
    String healthCheck();
    Result process(Order order);
}
