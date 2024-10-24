package com.DesignPattern;

import static java.lang.System.out;

/**
 *
 *     To create logger classes
 *     To create configuration management-related classes
 *     To create classes related to database connection pooling
 *     To create a class for the caching mechanism
 *
 */
class Singleton {
    private static final Singleton Singleton = new Singleton();
    private Singleton(){}

    public static Singleton getInstance(){
        return Singleton;
    }

    public void demo(){
        out.println("demo for Singleton");
    }

    public static void Tester(){
        //Singleton Singleton = new Singleton(); // compile-error
        Singleton tmp = getInstance();
        tmp.demo();
    }
}

/**
 *  Thread-safe version
 *     To create logger classes
 *     To create configuration management-related classes
 *     To create classes related to database connection pooling
 *     To create a class for the caching mechanism
 *
 */
class ClassicSingleton{
    private static ClassicSingleton classicSingleton = null;
    private ClassicSingleton(){}

    public static ClassicSingleton getInstance(){
        if (classicSingleton == null) {
            classicSingleton = new ClassicSingleton();
        }
        return classicSingleton;
    }

    public void demo(){
        out.println("demo for Singleton");
    }
    
    public static void Tester(){
        //ClassicSingleton tmp = new ClassicSingleton();
        ClassicSingleton tmp = ClassicSingleton.getInstance();
        tmp.demo();
    }
}

public class DesignPattern {

    public static void main(String[] args){
        Singleton.Tester();
        ClassicSingleton.Tester();
    }
}
