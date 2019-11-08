package ru.sbt.classwork08;

public class SemaphoreRunner implements Runnable{
    private final Semaphore semaphore;

    public SemaphoreRunner(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        semaphore.lock();
        try {
            doRun();
        } finally {
            semaphore.unlock();
        }
    }

    private void doRun() {
        System.out.println("Thread " + Thread.currentThread().getId() + " started");
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getId() + " finished");
    }
}
