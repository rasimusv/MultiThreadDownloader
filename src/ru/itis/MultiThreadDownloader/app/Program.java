package ru.itis.MultiThreadDownloader.app;

import com.beust.jcommander.*;
import ru.itis.MultiThreadDownloader.utils.Downloader;
import java.util.concurrent.*;

public class Program {

    public static void main(String[] args) {
        args = new String[] {
                "--mode=multi-thread",
                "--files=https://sun9-43.userapi.com/c857028/v857028201/1bae56/bhy8k2FfTYM.jpg;https://sun9-71.userapi.com/q25arh2ap-SJl8KTM9cKL2UabqasVZY_5Ntteg/Y226m1ntc3I.jpg;https://sun9-4.userapi.com/c855432/v855432982/22ea2a/9kS5EibN56A.jpg;https://sun9-37.userapi.com/c857636/v857636576/1f789b/rwn2P-pVdV0.jpg",
                "--folder=/Users/rasimusv/Desktop"
        };

        Args argv = new Args();
        JCommander.newBuilder().addObject(argv).build().parse(args);

        Downloader downloader = new Downloader(argv.folder);

        if (argv.mode.equals("one-thread")) {
            for (String url : argv.files) {
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

            for (String url : argv.files) {
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
