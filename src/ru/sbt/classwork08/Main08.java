package ru.sbt.classwork08;

import java.util.ArrayList;
import java.util.List;

public class Main08 {
    public static void main(String[] args) {
        //checkSemaphore();
        checkBarrier();
    }

    private static void checkBarrier() {
        Barrier barrier = new Barrier(5);
        BarrierRunner runner = new BarrierRunner(barrier);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            threads.add(new Thread(runner));
        }
        for (Thread thread : threads) {
            thread.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {

            }
        }
    }

    private static void checkSemaphore() {
        Semaphore semaphore = new Semaphore(3);
        SemaphoreRunner runner = new SemaphoreRunner(semaphore);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            threads.add(new Thread(runner));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }


}