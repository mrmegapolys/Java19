package ru.sbt.classwork09;

import ru.sbt.homework08.ITask;

import static ru.sbt.homework08.Main.checkTask;
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Callable<Integer> callable = () -> {
            Thread.sleep(2_000);
            return 12345;
        };
        ITask<Integer> task = new Task<>(callable);

        checkTask(task);
    }
}
