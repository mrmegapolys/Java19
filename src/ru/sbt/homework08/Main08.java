package ru.sbt.homework08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Main08 {
    public static void main(String[] args) throws InterruptedException {
        Callable<Integer> callable = () -> {
            Thread.sleep(3_000);
            return 12345;
        };
        Task<Integer> task = new Task<>(callable);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(new TaskRunner<>(task)));
        }

        for (int i = 0; i < 3; i++) {
            threads.get(i).start();
        }
        Thread.sleep(5_000);
        for (int i = 3; i < 5; i++) {
            threads.get(i).start();

        }
    }
}
