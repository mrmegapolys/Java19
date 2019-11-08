package ru.sbt.classwork06;

public class Person implements IPerson{
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public void sayName() {
        System.out.println(name);
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
