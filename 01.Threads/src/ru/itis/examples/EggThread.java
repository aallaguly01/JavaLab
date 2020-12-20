package ru.itis.examples;


public class EggThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Egg");
        }
    }
}
