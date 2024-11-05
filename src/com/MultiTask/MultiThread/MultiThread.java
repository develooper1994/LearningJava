package com.MultiTask.MultiThread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

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
                 |    New    |
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
        *
        * Daemon thread örneği: jvm garbage collector
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
    public void interrupt(){
        thread.interrupt();
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
                interrupted.interrupt();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// -*-*-*-*-* Synchronization *-*-*-*-*-
/*
* - synchronized anahtar kelimesi, belirli bir nesne veya metod üzerinde kilitleme sağlar.
* - ReentrantLock, daha gelişmiş kilitleme işlevleri sunar(manuel synchronized). [lock, unlock, tryLock, lockInterruptibly]
* - Atomic sınıflar, daha düşük seviyede kilit gerektirmeyen atomik işlemler sağlar. [AtomicInteger, AtomicBoolean]
* - volatile, değişkenin ana hafızadaki değerini iş parçacıklarına yansıtır.
* */

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

class ProducerConsumer1{
    private final int MAX_SIZE = 5;
    private final Queue<Integer> buffer = new LinkedList<>(); // LinkedList Queue Interface

    // producer
    public void produce() throws InterruptedException {
        int value=0;
        while (true){
            synchronized (this) {
                while (buffer.size() >= MAX_SIZE) {
                    out.println("Buffer reached max size");
                    wait();
                }
                out.println("Producer value: " + value);
                buffer.add(value++);
                notifyAll();
                Thread.sleep(1000);
            }
        }
    }
    //consumer
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    out.println("Buffer is empty. Consumer is waiting");
                    wait();
                }
                int value = buffer.poll();
                out.println("Consumer value: " + value);
                notifyAll();
                Thread.sleep(1000);
            }
        }
    }

    public static void test(){
        ProducerConsumer1 pc = new ProducerConsumer1();

        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();

    }
}

/*
* The synchronized lock is reentrant, meaning that if a thread already holds a lock, it can acquire
* same lock again without causing a deadlock.
* */
class Synchronized{
    static class Reentrant{
        private synchronized static void innercall(){
            out.println("inner call");
        }
        private synchronized static void outhercall(){
            out.println("outher call");
            innercall();
        }

        public static void nodeadlock(){
            outhercall();
        }
    }
    static class Deadlock{
        private final Object lock1 = new Object();
        private final Object lock2 = new Object();

        private void method1(){
            synchronized (lock1){
                out.println("Thread 1: Holding lock 1...");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                out.println("Thread 1: Waiting for lock 1...");
                synchronized (lock2){
                    out.println("Thread 1: Holding lock 1 and lock 2...");
                }
                out.println("Thread 1: lock 2 released");
            }
            out.println("Thread 1: lock 1 released");
        }
        private void method2(){
            synchronized (lock2){
                out.println("Thread 2: Holding lock 2...");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                out.println("Thread 2: Waiting for lock 2...");
                synchronized (lock1){
                    out.println("Thread 2: Holding lock 1 and lock 2...");
                }
                out.println("Thread 2: lock 2 released");
            }
            out.println("Thread 2: lock 1 released");
        }
        public static void deadlock(){
            Deadlock deadlock = new Deadlock();
            Thread thread1= new Thread(deadlock::method1);
            Thread thread2= new Thread(deadlock::method2);

            thread1.start();
            thread2.start();
        }
    }

    public static void test(){
        Reentrant.nodeadlock();
        Deadlock.deadlock();
    }
}

/*
* Manuel Synchronized.
* looks like c++ mutex
* */
class LockCounter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment(){
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
    public int getCount(){
        return count;
    }

    public static void test(){
        LockCounter lockCounter = new LockCounter();

        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                lockCounter.increment();
            }
        });
        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                lockCounter.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        out.println("Result: " + lockCounter.getCount());
    }
}
/*
* Atomic instruction support!
* */
class AtomicCounter{
    private AtomicInteger count = new AtomicInteger(0);
    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public static void test(){
        AtomicCounter atomicCounter = new AtomicCounter();

        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                atomicCounter.increment();
            }
        });
        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                atomicCounter.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        out.println("Result: " + atomicCounter.getCount());
    }
}
// -*-*-*-*-* ThreadPoll *-*-*-*-*-
/*
Neden ThreadPool Kullanılır?
* Performans: Her görev için yeni bir iş parçacığı oluşturmak ve kapatmak yerine mevcut iş parçacıkları tekrar kullanılır, bu da maliyetli iş parçacığı oluşturma ve yok etme işlemlerini azaltır.
* Kaynak Yönetimi: İş parçacığı sayısı kontrol altında tutularak sistem kaynakları daha verimli kullanılır.
* Kolaylık: Java’nın Executors sınıfı, çeşitli türde iş parçacığı havuzlarını oluşturmak için basit yöntemler sağlar.
* ThreadPool Çeşitleri

Java’da çeşitli iş parçacığı havuzları bulunmaktadır:

- Fixed Thread Pool: Sabit sayıda iş parçacığına sahip bir havuz oluşturur.
    Kullanımı: Executors.newFixedThreadPool(int nThreads)
- Cached Thread Pool: Gerektiğinde yeni iş parçacığı ekler, kullanılmayan iş parçacıklarını belirli bir süre sonra kapatır.
    Kullanımı: Executors.newCachedThreadPool()
- Single Thread Executor: Tek iş parçacığıyla görevleri sırayla çalıştırır.
    Kullanımı: Executors.newSingleThreadExecutor()
- Scheduled Thread Pool: Zamanlanmış veya belirli aralıklarla çalışan görevler için kullanılır.
    Kullanımı: Executors.newScheduledThreadPool(int corePoolSize)
* */

