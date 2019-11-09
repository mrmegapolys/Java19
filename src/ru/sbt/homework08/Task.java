package ru.sbt.homework08;

import java.util.concurrent.Callable;

public class Task<T> implements ITask<T> {
    private final Callable<? extends T> callable;
    private boolean notDone;
    private boolean noException;
    private T result;
    private Exception caughtException;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
        notDone = true;
    }

    public T get() {
        if (notDone) {
            synchronized (this) {
                if (notDone) calculateResult();
            }
        }

        if (noException) return result;
        throw new RuntimeException(caughtException);
    }

    private void calculateResult() {
        try {
            result = callable.call();
            noException = true;
        } catch (Exception e) {
            caughtException = e;
            noException = false;
        }
        notDone = false;
    }


}
