package ru.itis.MultiThreadDownloader.app;

import com.beust.jcommander.*;

@Parameters(separators = "=")
public class Args {

    @Parameter (
            names = {"--mode", "--m"},
            description = "Choice of thread mode. Possible values: one-thread, multi-thread. Default: one-thread")
    public String mode = "one-thread";

    @Parameter (
            names = {"--count", "--c"},
            description = "Choice of count of threads. Default: 3")
    public int count = 3;

    @Parameter (
            names = {"--files"},
            description = "Selection of URL addresses to download. To separate files use semicolon")
    public String files;

    @Parameter (
            names = {"--folder"},
            description = "Selection of path to download. Use absolute path")
    public String folder;
}
