package ru.itis.downloader.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Download implements Runnable{
    private String imageUrl;
    private String folder;
    private int count;
    private String imageName;

    public Download(int count, String imageUrl, String folder) {
        this.imageUrl = imageUrl;
        this.folder = folder;
        this.count = count;
    }

    public Download(String imageUrl, String folder) {
        this.imageUrl = imageUrl;
        this.folder = folder;
        this.count = 1;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            URL url = new URL(imageUrl);
            inputStream = url.openStream();
            outputStream = new FileOutputStream(folder + imageName + ".jpg");

            byte[] buffer = new byte[2048];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException :- " + e.getMessage());

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException :- " + e.getMessage());

        } catch (IOException e) {
            System.out.println("IOException :- " + e.getMessage());

        } finally {
            try {

                inputStream.close();
                outputStream.close();

            } catch (IOException e) {
                System.out.println("Finally IOException :- " + e.getMessage());
            }
        }
    }

    public void downloader(){
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++){
            imageName = String.valueOf(i);
            executorService.submit(this::run);
            System.out.println(i + " is downloading");
        }
    }
}
