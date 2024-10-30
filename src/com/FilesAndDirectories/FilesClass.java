package com.FilesAndDirectories;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static java.lang.System.out;

/*
* Classical, Old method
* Char Stream (2 bytes)
* Generic usage
* Programmer have to create multiple objects handle everything manually
* */
class FilesClass {
    private final String filename = "newFile.txt";
    private String data = "Test data";
    private final File file = new File(filename);
    // BufferedXXXX increases performance
    private final BufferedReader fileReader; // -> FileNotFoundException
    private BufferedWriter fileWriter; // -> IOException

    FilesClass() {
        try {
            // close them whenever work is finished.
            fileWriter = new BufferedWriter(new FileWriter(file)); // -> IOException
            fileReader = new BufferedReader(new FileReader(file)); // -> FileNotFoundException
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void createFile(){
        try {
            if (file.createNewFile()){ // not mandatory. FileWriter class can create a file.
                out.println("File Created: " + file.getName());
                testFile();
            } else {
                out.println("File already exist.");
            }
        } catch (IOException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace();
        }
    }
    private void testFile(){
        out.println("exists: " + file.exists());
        out.println("isFile: " + file.isFile());
        out.println("isDirectory: " + file.isDirectory());
        out.println("isAbsolute: " + file.isAbsolute());
        out.println("isHidden: " + file.isHidden());
        out.println("canRead: " + file.canRead());
        out.println("canWrite: " + file.canWrite());
        out.println("canExecute: " + file.canExecute());
        out.println("getAbsolutePath: " + file.getAbsolutePath());
        out.println("getAbsoluteFile: " + file.getAbsoluteFile());
        out.println("getParent: " + file.getParent());
        out.println("getPath: " + file.getPath());
        out.println("getFreeSpace: " + file.getFreeSpace());
        out.println("getUsableSpace: " + file.getUsableSpace());
        out.println("getTotalSpace: " + file.getTotalSpace());
        try {
            out.println("getCanonicalFile: " + file.getCanonicalFile());
            out.println("getCanonicalPath: " + file.getCanonicalPath());
        } catch (IOException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace(); //
        }
    }
    private void writeFile(){
        try {
            out.println("Writing in to file: " + file.getName());
            // write in to that!
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void appendFile(){
        try {
            fileWriter = new BufferedWriter(new FileWriter(filename, true)); // filename, (append or not append)
            out.println("Writing in to file: " + file.getName());
            // write in to that!
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void readByCharFile(){
        try {
            out.print("Reading from file: " + file.getName() + " | Data: ");
            // read from file

            int c;
            while ((c = fileReader.read()) != -1) // -> IOException
            {
                char ch = (char) c;
                out.print(ch);
            }
            fileReader.close();
            out.println(" | Length of file: " + file.length());
            out.println();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void readByLineFile(){
        out.print("Reading from file: " + file.getName() + " | Data: ");

        try {
            // BufferedReader bufferedReader = new BufferedReader(fileReader); // filepointer sets to end due to first reading
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null)
                out.println(line);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IOException: An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void deleteFile(){
        out.println(file.exists() && file.delete() ? "File deleted" : "File cannot deleted");
//         file.deleteOnExit(); // delete on jre close
    }
    private void copyFile(){
        Path Source = Paths.get(file.getName());
        Path Destination = Paths.get(file.getName() + "_copy");
        try {
            out.println(file.getName() + " copied to: " + Destination);
            //Files.copy(Source, Destination); // throws IOException
            Files.copy(Source, Destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("IOException: An error occurred. File Exist");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void moveFile(){
        Path Source = Paths.get(file.getName());
        Path Destination = Paths.get(file.getName() + "_move");
        try {
            out.println(file.getName() + " moved to: " + Destination);
            //Files.move(Source, Destination); // throws IOException
            Files.move(Source, Destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("IOException: An error occurred. File Exist");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        FilesClass files = new FilesClass();
        files.createFile();
        files.testFile();
        files.writeFile();
        files.appendFile();
        files.readByCharFile();
        files.readByLineFile();

        files.copyFile();
        files.moveFile();

//        files.deleteFile();
    }
}
