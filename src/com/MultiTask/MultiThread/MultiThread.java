package com.MultiTask.MultiThread;

import java.util.concurrent.*;

import static java.lang.System.out;

/*
* New (Yeni): İş parçacığı henüz başlatılmadı.
* Runnable (Çalışabilir): İş parçacığı başlatıldı, çalışmaya hazır.
* Blocked (Bloke): İş parçacığı bir kaynağı bekliyor.
* Waiting (Beklemede): Başka bir iş parçacığının eylemini bekliyor.
* Timed Waiting (Zamanlanmış Bekleme): Belirli bir süre bekliyor.
* Terminated (Sonlandırıldı): İş parçacığı çalışmasını tamamladı.
*
* State Chart:
*                +-----------+
                 |   New     |
                 +-----------+
                        |
                        |  start()
                        v
                 +-----------+
                 | Runnable  |
                 +-----------+
                /      |      \
               /       |       \
              /        |        \
             v         |         v
       +---------+     |    +----------+
       | Blocked |     |    | Waiting  |
       +---------+     |    +----------+
             |         |         |
             |         |         |
             v         |         |
      +-------------+  |   +----------------+
      |  Terminated |<-|-->| Timed Waiting |
      +-------------+  |   +----------------+
                        |
                        |
                        v
                   +-----------+
                   | Runnable  |
                   +-----------+
* */

/*
* Don't use it. Java doesn't support extend more than one class.
* */
class MyThread extends Thread {
    public void run(){
        for (int i=0 ;i<10; i++) {
            out.println(Thread.currentThread().getId() + "Name: " + Thread.currentThread().getName() + " - Value: " + i); // deprecated
            // out.println(Thread.currentThread().threadId() + "Name: " + Thread.currentThread().getName() + " - Value: " + i); // jdk-19
            try {
                Thread.sleep(1000); // wait for 1 sec
            } catch (InterruptedException e) {
                out.println(e.getMessage());
            }
        }
    }

    public static void test(){
        /*
        ** Daemon Thread ve Kullanıcı Thread Farkı
        * Kullanıcı İş Parçacığı (User Thread): Ana uygulamanın bir parçası olarak çalışır. Tüm kullanıcı iş
        * parçacıkları tamamlanmadan JVM kapanmaz.
        *
        * Daemon İş Parçacığı (Daemon Thread): Arka planda çalışır ve yalnızca ana uygulama açıkken çalışır.
        * Tüm kullanıcı iş parçacıkları bittiğinde daemon iş parçacıkları otomatik olarak sonlanır.
        *
        ** Dikkat Edilmesi Gerekenler
        * Daemon iş parçacıkları, normal iş parçacıkları gibi kaynaklara veya veriye uzun süre bağlı kalmamalıdır.
        * Çünkü ana iş parçacığı bittiğinde daemon iş parçacığı herhangi bir veri kaydedilmeden sonlanabilir.
        * Daemon iş parçacıkları, kullanıcı işlemlerinin gerektirdiği kritik görevler için uygun değildir,
        * çünkü çalışmasının garanti edilmesi zor olabilir.
        * */
        var thread1 = new MyThread();
        thread1.setName("MyThread-thread1");
        var thread2 = new MyThread();
        thread2.setName("MyThread-thread2");
        var thread3 = new MyThread();
        thread3.setDaemon(true); // low priority background job. stops when application or jvm stops.
        thread3.setName("MyThread-thread3 - Daemon thread");

        thread1.start();
        /*
        // To run tasks sequentially
        try {
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        */
        thread2.start();
        thread3.start();

        // To run tasks sequentially. waits main thread
        try {
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
* Use it. Java supports implement more than one class.
* */
class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i=0 ;i<10; i++) {
            out.println(Thread.currentThread().getId() + "Name: " + Thread.currentThread().getName() + " - Value: " + i); // deprecated
            // out.println(Thread.currentThread().threadId() + "Name: " + Thread.currentThread().getName() + " - Value: " + i); // jdk-19
        }
    }
    public static void test(){
        // you can implement one thread with this(new Thread(this, threadName)) overriding start method to simplify syntax.
        var myRunnable = new MyRunnable();
        var thread1 = new Thread(myRunnable, "thread1");
        var thread2 = new Thread(myRunnable ,"thread2");
        thread1.setPriority(10); // low:1 - high:10;

        thread1.start();
        /*
        // To run tasks sequentially
        try {
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        */

        // To run tasks sequentially. waits main thread
        thread2.start();
        out.println("thread1 isAlive?: " + thread1.isAlive());
        try {
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
* - Eğer iş parçacığı sleep(), wait(), veya join() gibi kesintiye duyarlı bir durumda (blocked) ise,
* InterruptedException hatası fırlatır.
* - Eğer iş parçacığı o an beklemede değilse, yalnızca iş parçacığına "kesildi" bayrağını (flag) set eder ve
* iş parçacığı isInterrupted() metodu ile bu bayrağı kontrol edebilir.
* */
class Interrupted implements Runnable {
    String threadName;
    Thread thread;
    private void Count() {
        for (int i=0 ;i<10; i++) {
            out.println(Thread.currentThread().getId() + "Name: " + Thread.currentThread().getName() + " - Value: " + i); // deprecated
            // out.println(Thread.currentThread().threadId() + "Name: " + Thread.currentThread().getName() + " - Value: " + i); // jdk-19
        }
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            out.println("Thread running...");
            Count();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                out.println("Thread interrupted");
            }
        }
    }

    public void start(){
        if (thread == null) {
            thread = new Thread(this, threadName==null ? "thread" : threadName);
            thread.start();
        }
    }

    public static void test(){
        Interrupted interrupted = new Interrupted();
        interrupted.start();

        try {
            while (true) {
                Thread.sleep(2000);
                interrupted.thread.interrupt();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
* Synchronization Problem
* Not every process or thread suits to embarrassingly parallel
* - synchronized: Atomic operation
* */
class Counter {
    private int counter = 0;
    public synchronized void increment(){
        counter++;
    }
    public int getCounter() {
        return counter;
    }
    public static void test(){
        /*
        * Expected outcome: counter==20
        * thread1 -> increase counter from 0 to 9
        * thread2 -> increase counter from 9 to 20
        * */
        var counter = new Counter();
        var thread1 =  new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        counter.increment();
                    }
                }
        );

        var thread2 =  new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        counter.increment();
                    }
                }
        );

        thread1.start();
        thread2.start();

        try {
            // synchronized keyword is not enough. As long as process is not embarrassingly parallel, some synchronization problem always happens.
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        out.println("Result: " + counter.getCounter());
    }
}

/*
* java.util.concurrent.*
* */
class Concurrent {
    public static void test1(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executorService.submit(()->{
                out.println("Thread Id: " + Thread.currentThread().getName());
            });
        }
        executorService.shutdown(); // stop the execution, transition thread state to "DEAD"
    }
    public static void test2(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<Integer> task = () -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum+=i;
            }
            return sum;
        };

        Future<Integer> result = executorService.submit(task);
        try {
            out.println("Sum: " + result.get());
        } catch (InterruptedException | ExecutionException e) {
            out.println(e.getMessage());
        }
        executorService.shutdown(); // stop the execution, transition thread state to "DEAD"
    }
    public static void test(){
        test1();
        test2();
    }
}

public class MultiThread {

    public static void main(String[] args){
//            MyThread.test();
//            MyRunnable.test();
            Interrupted.test();
//            Counter.test();
//            Concurrent.test();
        }
}