/*
* Performans ve Esneklik: ScheduledExecutorService, Timer'dan daha esnek ve çok iş
* parçacıklı görevleri daha verimli yönetir.
* Hata Toleransı: Timer, bir görevde hata oluştuğunda tüm zamanlayıcıyı durdurabilir;
* ScheduledExecutorService ise daha dayanıklıdır.
*
* Schedule: Tek seferlik çalıştırma gerçekleştirir. Qt oneShotTimer gibi
* ScheduleAtFixedRate: Birden fazla kez çalıştırma gerçekleştirir. Metodun sonuçlanmasıını beklemez.
* Periyotlar arasındaki süre metotların başlangıçları arasındaki süresidir.
* ScheduleWithFixedDelay: Birden fazla kez çalıştırma gerçekleştirir. Metodun sonuçlanmasıını bekler.
* Periyotlar arasındaki süre metotların bitişleri arasındaki süresidir.
*
* ScheduledExecutorService
* Oluşturulan scheduler nesnesi birden fazla kullanılabilir fakat!
* - 1 threadpool kullanılıyorsa scheduler nesnesine bağlı görevler sırayla gerçekleştirilir.
* - 1 threadpool'dan fazla kullanılıyorsa scheduler nesnesine bağlı görevler kaç thread varsa threadlere atanarak eş zamanlı gerçekleştirilebilir.
*
* Not: Eğer shutdown() çağrıldıktan sonra yeni görev eklemeye çalışırsanız, bir RejectedExecutionException hatası alırsınız.
* */
class ScheduledTasks{
    static class Alarm implements Runnable{
        String m_name;
        public Alarm(String name){
            m_name = name;
        }
        @Override
        public void run() {
            out.println(m_name + " ⏰ " + System.currentTimeMillis());
        }
    }
    static void test1(){
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            // start in 0-second delay with 2-seconds periods. Doesn't wait to finish the execution.
            final ScheduledFuture<?> alarmHandler = scheduler.scheduleAtFixedRate(new Alarm("scheduleAtFixedRate"), 0, 2, TimeUnit.SECONDS);
            // stop the scheduler in 10-seconds
            scheduler.schedule((Runnable) () -> {
                alarmHandler.cancel(true);
                scheduler.shutdown();
                out.println("scheduleAtFixedRate finished");
            }, 10, TimeUnit.SECONDS);

            Runnable task = () -> {
                try {
                    out.println("scheduleWithFixedDelay ⏰");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    out.println("Interrupted");
                }
            };
            // start in 1-second delay with 3-seconds periods. Waits to finish the execution.
            final ScheduledFuture<?> alarmHandler2 = scheduler.scheduleWithFixedDelay(task, 1, 3, TimeUnit.SECONDS);
            scheduler.schedule((Runnable) () -> {
                alarmHandler2.cancel(true);
                scheduler.shutdown();
                out.println("scheduleWithFixedDelay finished");
            }, 10, TimeUnit.SECONDS);
    }
    // newScheduledThreadPool(2)
    static void test2(){
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
            // start in 0-second delay with 2-seconds periods. Doesn't wait to finish the execution.
            final ScheduledFuture<?> alarmHandler = scheduler.scheduleAtFixedRate(new Alarm("scheduleAtFixedRate"), 0, 2, TimeUnit.SECONDS);
            // stop the scheduler in 10-seconds
            Runnable task = () -> {
                try {
                    out.println("scheduleWithFixedDelay ⏰");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    out.println("Interrupted");
                }
            };
            // start in 1-second delay with 3-seconds periods. Doesn't wait to finish the execution.
            final ScheduledFuture<?> alarmHandler2 = scheduler.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
            scheduler.schedule((Runnable) () -> {
                alarmHandler2.cancel(true);
                scheduler.shutdown();
                out.println("scheduleWithFixedDelay finished");
            }, 10, TimeUnit.SECONDS);
    }
    // timer
    static void test3(){
        // Timer
        Timer timer = new Timer();
        Counter counter = new Counter();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter.increment();
                out.println(counter.getCounter() + " Timer Alarm ⏰ " + System.currentTimeMillis());
            }
        };
        // 0-second delay with repeats every 1-second
        timer.scheduleAtFixedRate(timerTask, 0,2000);
        // Örneği sonlandırmak için 10 saniye sonra Timer'ı kapatalım
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                timer.purge();
            }
        }, 10000);
    }
    public static void test(){
        test1();
        test2();
        test3();
    }
}

/*
* java.util.concurrent.*
* */
class FixedThreadPool {
    static class Task implements Runnable {

