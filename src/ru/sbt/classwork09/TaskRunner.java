package ru.sbt.classwork09;

public class TaskRunner<T> implements Runnable{
    private final Task<T> task;

    public TaskRunner(Task<T> task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println(task.get());
    }
}
