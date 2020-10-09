package ru.itis.MultiThreadDownloader.utils;

import java.io.*;
import java.net.*;


public class Downloader {

    private String path;

    public Downloader(String path) {
        this.path = path;
    }

    public void download(String URL) {
        try {
            URL url = new URL(URL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();


            String dest  = url.getFile().split("/")[url.getFile().split("/").length -1];

            InputStream in = connection.getInputStream();
            OutputStream out = new FileOutputStream(path + "/" + dest);

            byte[] buf = new byte[1];
            int result;

            do {
                result = in.read(buf);
                out.write(buf);
            } while (result != -1);

            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}