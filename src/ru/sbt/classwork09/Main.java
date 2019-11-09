package ru.sbt.classwork09;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Callable<Integer> callable = () -> {
            Thread.sleep(2_000);
            return 12345;
        };
        Task<Integer> task = new Task<>(callable);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(new TaskRunner<>(task)));
        }
        System.out.println("start");
        for (int i = 0; i < 3; i++) {
            threads.get(i).start();
        }
        Thread.sleep(4_000);
        for (int i = 3; i < 5; i++) {
            threads.get(i).start();

        }
    }
}