        public void run() {

            try {
                Long duration = (long) (Math.random() * 5);
                System.out.println("Running Task! Thread Name: " +
                        Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(duration);

                System.out.println("Task Completed! Thread Name: " +
                        Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
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
    public static void test3(){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(()->{
                out.println("Task: " + taskId + " started. Thread name: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                out.println("Task: " + taskId + " stopped. Thread name: " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
    public static void test4(){
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Assigning tasks to Threadpool
        for (int i = 0; i < 10; i++) {
             int taskId = i;
             executor.submit(()->{
                 out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
                 try {
                     Thread.sleep(500);
                 } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                 }
             });
        }

        // wait all tasks to complete
        executor.shutdown();
        // if all tasks must end without waiting, use deadline timer and shutdown forcefully.
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)){
                out.println("Executor shutdown as tasks did not completed.");
                executor.shutdownNow(); // shutdown forcefully
            }
            System.out.println("All tasks completed.");
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    public static void attributes(){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;

        //Stats before tasks execution
        System.out.println("Largest executions: "
         + pool.getLargestPoolSize());
        System.out.println("Maximum allowed threads: "
         + pool.getMaximumPoolSize());
        System.out.println("Current threads in pool: "
         + pool.getPoolSize());
        System.out.println("Currently executing threads: "
         + pool.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): "
         + pool.getTaskCount());

        executor.submit(new Task());
        executor.submit(new Task());
        executor.submit(new Task());
        executor.submit(new Task());

      //Stats after tasks execution
      System.out.println("Core threads: " + pool.getCorePoolSize());
      System.out.println("Largest executions: "
         + pool.getLargestPoolSize());
      System.out.println("Maximum allowed threads: "
         + pool.getMaximumPoolSize());
      System.out.println("Current threads in pool: "
         + pool.getPoolSize());
      System.out.println("Currently executing threads: "
         + pool.getActiveCount());
      System.out.println("Total number of threads(ever scheduled): "
         + pool.getTaskCount());

      executor.shutdown();
    }
    public static void test(){
        test1();
        test2();
        test3();
        test4();
        attributes();
    }
}

class ProducerConsumer2{
    private static final int MAX_SIZE = 5;
    private static final int PRODUCER_COUNT = 2;
    private static final int CONSUMER_COUNT = 2;
    // ArrayBlockingQueue ensures thread-safe access with put() and take() methods
    private final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(MAX_SIZE);

    class Producer implements Runnable {
        private int value = 0;
        @Override
        public void run() {
            try {
                for(;;){
                    out.println("Producer value: " + value);
                    buffer.put(value++);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                for(;;){
                    int value = buffer.take();
                    out.println("Consumer value: " + value);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void start(){
        ExecutorService executor = Executors.newFixedThreadPool(PRODUCER_COUNT + CONSUMER_COUNT);

        // producer
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            executor.submit(new Producer());
        }
        // consumer
        for (int i = 0; i < CONSUMER_COUNT; i++) {
            executor.submit(new Consumer());
        }
    }

    public static void test() {
        ProducerConsumer2 producerConsumer2 = new ProducerConsumer2();
        producerConsumer2.start();
    }
}

class ThreadGroupDemo{

    public static void test(){
        ThreadGroup parentGroup = new ThreadGroup("Parent Group");
        ThreadGroup childGroup1 = new ThreadGroup(parentGroup, "Child Group");
        ThreadGroup childGroup2 = new ThreadGroup(parentGroup, "Child Group");

        Thread parentThread1 = new Thread(parentGroup, ()->{out.println("Parent Thread 1");}, "Parent Thread 1");
        Thread parentThread2 = new Thread(parentGroup, ()->{out.println("Parent Thread 2");}, "Parent Thread 2");
        parentThread1.start();
        parentThread2.start();

        Thread childThread11 = new Thread(childGroup1, ()->{out.println("Child-1 Thread-1");}, "Child-1 Thread-1");
        Thread childThread12 = new Thread(childGroup1, ()->{out.println("Child-1 Thread-2");}, "Child-1 Thread-2");
        Thread childThread21 = new Thread(childGroup2, ()->{out.println("Child-2 Thread-1");}, "Child-2 Thread-1");
        Thread childThread22 = new Thread(childGroup2, ()->{out.println("Child-2 Thread-2");}, "Child-2 Thread-2");
        childThread11.start();
        childThread12.start();
        childThread21.start();
        childThread22.start();

        out.println("Parent Group, Active thread count: " + parentGroup.activeCount());
        out.println("Child Group 1, Active thread count: " + childGroup1.activeCount());
        out.println("Child Group 2, Active thread count: " + childGroup2.activeCount());

        parentGroup.interrupt();
    }
}

public class MultiThread {

public static void main(String[] args){
            MyThread.test();
            MyRunnable.test();
            Interrupted.test();
            Counter.test();
            ProducerConsumer1.test();
            Synchronized.test();
            LockCounter.test();
            AtomicCounter.test();
            ScheduledTasks.test();
            FixedThreadPool.test();
            ProducerConsumer2.test();
            ThreadGroupDemo.test();

    // There are much more but i can not cover all the details.
        }
}
