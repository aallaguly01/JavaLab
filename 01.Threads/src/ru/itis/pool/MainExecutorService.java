package ru.itis.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainExecutorService {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + "A");
            }
        });

        executorService.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + "B");
            }
        });

        executorService.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + "C");
            }
        });

    }
}
