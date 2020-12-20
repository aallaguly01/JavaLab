package ru.itis.examples;


public class HenThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Hen");
        }
    }
}
