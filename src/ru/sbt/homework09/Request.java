package ru.sbt.homework09;

import java.io.Serializable;
import java.util.Arrays;

public class Request implements Serializable {
    private final String methodName;
    private final Object[] args;

    public Request(String methodName, Object[] args) {
        this.methodName = methodName;
        this.args = args;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "Request{" +
                "methodName='" + methodName + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
