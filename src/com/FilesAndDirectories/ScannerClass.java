package com.FilesAndDirectories;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static java.lang.System.out;

public class ScannerClass {
    static void scannerTest1(){
        final Scanner in = new Scanner(System.in);
        out.println("in.delimiter(): " + in.delimiter());

        out.println("Start entering lines.");
        while (in.hasNext()){
            // System.out.println("Found something: " + scanner.next());
            if (in.hasNextInt())
                out.println("Found a Int: " + in.nextInt());
            else if (in.hasNextLong())
                out.println("Found a Long: " + in.nextLong());
            else if (in.hasNextFloat())
                out.println("Found a Float: " + in.nextFloat());
            else if (in.hasNextDouble())
                out.println("Found a Double: " + in.nextDouble());
            else if (in.hasNextBigDecimal())
                out.println("Found a BigDecimal: " + in.nextBigDecimal());
            else if (in.hasNextBigInteger())
                out.println("Found a BigInteger: " + in.nextBigInteger());
            else if (in.hasNextBoolean())
                out.println("Found a Boolean: " + in.nextBoolean());
            else if (in.hasNextLine())
                out.println("Found a Line: " + in.nextLine());
        }
        in.close();
    }
    static void scannerTest2(){
        final Scanner in = new Scanner(System.in);
        int sum = 0, count = 0;
        double mean = 0;

        out.println("Start entering numbers");
        while (in.hasNext()) {
            if (in.hasNextInt()) {
                int num = in.nextInt();
                ++count;
                mean += (num - mean)/count;
            }
            else if (in.hasNextLine()) {
                String str = in.nextLine();
                if (str.contains("q"))
                    break;
            }
        }
        in.close();
        out.println("Mean: " + mean);
        out.println("Count: " + count);
    }
    static void scannerWithData(){
        String data = "This is a test data:\n 123 456 789";
        final Scanner scanner = new Scanner(data);
        while (scanner.hasNext()){
            String word = scanner.next();
            out.println("word: " + word);
        }
        scanner.close();
    }
    static void scannerWithFile(){
        String filename = "newFile.txt";
        File file = new File(filename);
        if (!file.exists()){
            out.println("File Not Exist");
            return;
        }

        try {
            final Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String data = scanner.next(); // takes words
//                String data = scanner.nextLine(); // takes lines
                out.println("data: " + data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: An error occurred. File Not Exist");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
//        scannerTest1();
//        scannerTest2();
//        scannerWithData();
        scannerWithFile();
    }
}
