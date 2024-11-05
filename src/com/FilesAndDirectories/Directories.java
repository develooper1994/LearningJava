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
    void deleteDirectory();
    void Test();
    void TEST_deleteDirectoryRecursively();
}

/*
* java.io.*
* */
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

    private static final String dirname = "dir_OLD";
    final File dir = new File(dirname);

    @Override
    public void makeDir() {
        // bash -c mkdir $DIRNAME
        if (!dir.exists()){
            if (dir.mkdir()) out.println("Directory successfully created.");
            else out.println("Failed to create directory.");
        }
    }

    public void makeDirs(){
        // bash -c mkdir -p $DIRNAME
        final File dirs = new File(dirname + "/" + dirname + "/" + dirname); // java can solve path separator differences
        if (!dirs.exists()){
            if (dirs.mkdirs()) out.println("Directories successfully created.");
            else out.println("Failed to create directories.");
        }
    }

    @Override
    public void listing() {
        File[] dirs = new File(".").listFiles();
        if (dirs != null) {
            for (var d : dirs) {
                out.println(d.getName());
            }
        } else {
            out.println("Directory does not exist or is not accessible.");
        }
    }

    @Override
    public void checking() {
        out.println("isHidden: " + dir.isHidden());
        out.println("isAbsolute: " + dir.isAbsolute());
        out.println("isDirectory: " + dir.isDirectory());
        out.println("isFile: " + dir.isFile());
        out.println("exists: " + dir.exists());
        out.println("length: " + dir.length());
        out.println("canRead: " + dir.canRead());
        out.println("canWrite: " + dir.canWrite());
        out.println("canExecute: " + dir.canExecute());
        out.println("lastModified: " + dir.lastModified());
        for (var root :
                File.listRoots()) {
            out.println("root: " + root);
        }
    }
    @Override
    public void deleteDirectory() {
        if (dir.delete()){
            out.println("Directory deleted successfully.");
        } else {
            out.println("Failed to delete directory (it may not empty).");
        }
    }

    @Override
    public void Test() {
        makeDir();
        makeDirs();
        listing();
        checking();
        deleteDirectory();
        //TEST_deleteDirectoryRecursively();
    }
}

/*
* java.nio.*
* */
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
    public void TEST_deleteDirectoryRecursively(){
        Path directory = Paths.get("path/to/directory");

        try {
            deleteDirectoryRecursively(directory);
            System.out.println("Directory deleted successfully.");
        } catch (IOException e) {
            System.err.println("Failed to delete directory: " + e.getMessage());
        }

    }

    private static final String dirname = "dir_NEW";

    @Override
    public void makeDir() {
        // bash -c mkdir $DIRNAME
        try {
            Files.createDirectory(Path.of(dirname));
            out.println("Directory successfully created.");
        } catch (IOException e) {
            out.println("Failed to create directory: " + e.getMessage());
        }
    }

    @Override
    public void makeDirs() {
        // bash -c mkdir -p $DIRNAME
        final String dirsname = dirname + "/" + dirname + "/" + dirname;
        try {
            Files.createDirectories(Path.of(dirsname));
            out.println("Directories successfully created.");
        } catch (IOException e) {
            out.println("Failed to create directories.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void listing() {
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of("."))) {
            for (Path path :
                    stream) {
                out.println(path.getFileName());
            }
        } catch (IOException e) {
            out.println("Failed to list directory contents: " + e.getMessage());
        }
    }

    @Override
    public void checking() {
//        Path path = Path.of(dirname);
        Path path = Paths.get(dirname);
        try {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            out.println("isOther: " + attrs.isOther());
            out.println("isRegularFile: " + attrs.isRegularFile());
            out.println("isDirectory: " + attrs.isDirectory());
            out.println("isSymbolicLink: " + attrs.isSymbolicLink());
            out.println("length: " + attrs.size());
            out.println("fileKey: " + attrs.fileKey());
            out.println("creationTime: " + attrs.creationTime());
            out.println("lastModified: " + attrs.lastModifiedTime());
            out.println("lastAccessTime: " + attrs.lastAccessTime());
        } catch (IOException e) {
            out.println("Failed to read attributes: " + e.getMessage());
        }
    }

    @Override
    public void deleteDirectory() {
        try {
            Files.delete(Path.of(dirname));
            out.println("Directory deleted successfully.");
        } catch (IOException e) {
            out.println("Failed to delete directory (it may not empty).");
        }
    }

    @Override
    public void Test() {
        makeDir();
        makeDirs();
        listing();
        checking();
        deleteDirectory();
        //TEST_deleteDirectoryRecursively();
    }

}

public class Directories {
    public static void main(String[] args){
        DirectoryMethods oldMethods = new OldMethods();
        oldMethods.Test();

        out.println("\n-*-*-*-*-* newMethods *-*-*-*-*-\n");

        DirectoryMethods newMethods = new NewMethods();
        newMethods.Test();
    }
}
