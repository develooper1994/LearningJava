package com.Files;

import java.io.*;

import static java.lang.System.out;

/*
* Practical for reading and writing data files!
* */
class FileStream {
    String filename = "newFile.txt";
    String data = "Test data";
    FileOutputStream outStream;
    FileInputStream inputStream;

    FileStream(){
        try {
            outStream = new FileOutputStream(filename);
            inputStream = new FileInputStream(filename);
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
        out.println(new File(filename).delete() ? "File deleted" : "File cannot deleted");
    }

    public static void main(String[] args){
        FileStream fileStream = new FileStream();
//        fileStream.create();
        fileStream.write();
        fileStream.read();
        fileStream.delete();
    }
}
