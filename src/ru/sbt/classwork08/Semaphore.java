package ru.sbt.classwork08;

public class Semaphore {
    private final int maxThreadCount;
    private int currentThreadCount;

    public Semaphore(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
        this.currentThreadCount = 0;
    }

    public synchronized void lock() {
        while (currentThreadCount == maxThreadCount) {
            await();
        }
        currentThreadCount++;
    }

    public synchronized void unlock() {
        currentThreadCount--;
        this.notify();
    }

    private void await() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
