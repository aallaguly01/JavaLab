package ru.itis.pool;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

// wait, notify, synchronized
public class ThreadPool {
    // очередь задач
    private Deque<Runnable> tasks;

    // пул потоков
    private PoolWorker threads[];

    public ThreadPool(int threadsCount) {
        this.tasks = new ConcurrentLinkedDeque<>();
        this.threads = new PoolWorker[threadsCount];

        for (int i = 0; i < this.threads.length; i++) {
            this.threads[i] = new PoolWorker();
            this.threads[i].start();
        }
    }

    public void submit(Runnable task) {
        // TODO: реализовать
        synchronized (tasks){
            tasks.add(task);
            tasks.notify();
        }
    }

    // класс - рабочий поток
    private class PoolWorker extends Thread {
        @Override
        public void run() {
            // TODO: реализовать
            while(true) {
                synchronized (tasks){
                    while (tasks.isEmpty()){
                        try {
                            tasks.wait();
                        } catch (InterruptedException e){
                            throw new IllegalArgumentException(e);
                        }
                    }
                }
                tasks.poll().run();
            }
        }
    }
}
