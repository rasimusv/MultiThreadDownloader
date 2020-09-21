package ru.itis.MultiThreadDownloader.utils;

import com.beust.jcommander.IStringConverter;
import java.util.*;

public class FileListConverter implements IStringConverter<List<String>> {
    @Override
    public List<String> convert(String files) {
        return new ArrayList<>(Arrays.asList(files.split(";")));
    }
}