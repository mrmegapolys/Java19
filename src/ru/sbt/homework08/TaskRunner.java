package ru.sbt.homework08;

public class TaskRunner<T> implements Runnable{
    private final ITask<T> task;

    public TaskRunner(ITask<T> task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println(task.get());
    }
}
