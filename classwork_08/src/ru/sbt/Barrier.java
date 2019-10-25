package ru.sbt;

public class Barrier {
    private final int threadCounter;
    private int currentThreadCounter;

    public Barrier(int threadCounter) {
        this.threadCounter = threadCounter;
        this.currentThreadCounter = 0;
    }

    public synchronized void await() {
        currentThreadCounter++;
        while (currentThreadCounter < threadCounter) {
            doAwait();
        }
        this.notifyAll();
    }

    private void doAwait() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
