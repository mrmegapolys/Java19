package ru.sbt.classwork08;

public class BarrierRunner implements Runnable{
    private final Barrier barrier;

    public BarrierRunner(Barrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " started");
        barrier.await();
        System.out.println("Thread " + Thread.currentThread().getId() + " passed through");
    }
}
