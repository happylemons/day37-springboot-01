package com.emilia;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.file.AccumulatorPathVisitor;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtils {


    public static List<File> listFiles(String regex) {



        return null;
    }


    private static Path findFirstAvaliablePath(String regex){

        String[] split = regex.split("/");

        Path path = null;
        for (int i = 0; i < split.length-1; i++) {
            String pathStr = String.join("/", Arrays.copyOfRange(split, 0, i+1));
            Path tmpPath = getPath(pathStr);
            if (tmpPath != null && tmpPath.toFile().exists()){
                path = tmpPath;
            }else {
                break;
            }
        }
        return path;
    }

    @SneakyThrows
    private static List<Path> listFileWithRegex(String regex){

        Path path = findFirstAvaliablePath(regex);
        AccumulatorPathVisitor visitor = AccumulatorPathVisitor.withLongCounters(
                new RegexFileFilter(
                        Pattern.compile(regex),
                        x -> FilenameUtils.separatorsToUnix(x.toString())
                        ),
                CanReadFileFilter.CAN_READ);
        Files.walkFileTree(path, Collections.emptySet(), 50, visitor);
        List<Path> fileList = visitor.getFileList();

        return fileList.stream()
                .toList();
    }

    private static Path getPath(String pathStr){
        try {
            return Paths.get(pathStr);
        }catch (Exception e){
            return null;
        }
    }

    private static String regex = "C:/Users/bob/.m2/repository/(org|com)/.+/.*\\.jar";


    @SneakyThrows
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Path firstAvaliablePath = findFirstAvaliablePath(regex);
        List<Path> paths = listFileWithRegex(regex);
        paths.forEach(System.out::println);
    }
}
