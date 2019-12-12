package ru.sbt.shop;

import java.io.Serializable;

public class Result implements Serializable {
    private final boolean status;

    public Result(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                '}';
    }
}
