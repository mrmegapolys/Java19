package ru.sbt.homework06;

import java.util.List;

public class Person {
    private final Hand[] hands;
    private final List<String> skills;
    private final String name;
    private final int salary;
    private Integer notInitialized;
    private final String nullValue = null;


    public Person(int salary, String name, List<String> skills) {
        this.skills = skills;
        this.name = name;
        this.salary = salary;
        this.hands = new Hand[2];
        this.hands[0] = new Hand(true);
        this.hands[1] = new Hand(true);
    }
}
