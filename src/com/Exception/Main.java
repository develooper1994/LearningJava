package com.Exception;

import static java.lang.System.out;

public class Main {
    //<exception>
    class DivisionException extends Exception{
        public DivisionException(String msg){
            super(msg);
        }
    }
    class ZeroException extends Exception{
        public ZeroException(String msg){
            super(msg);
        }
    }
    //</exception>

    private double exceptionThrower(double a, double b) throws Exception {
        if (a==0 && b==0){
            throw new DivisionException("DivisionException: dividend and divisor are 2");
        } else if (a==1 && b==1) {
            throw new ZeroException("ZeroException: dividend and divisor are 1");
        } else if (a==2 && b==2) {
            throw new ArithmeticException("ArithmeticException: dividend and divisor are 2");
        } else if (a==3 && b==3) {
            throw new NullPointerException("NullPointerException: dividend and divisor are 3");
        } else if (a==4 && b==4) {
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException: dividend and divisor are 4");
        } else if (a==5 && b==5) {
            throw new Exception("Exception: dividend and divisor are 4");
        }
        return a/b;
    }

    void exceptions(){
        try {
//            exceptionThrower(0,0);
//            exceptionThrower(1,1);
//            exceptionThrower(2,2);
//            exceptionThrower(3,3);
//            exceptionThrower(4,4);
            exceptionThrower(5,5);
        }
        catch (DivisionException e) {
            System.out.println("DivisionException caught.");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack Trace: ");
            e.printStackTrace(); // throws exception and prints stacktrace
            System.out.println("Cause: " + e.getCause());
//            throw new RuntimeException(e); // rethrow
        }
        catch (ZeroException e) {
            System.out.println("ZeroException caught.");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack Trace: ");
            e.printStackTrace(); // throws exception and prints stacktrace
            System.out.println("Cause: " + e.getCause());
//            throw new RuntimeException(e); // rethrow
        }
        catch (ArithmeticException e) {
            System.out.println("ArithmeticException caught.");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack Trace: ");
            e.printStackTrace(); // throws exception and prints stacktrace
            System.out.println("Cause: " + e.getCause());
//            throw new RuntimeException(e); // rethrow
        }
        catch (NullPointerException e) {
            System.out.println("NullPointerException caught.");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack Trace: ");
            e.printStackTrace(); // throws exception and prints stacktrace
            System.out.println("Cause: " + e.getCause());
//            throw new RuntimeException(e); // rethrow
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException caught.");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack Trace: ");
            e.printStackTrace(); // throws exception and prints stacktrace
            System.out.println("Cause: " + e.getCause());
//            throw new RuntimeException(e); // rethrow
        }
        catch (Exception e) {
            System.out.println("NullPointerException caught.");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack Trace: ");
            e.printStackTrace(); // throws exception and prints stacktrace
            System.out.println("Cause: " + e.getCause());
//            throw new RuntimeException(e); // rethrow
        }
        finally {
            out.println("will eventually runs even throws an exception");
        }
    }

    public static void main(String[] args){
        Main m = new Main();
        m.exceptions();
    }
}
