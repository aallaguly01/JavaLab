package ru.itis.examples;


public class ThreadService {
    public void submit(Runnable task) {
        Thread taskThread = new Thread(task);
        taskThread.start();
    }
}
