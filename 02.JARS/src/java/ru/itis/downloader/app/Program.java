package ru.itis.downloader.app;

import ru.itis.downloader.utils.Download; 
import com.beust.jcommander.JCommander;

public class Program{
    public static void main(String[] argv){
        Args args = new Args();

        JCommander.newBuilder()
        	.addObject(args)
        	.build()
        	.parse(argv);

        if (args.mode.equals("one-thread")){
            Download download = new Download(args.files, args.folder);
            download.downloader();

        } else
            if (args.mode.equals("multi-thread")){
                Download download = new Download(args.count, args.files, args.folder);
                download.downloader();
            }
    }
}