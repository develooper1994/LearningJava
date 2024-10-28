package com.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;

import static java.lang.System.out;

/*
* New way to handle file operations with static methods
* Java 7+
* + Platform Independent, High performance and Flexible file operations
*
* */
public class NewFilesClass {
    String filename = "newFile.txt";
    String data = "Test data\n";

    private void createFile() {
        // nio.Files static methods creates the file.
        // Don't need any extra step
    }
    private void write() {
        //        Path currentRelativePath = Paths.get("");
        try {
            Files.write(Paths.get(filename), data.getBytes(), StandardOpenOption.CREATE); // creates evertime
//            Files.write(Paths.get(filename), data.getBytes(), StandardOpenOption.CREATE_NEW); // creates only if not exist, otherwise throw an exception
            var lines = Arrays.asList("Line-1", "Line-2", "Line-3");
//            Files.write(Paths.get(filename), lines, StandardCharsets.UTF_8); // creates evertime
            Files.write(Paths.get(filename), lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND); // creates evertime
        } catch (IOException e) {
            System.out.println("IOException: An error occurred. File Exist");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void read() {
        try {
            var content = Files.readAllLines(Paths.get(filename));
            out.println(content);
        } catch (IOException e) {
            System.out.println("IOException: An error occurred. File Exist");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void deleteFile() {
        out.println(new File(filename).delete() ? "File deleted" : "File cannot deleted");
    }

    public static void main(String[] args){
        var files = new NewFilesClass();
//        files.createFile();
        files.write();
        files.read();
//        files.deleteFile();
    }


}
