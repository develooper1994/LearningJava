package com.FilesAndDirectories;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.lang.System.out;

interface DirectoryMethods{
    void makeDir();
    void makeDirs();
    void listing();
    void checking();
    void readingAttributes();
    void deleteDirectory();
    void deleteDirectoryRecursively();
    void TEST_deleteDirectoryRecursively();
}

class OldMethods implements DirectoryMethods{
    private boolean deleteDirectoryRecursively(final File directory){
        if (!directory.exists())
            return false;

        File[] files = directory.listFiles();
        if (files != null){ // Check if directory is empty
            for (File file : files){
                if (file.isDirectory()) {
                    deleteDirectoryRecursively(file); // Recursive call for subdirectory
                } else {
                    out.println("file: " + file.getName() + (file.delete() ? "deleted" : "cannot be deleted"));
                }
            }
        }
        return directory.delete(); // Delete the directory itself
    }
    public void TEST_deleteDirectoryRecursively(){
        File directory = new File("path/to/directory");

        if (deleteDirectoryRecursively(directory)) {
            System.out.println("Directory deleted successfully.");
        } else {
            System.err.println("Failed to delete directory.");
        }
    }

    @Override
    public void makeDir() {

    }

    public void makeDirs(){

    }

    @Override
    public void listing() {

    }

    @Override
    public void checking() {

    }

    @Override
    public void readingAttributes() {

    }

    @Override
    public void deleteDirectory() {

    }

    @Override
    public void deleteDirectoryRecursively() {

    }
}

class NewMethods implements DirectoryMethods{
    private void deleteDirectoryRecursively(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Override
    public void makeDir() {

    }

    @Override
    public void makeDirs() {

    }

    @Override
    public void listing() {

    }

    @Override
    public void checking() {

    }

    @Override
    public void readingAttributes() {

    }

    @Override
    public void deleteDirectory() {

    }

    @Override
    public void deleteDirectoryRecursively() {

    }

    public void TEST_deleteDirectoryRecursively(){
        Path directory = Paths.get("path/to/directory");

        try {
            deleteDirectoryRecursively(directory);
            System.out.println("Directory deleted successfully.");
        } catch (IOException e) {
            System.err.println("Failed to delete directory: " + e.getMessage());
        }

    }
}

public class Directories {
    public static void main(String[] args){
        OldMethods oldMethods = new OldMethods();
        oldMethods.makeDir();
        oldMethods.makeDirs();
        oldMethods.listing();
        oldMethods.checking();
        oldMethods.readingAttributes();
        oldMethods.deleteDirectory();
        oldMethods.TEST_deleteDirectoryRecursively();

        NewMethods newMethods = new NewMethods();
        newMethods.makeDir();
        newMethods.makeDirs();
        newMethods.listing();
        newMethods.checking();
        newMethods.readingAttributes();
        newMethods.deleteDirectory();
        newMethods.TEST_deleteDirectoryRecursively();
    }
}
