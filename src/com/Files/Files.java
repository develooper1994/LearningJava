package com.Files;

import java.io.File;
import java.io.IOException;
import static java.lang.System.out;

public class Files {
    public static void createFile(){
        File file = new File("newFile.txt");
        try {
            if (file.createNewFile()){
                out.println("File Created: " + file.getName());
            } else {
                out.println("File already exist.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); //
        }
    }
    public static void testFile(){

    }
    public static void writeFile(){

    }
    public static void readFile(){

    }
    public static void deleteFile(){

    }

    public static void main(String[] args){
        createFile();
//        testFile();
//        readFile();
//        writeFile();
//        deleteFile();
    }
}
