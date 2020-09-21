package ru.itis.MultiThreadDownloader.app;

import com.beust.jcommander.*;
import ru.itis.MultiThreadDownloader.utils.Downloader;
import java.util.concurrent.*;

public class Program {

    public static void main(String[] args) {
        Args argv = new Args();
        JCommander.newBuilder().addObject(argv).build().parse(args);

        Downloader downloader = new Downloader(argv.folder);

        String [] urls = argv.files.split(";");

        if (argv.mode.equals("one-thread")) {
            for (String url : urls) {
                downloader.download(url);
            }
        } else {
            BlockingQueue<Runnable> workQueue = new SynchronousQueue<>();
            ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                    argv.count,
                    argv.count,
                    Long.MAX_VALUE,
                    TimeUnit.NANOSECONDS,
                    workQueue);

            for (String url : urls) {
                while (argv.count - tpe.getActiveCount() == 0) {
                }
                tpe.submit(() -> {
                    downloader.download(url);
                    System.out.println(Thread.currentThread().getName());
                });
            }
        }
    }
}
