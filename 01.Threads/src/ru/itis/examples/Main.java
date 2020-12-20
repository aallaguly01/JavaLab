package ru.itis.examples;

public class Main {

    public static void main(String[] args) throws Exception {

        HenThread henThread = new HenThread();
        henThread.start();

        henThread.join();

	    EggThread eggThread = new EggThread();
	    eggThread.start();

	    eggThread.join();

	    Thread.sleep(3000);

	    TirexTask tirexTask = new TirexTask();
	    Thread tirexThread = new Thread(tirexTask);
	    tirexThread.start();
	    for (int i = 0; i < 100; i++) {
            System.out.println("Human");
        }

	    ThreadService threadService = new ThreadService();

	    threadService.submit(() -> {
	        for (int i = 0; i < 100; i++) {
                System.out.println("From service");
            }
        });
    }
}
