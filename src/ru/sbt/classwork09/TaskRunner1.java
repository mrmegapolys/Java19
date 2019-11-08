package ru.sbt.classwork09;

public class TaskRunner1<T> implements Runnable{
    private final Task1<T> task;

    public TaskRunner1(Task1<T> task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println(task.get());
    }
}
