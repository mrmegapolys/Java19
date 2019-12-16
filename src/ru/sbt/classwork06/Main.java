package ru.sbt.classwork06;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        IPerson personTimer = TimerProxy.timer(new Person("Alex"));
        personTimer.sleep();
    }
}
