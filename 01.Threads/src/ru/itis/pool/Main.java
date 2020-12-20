package ru.itis.pool;


public class Main {
    public static void main(String[] args) {
        /*
        Сделать проверки:

        1) Одна задача - один поток
        2) Две задачи - один поток (поочередно выполнить каждую)
        3) Три задачи - три потока (каждый поток выполняет свою задачу)
        4) Четыре задачи - три потока (три потока выполняют три задачи, четвертая задача выполняется первым свободным)
         */
        ThreadPool threadPool = new ThreadPool(1);

        threadPool.submit(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("1");
            }
        });

        threadPool.submit(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("2");
            }
        });

        threadPool.submit(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("3");
            }
        });

        threadPool.submit(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("4");
            }
        });
    }
}
