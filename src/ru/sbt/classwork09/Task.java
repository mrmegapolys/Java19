package ru.sbt.classwork09;

import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<T> callable;

    private T result;
    private RuntimeException exception;

    private volatile boolean finished = false;
    private volatile boolean inProgress = false;

    public Task(Callable<T> callable) {
        this.callable = callable;
    }

    public T get() { //doesn't work

        if (!finished) {
            synchronized (this) {
                while (inProgress) await();
            }

            if (!finished) {
                inProgress = true;
                calculateResult();
                finished = true;
                inProgress = false;
            }

            synchronized (this) {
                this.notifyAll();
            }
        }


        if (exception != null) throw exception;
        return result;
    }

    private void await() {
        try {
            this.wait();
        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }
    }

    private void calculateResult() {
        try {
            result = callable.call();
        } catch (Exception e) {
            exception = new RuntimeException(e);
        }

    }
}