package com.FilesAndDirectories;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.System.out;

/*
* Practical for reading and writing data files!
* Byte Stream(1 byte)
* */
class FileStream {
    private final String filename = "newFile.txt";
    private String data = "Test data";
    // BufferedXXXX increases performance
    private final BufferedOutputStream outStream;
    private final BufferedInputStream inputStream;

    FileStream(){
        try {
            outStream = new BufferedOutputStream(new FileOutputStream(filename));
            inputStream = new BufferedInputStream(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void create(){
        // FileOutputStream creates the file.
        // Don't need any extra step
    }
    private void write(){
        try {
            for (byte b : data.getBytes()) {
                outStream.write(b);
            }
            outStream.close();
        } catch (IOException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void read(){
        try {
            int size=inputStream.available();
            for (int i = 0; i < size; i++) {
                out.print((char)inputStream.read());
            }
            inputStream.close();
            out.println();
        } catch (IOException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void delete(){
        try {
            Files.delete(Path.of(filename));
            out.println("File deleted");
        } catch (IOException e) {
            out.println("File cannot deleted");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        FileStream fileStream = new FileStream();
//        fileStream.create();
        fileStream.write();
        fileStream.read();
        fileStream.delete();
    }
}
