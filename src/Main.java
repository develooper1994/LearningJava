import java.util.Arrays;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import java.util.stream.IntStream;

// custom packages
// import com.example.mypackage.*;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class Main {
    //<enums>
    enum Days {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

        @Override
        public String toString() {
            return switch (this){
                case MONDAY: yield "Bugün Pazartesi. İş başı\n";
                case TUESDAY: yield "Bugün Salı.\n";
                case WEDNESDAY: yield "Bugün Çarşamba.\n";
                case THURSDAY: yield "Bugün Perşembe.\n";
                case FRIDAY: yield "Bugün Cuma. Hayırlı Cumalar. Enseyi kapa\n";
                // java language doesn't have "or" by default
                case SATURDAY:
                case SUNDAY: yield "Hafta Sonu 😉\n";
                default: yield "Böyle bir gün yok\n";
            };
        }
    }
    public enum Color{
        // Enums can have default values
        RED("#FF0000"), GREEN("#00FF00"), BLUE("#0000FF")
        ;

        private final String hexCode;

        Color(String hexCode) {
            this.hexCode = hexCode;
        }

        public String getHexCode() {
            return hexCode;
        }
    }
    class Window{
        public enum WindowProp{
            MINIMIZE(1 << 0), // 0001
            WINDOWLESS(1 << 1), // 0010
            FULLSCREEN(1 << 2), // 0100
            RESIZABLE(1 << 3); // 1000
            private final int windowProp;
            ;

            WindowProp(int windowProp) {
                this.windowProp = windowProp;
            }

            public int getWindowProp(){
                return windowProp;
            }
        }
        private int properties;
        public boolean hasProperties(WindowProp prop){
            return (properties & prop.getWindowProp()) != 0;
        }

        public void setWindowProp(WindowProp... props){
            for (WindowProp prop : props) {
                properties |= prop.getWindowProp();
            }
        }

        public void Tester(){

        }
    }
    //</enums>

    Main(){
        out.println("Main Default Constructor");
    }
	int x;
    static int NotInitalizedGlobalVariable;
    // static double[] NotInitalizedGlobalArray = new double[10]; // global empty array usage, cause runtime exception

    private static void asciiTablePrint(){
        System.out.println("-*-*-*-*-* asciiTablePrint *-*-*-*-*-");
        for (short i=0;i<256;++i){
            System.out.printf("%d = %c | ", i, (char)i);
            if (i % 16 == 15)
                System.out.println();
        }
    }
    private static void printfArray(int[] arr2){
        for (int i = 0; i < arr2.length; i++){
            System.out.printf("arr2[%d]: %d | ", i, arr2[i]);
        }
        System.out.println("\n------------");
    }
    private static int[] reverseArray(int[] arr){
        int[] result = new int[arr.length];

        for (int begin=0, end=arr.length-1; begin<arr.length; begin++, end--){
            result[end] = arr[begin];
        }

        return result;
    }
    private static void shuffleArray(int[] arr){
        Random rand = new Random();
        for (int i = 0; i < arr.length-1; i++) {
            int randIndex = rand.nextInt(i+1);
            int temp = arr[randIndex];
            arr[randIndex] = arr[i];
            arr[i] = temp;
        }
    }
    private static double printMax(double ... numbers){
        // variable arguments
        if (numbers.length<=0) {
            out.println("No Args passed");
        }

        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i]>result){
                result = numbers[i];
            }
        }
        out.println("Max: " + result);

        return result;
    }
    private static void printPatternInstanceOf(Object o){
        // if-else printPatternInstanceOf
        if (o instanceof String S){
            out.println("This is String of lenght " + S.length());
        } else if (o instanceof Character C) {
            out.println("This is Character " + C);
        }
        if (o instanceof String S && !S.isEmpty()){
            out.println("String is not empty. Lenght is " + S.length());
        }

        if (o instanceof Integer I){
            out.println("This is Integer " + I);
        } else if (o instanceof Long L){
            out.println("This is Long " + L);
        } else if (o instanceof Float F){
            out.println("This is Float " + F);
        } else if (o instanceof Double D){
            out.println("This is Double " + D);
        }

        if (o instanceof Animal A) {
            out.println("This is " + A.getClass());
        }
        if (o instanceof Mammal M) {
            out.println("This is " + M.getClass());
        }
        if (o instanceof Dog dog) {
            out.println("This is " + dog.getClass());
        }
        if (o instanceof Reptile R) {
            out.println("This is " + R.getClass());
        }
        if (o instanceof Crocodile crocodile) {
            out.println("This is " + crocodile.getClass());
        }

        if (o instanceof Human H) {
            out.println("This is " + H.getClass());
        }
        if (o instanceof Persona P) {
            out.println("This is " + P.getClass());
        }
        if (o instanceof Child Ch) {
            out.println("This is " + Ch.getClass());
        }

        if (o instanceof Vehicle V) {
            out.println("This is " + V.getClass());
        }
        if (o instanceof Truck T) {
            out.println("This is " + T.getClass());
        }
        if (o instanceof Car car) {
            out.println("This is " + car.getClass());
        }

        // switch printPatternInstanceOf(java 16+)
        /*
        record Box(Object o) {}
        record Point(double x, double y) {}
        // record Point(var x, var y) {} // compile time type inference doesn't work!
        String formatted = switch (o){
            case Integer i-> String.format("int %d", i);
            case Long l-> String.format("int %d", l);
            case Double d-> String.format("int %f", d);
            //case String s && !s.isEmpty()-> String.format("int %s", s); // compile error
            case String s when !s.isEmpty()-> String.format("int %s", s);
            case Box(Integer i) -> String.format("Box container int: %d", i);
            case Box(String s) -> String.format("Box container String: %s", s);
            case Box(Object obj) -> String.format("Box container Object: %s", obj.toString());
            default        -> String.format("Object %s", o.toString());
        };
        out.println(formatted);
         */

    }


    static void arrays(){
        double[] arr1 = new double[10]; // empty array
        double[] arr2 = {Math.E, Math.PI, Math.TAU};
        var arr3 = new int[]{0,1,2,3,4,5,6,7,8,9};

        // System.out.printf("arr1[0]: %d", arr1[0]); // local empty array usage, cause runtime exception

        for (int i = 0; i < arr2.length; i++){
            System.out.printf("arr2[%d]: %10.2f | ", i, arr2[i]*100);
        }
        System.out.println("\n------------");
        for (double temp :
                arr2) {
            System.out.print(temp + " | ");
        }
        System.out.println("\n------------");
        printfArray(new int[]{987,456,321,147,852,369,753,951});

        var Rarr3 = reverseArray(arr3);
        printfArray(Rarr3);

        // ----------------------- Arrays -----------------------
        Arrays.fill(arr1, Math.PI);
        for (double temp : arr1) {
            System.out.print(temp + " | ");
        }
        System.out.println("\n------------");


    }
    static void strings(){

    }
    static void collections(){
        /*
        * - Use with wrapper classes!
        * List(Ordered, Repeatable, Index(sequential) access) -> Arraylist, LinkedList, Vector
        * Set(Not Ordered, Not Repeatable, Not Index(sequential) access) -> HashSet, LinkedHashSet, TreeSet(sorts)
        * Queue(FIFO) -> PriorityQueue, LinkedList
        *   Deque(Double ended-Queue) -> ArrayDeque, LinkedList
        * Map(Key<->Value) -> HashMap, LinkedHashMap, TreeMap, Hashtable
        *
        * Not: LinkedList is in the both of List, Queue categories
        *
        *
        *
        * -*-*-* List Interface *-*-*-
        * ArrayList: Hızlı okuma erişimi, dinamik dizi kullanımı.
        * LinkedList: Başta ve sonda hızlı ekleme/çıkarma, çift yönlü liste yapısı.
        * Vector: Senkronize çalışma, thread-safe koleksiyon yapısı.
        *
        * -*-*-* Set Interface *-*-*-
        * HashSet: Tekrarsız ve sırasız depolama, hızlı erişim sağlar.
        * LinkedHashSet: Tekrarsız ve eklenme sırasına göre depolama sağlar.
        * TreeSet: Tekrarsız ve sıralı depolama sağlar, elemanları doğal sıralama veya özel bir sıralama ile depolar.
        *
        * -*-*-* FIFO(Queue, Deque) Interface *-*-*-
        * LinkedList (Queue olarak): FIFO prensibiyle çalışır, baştan eleman ekleme/çıkarma gibi klasik kuyruk işlevlerini sağlar.
        * PriorityQueue: Elemanları doğal sıralama veya belirli bir önceliğe göre sıralar.
        * Deque (ArrayDeque ile): Çift yönlü kuyruk yapısıdır; hem başa hem de sona eleman ekleme/çıkarma işlemleri için kullanılabilir.
        *
        * -*-*-* Map Interface *-*-*-
        * List or Pairs
        * HashMap: Anahtar-değer çiftlerini sırasız depolar, hızlı erişim sağlar.
        * LinkedHashMap: Elemanları eklenme sırasına göre depolar, sıralı erişim sağlar.
        * TreeMap: Elemanları anahtar sırasına göre sıralar ve hızlı sıralı erişim sunar.
        *
        *
        * Veri Yapısı	                                                            Ekleme Sırasını Korur	Sıralama	Duplikasyona İzin Verir	Hashing Mekanizması	Thread-Safe
          List (ArrayList, LinkedList)	                                            Evet	                Hayır	Evet	Hayır	Hayır
          Set (HashSet, LinkedHashSet, TreeSet)	LinkedHashSet:                      Evet; Diğerleri: Hayır	TreeSet: Evet	Hayır	HashSet, LinkedHashSet: Evet	Hayır
          Queue (LinkedList, PriorityQueue, ArrayDeque)	LinkedList, PriorityQueue:  Evet	PriorityQueue: Evet	Evet	Hayır	Hayır
          Map (HashMap, LinkedHashMap, TreeMap, Hashtable)	LinkedHashMap:          Evet; Diğerleri: Hayır	TreeMap: Evet	Yalnızca benzersiz anahtarlar	HashMap, LinkedHashMap: Evet	Hashtable: Evet; Diğerleri: Hayır

        *
        *
        * * -*-*-* Common methods *-*-*-
        * add(): Eleman ekler.
        * remove(): Belirtilen elemanı veya indeksi siler.
        * contains(): Elemanın var olup olmadığını kontrol eder.
        * size(): Koleksiyonun boyutunu döndürür.
        * iterator(): Koleksiyon üzerinde gezinme işlemi sağlar.
        *
        * -*-*-* Queue, Deque methods *-*-*-
        * peek(): get first element but doesn't remove
        * poll(): get first element and removes
        */

        out.println("-*-*-*-*-* LIST INTERFACE *-*-*-*-*-");
        // List(Ordered, Repeatable, Index(sequential) access) -> Arraylist, LinkedList, Vector
        out.println("-*-*-*-*-* ArrayList *-*-*-*-*-");
        {
            /*
            * - Dynamic array
            * - Fast read, Slow write
            */
            ArrayList<String> animals = new ArrayList<>();
            animals.add("Cat");
            animals.add("Elephant");
            animals.add("Cat");
            animals.add("Dog");
            animals.add("Bird");
            out.println("First element: " + animals.getFirst()); // animals.get(0)
            out.println("Son element: " + animals.getLast()); // animals.get(animals.size()-1)
            out.println("Third element: " + animals.get(2));
            animals.set(1, "Crocodile");
            out.println("toArray: " + Arrays.toString(animals.toArray()));
            animals.remove(3);
            animals.removeLast(); // animals.remove(animals.getLast());
            animals.ensureCapacity(animals.size() + 3); // increase capacity(allocated memory), not necessary
            var animals2 = new ArrayList<String>(){};
            animals2.add("Lion");
            animals2.add("Zebra");
            animals2.add("Snake");
            animals.addAll(animals2);
            out.print("animal2: ["); animals2.forEach(str->out.print(str + ',')); out.println("]");
            out.print("animal: ["); animals.forEach(str->out.print(str + ',')); out.println("]"); // animals.forEach(out::print);

            out.println("indexOf(\"Crocodile\"): " + animals.indexOf("Crocodile"));
            out.println("contains(\"Crocodile\"): " + animals.contains("Crocodile"));
            out.println("contains(\"Crocodile\"): " + animals.contains("asdfıhfdsagojkl"));
            out.println("isEmpty: " + animals.isEmpty());
            out.println("size: " + animals.size());
            out.print("subList: ["); animals.subList(1, 4).forEach(str->out.print(str + ',')); out.println("]");
            animals.removeIf(str -> str.equals("Cat"));
            out.print("removeIf: ["); animals.forEach(str->out.print(str + ',')); out.println("]");
            out.print("reversed: ["); animals.reversed().forEach(str->out.print(str + ',')); out.println("]"); // returns a new ArrayList
            out.println("animals: " + animals); // simple way to print all collection.
            animals.trimToSize(); // capacity(allocated memory)==size(number of elements)
        }

        out.println("\n-*-*-*-*-* LinkedList *-*-*-*-*-");
        {
            /*
            * - multiple use cases: List, Queue, Deque
            * - Double ended-Queue
            * - Slow read, Fast write
            */
            // List
            LinkedList<String> animals = new LinkedList<>();
            animals.add("Cat");
            animals.add("Elephant");
            animals.add("Cat");
            animals.add("Dog");
            animals.add("Bird");
            animals.addFirst("Elephant");
            animals.addLast("Lion");
            out.println("animals: " + animals);
            animals.removeFirst();
            animals.removeLast();
            animals.removeIf(string -> string.equals("Cat"));
            out.println("animals: " + animals);
            out.println("isEmpty: " + animals.isEmpty());
            out.println("size: " + animals.size());
            out.println("Second element: " + animals.get(1));

            // FIFO(Queue)
            Queue<String> documents = new LinkedList<>();
            documents.offer("qqq");
            documents.offer("www");
            documents.offer("www");
            documents.offer("eee");
            documents.offer("eee");
            out.println("documents: " + documents);
            out.println("peek: " + documents.peek()); // get First element
            out.println("pop: " + documents.poll()); // get out a here First element
            out.println("pop: " + documents.poll()); // get out a here First element
            out.println("documents: " + documents);

            // FIFO(Deque)
            Deque<String> book = new LinkedList<>();
            book.add("Page 0");
            book.add("Page 1");
            book.add("Page 2");
            book.add("Page 3");
            out.println("book: " + book);
            out.println("peek: " + book.peek()); // get First element
            out.println("pop: " + book.poll()); // get out a here First element
            out.println("pop: " + book.poll()); // get out a here First element
            out.println("book: " + book);

        }

        out.println("\n-*-*-*-*-* Vector *-*-*-*-*-");
        {
            /*
            * - Thread-safe version of ArrayList
            * - Dynamic array
            * - Fast read, Slow write
            */

            var animals = new Vector<String>();
            animals.add("Cat");
            animals.add("Elephant");
            animals.add("Cat");
            animals.add("Dog");
            animals.add("Bird");
            out.println("First element: " + animals.getFirst()); // animals.get(0)
            out.println("Son element: " + animals.getLast()); // animals.get(animals.size()-1)
            out.println("Third element: " + animals.get(2));
            animals.set(1, "Crocodile");
            out.println("toArray: " + Arrays.toString(animals.toArray()));
            animals.remove(3);
            animals.removeLast(); // animals.remove(animals.getLast());
            animals.ensureCapacity(animals.size() + 3); // increase capacity(allocated memory), not necessary
            var animals2 = new Vector<String>(){};
            animals2.add("Lion");
            animals2.add("Zebra");
            animals2.add("Snake");
            animals.addAll(animals2);
            out.print("animal2: ["); animals2.forEach(str->out.print(str + ',')); out.println("]");
            out.print("animal: ["); animals.forEach(str->out.print(str + ',')); out.println("]"); // animals.forEach(out::print);

            out.println("indexOf(\"Crocodile\"): " + animals.indexOf("Crocodile"));
            out.println("contains(\"Crocodile\"): " + animals.contains("Crocodile"));
            out.println("contains(\"Crocodile\"): " + animals.contains("asdfıhfdsagojkl"));
            out.println("isEmpty: " + animals.isEmpty());
            out.println("size: " + animals.size());
            out.print("subList: ["); animals.subList(1, 4).forEach(str->out.print(str + ',')); out.println("]");
            animals.removeIf(str -> str.equals("Cat"));
            out.print("removeIf: ["); animals.forEach(str->out.print(str + ',')); out.println("]");
            out.print("reversed: ["); animals.reversed().forEach(str->out.print(str + ',')); out.println("]"); // returns a new ArrayList
            out.println("animals: " + animals); // simple way to print all collection.
            animals.trimToSize(); // capacity(allocated memory)==size(number of elements)

        }

        out.println("\n-*-*-*-*-* SET INTERFACE *-*-*-*-*-");
        // Set(Not Ordered, Not Repeatable, Not Index(sequential) access) -> HashSet, LinkedHashSet, TreeSet(sorts)
        out.println("-*-*-*-*-* HashSet *-*-*-*-*-");
        {
            /*
            * - Not repeated, doesn't preserve insertion order or doesn't sort
            * - Fast read, Slow write
            */
            var colors = new HashSet<String>();
            colors.add("Red");
            colors.add("Red");
            colors.add("Green");
            colors.add("Green");
            colors.add("Blue");
            colors.add("Blue");
            colors.add("Yellow");
            colors.add("Orange");

            out.println("colors: " + colors);
            colors.remove("Blue");
            out.println("contains: " + colors.contains("Red"));
            out.println("size: " + colors.size());
            out.println("isEmpty: " + colors.isEmpty());
//            colors.forEach(out::printf);
            out.println("colors: " + colors);
        }
        out.println("\n-*-*-*-*-* LinkedHashSet *-*-*-*-*-");
        {
            /*
            * - Not repeated, preserves insertion order
            * - Fast read, Slow write
            */
            var colors = new LinkedHashSet<String>();
            colors.add("Red");
            colors.add("Red");
            colors.add("Green");
            colors.add("Green");
            colors.add("Blue");
            colors.add("Blue");
            colors.add("Yellow");
            colors.add("Orange");

            out.println("colors: " + colors);
            colors.remove("Blue");
            out.println("contains: " + colors.contains("Red"));
            out.println("size: " + colors.size());
            out.println("isEmpty: " + colors.isEmpty());
//            colors.forEach(out::printf);
            out.println("colors: " + colors);

        }
        out.println("\n-*-*-*-*-* TreeSet *-*-*-*-*-");
        {
            /*
            * - Not repeated, Sorts
            * - Fast read, Slow write
            * - Uses NavigableSet interface.
            * - Sort can be changed by Comparator callback
            */
            var colors = new TreeSet<String>();
            colors.add("Red");
            colors.add("Red");
            colors.add("Green");
            colors.add("Green");
            colors.add("Blue");
            colors.add("Blue");
            colors.add("Yellow");
            colors.add("Orange");

            out.println("colors: " + colors);
            colors.remove("Blue");
            out.println("contains: " + colors.contains("Red"));
            out.println("size: " + colors.size());
            out.println("isEmpty: " + colors.isEmpty());
            out.println("higher: " + colors.higher("Orange")); // Red
            out.println("lower: " + colors.lower("Orange")); // Green
//            colors.forEach(out::printf);
            out.println("colors: " + colors);

        }

        out.println("-*-*-*-*-* Queue INTERFACE *-*-*-*-*-");
        // Queue(FIFO) -> PriorityQueue, LinkedList
        out.println("-*-*-*-*-* PriorityQueue *-*-*-*-*-");
        {
            /*
            * - repeated, sorts
            * - Fast read, Slow write
            * - Sort can be changed by Comparator callback
            */
            var priorityQueue = new PriorityQueue<Integer>();
            priorityQueue.add(50);
            priorityQueue.add(50);
            priorityQueue.add(30);
            priorityQueue.add(90);
            priorityQueue.add(80);
            priorityQueue.add(70);
            priorityQueue.add(75);
            priorityQueue.add(75);
            priorityQueue.add(85);
            priorityQueue.add(85);

            out.println("priorityQueue: " + priorityQueue);

            out.println("peek: " + priorityQueue.peek());
            out.println("remove: " + priorityQueue.remove());
            out.println("priorityQueue: " + priorityQueue);
            out.println("poll: " + priorityQueue.poll());

            out.println("priorityQueue: " + priorityQueue);
        }

        out.println("-*-*-*-*-* ArrayDeque *-*-*-*-*-");
        {
            /*
            * - Double-ended queue
            * - repeated, sorts
            * - Fast read, Slow write
            * - Sort can be changed by Comparator callback
            */
            var book = new ArrayDeque<String>();
            book.add("Page 0");
            book.add("Page 1");
            book.add("Page 2");
            book.add("Page 3");
            book.addLast("Page 4");

            out.println("book: " + book);

            out.println("peekFirst: " + book.peekFirst());
            out.println("peekLast: " + book.peekLast());
            out.println("removeFirst: " + book.removeFirst());
            out.println("removeLast: " + book.removeLast());

            out.println("book: " + book);
        }
        out.println("-*-*-*-*-* Map INTERFACE *-*-*-*-*-");
        // Map(Key<->Value) -> HashMap, LinkedHashMap, TreeMap, Hashtable
        out.println("\n-*-*-*-*-* HashMap *-*-*-*-*-");
        {
            /*
            * - Not repeated(key-overrides), Not sorts
            * - Fast read, Slow write
            */
            var scores = new HashMap<String, Integer>();
            scores.put("Ali", 90);
            scores.put("Bilal", 85);
            scores.put("Cemal", 70);

            out.println("scores: " + scores);
            out.println("keySet: " + scores.keySet());
            out.println("values: " + scores.values());

            out.println("get(\"Cemal\"): " + scores.get("Cemal"));
            out.println("isEmpty: " + scores.isEmpty());
            out.println("containsKey(\"Ali\"): " + scores.containsKey("Ali"));
            out.println("containsKey(\"qqq\"): " + scores.containsKey("qqq"));
            out.println("containsValue(90): " + scores.containsValue(90));
            out.println("containsValue(10): " + scores.containsValue(10));

            scores.forEach((key, value) -> out.print(key + ':' + value + ", ")); out.println();
        }
        out.println("-*-*-*-*-* LinkedHashMap *-*-*-*-*-");
        {
            /*
            * - Not repeated(key-overrides), preseves insertion order
            * - Fast read, Slow write
            */
            var capitals = new LinkedHashMap<String, String>();

            // put() - Anahtar-değer çifti ekleme
            capitals.put("USA", "Washington D.C.");
            capitals.put("USA", "Washington D.C.");
            capitals.put("France", "Paris");
            capitals.put("France", "Paris");
            capitals.put("Japan", "Tokyo");
            capitals.put("Japan", "Tokyo");

            // Elemanlar eklenme sırasına göre depolanır
            System.out.println("LinkedHashMap: " + capitals); // Output: {USA=Washington D.C., France=Paris, Japan=Tokyo}

            // get() - Anahtara göre değeri alma
            System.out.println("Capital of Japan: " + capitals.get("Japan")); // Output: Tokyo

            // replace() - Belirli bir anahtarın değerini değiştirme
            capitals.replace("France", "Lyon");
            System.out.println("Updated LinkedHashMap: " + capitals); // Output: {USA=Washington D.C., France=Lyon, Japan=Tokyo}

            // entrySet() - Tüm anahtar-değer çiftlerini alma
            for (Map.Entry<String, String> entry : capitals.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }


        }
        out.println("\n-*-*-*-*-* TreeMap *-*-*-*-*-");
        {
            /*
            * - Not repeated(key-overrides), sorts by natural order
            * - Fast read, Slow write
            * - Sort can be changed by Comparator callback
            */
            var productPrices = new TreeMap<String, Double>();

            // put() - Anahtar-değer çifti ekleme
            productPrices.put("Laptop", 999.99);
            productPrices.put("Laptop", 999.99);
            productPrices.put("Phone", 799.99);
            productPrices.put("Phone", 799.99);
            productPrices.put("Tablet", 499.99);
            productPrices.put("Tablet", 499.99);

            // Elemanlar doğal sıralamaya göre depolanır (alfabetik sıraya göre)
            System.out.println("TreeMap: " + productPrices); // Output: {Laptop=999.99, Phone=799.99, Tablet=499.99}

            // firstKey() - İlk anahtarı alma
            System.out.println("First key: " + productPrices.firstKey()); // Output: Laptop

            // lastKey() - Son anahtarı alma
            System.out.println("Last key: " + productPrices.lastKey()); // Output: Tablet

            // higherKey() - Belirtilen anahtardan büyük olan ilk anahtarı alma
            System.out.println("Higher key than 'Laptop': " + productPrices.higherKey("Laptop")); // Output: Phone

            // lowerKey() - Belirtilen anahtardan küçük olan ilk anahtarı alma
            System.out.println("Lower key than 'Tablet': " + productPrices.lowerKey("Tablet")); // Output: Phone

            // NavigableMap alt metodları
            System.out.println("Descending map: " + productPrices.descendingMap()); // Output: ters sırayla ürünler
        }
        out.println("-*-*-*-*-* Hashtable *-*-*-*-*-");
        {
            /*
            * - Thread-safe version of HashMap and doesn't accept "null" as key or value
            * - doesn't faster than HashMap but safer.
            * - There is modern version: ConcurrentHashMap
            * - Not repeated(key-overrides), Not sorts
            * - Fast read, Slow write
            */

            var hashtable = new Hashtable<String, Integer>();

            // put() - Anahtar-değer çifti ekleme
            hashtable.put("Apple", 50);
            hashtable.put("Banana", 20);
            hashtable.put("Cherry", 30);

            // get() - Anahtara göre değeri alma
            System.out.println("Price of Apple: " + hashtable.get("Apple")); // Output: 50

            // containsKey() - Anahtarın var olup olmadığını kontrol etme
            System.out.println("Contains 'Banana'? " + hashtable.containsKey("Banana")); // Output: true

            // containsValue() - Belirli bir değerin var olup olmadığını kontrol etme
            System.out.println("Contains value 30? " + hashtable.containsValue(30)); // Output: true

            // remove() - Anahtara göre elemanı çıkarma
            hashtable.remove("Cherry");
            System.out.println("Hashtable after removal: " + hashtable); // Output: {Apple=50, Banana=20}

            // size() - Hashtable boyutunu öğrenme
            System.out.println("Hashtable size: " + hashtable.size()); // Output: 2

            // forEach - Tüm anahtar-değer çiftleri üzerinde gezinme
            hashtable.forEach((key, value) -> System.out.println(key + ": " + value));
        }






    }
    static void iterators(){

    }
    static void files(){

    }
    static void exceptions(){

    }

    static void scannerTest1(){
        Scanner in = new Scanner(System.in);
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
    }
    static void scannerTest2(){
        Scanner in = new Scanner(System.in);
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

        out.println("Mean: " + mean);
        out.println("Count: " + count);
    }
    static void howJvmWorks() {
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = s2;
        out.println("s1: " + s1);
        out.println("s1.toString(): " + s1.toString());
        out.println("s1.equals(s2): " + s1.equals(s2));
        out.println("s2.equals(s3): " + s2.equals(s3));
        out.println("s1.hashCode(): " + s1.hashCode());
        out.println("s1.getClass(): " + s1.getClass());

        Class c1 = s1.getClass();
        out.println("c1: " + c1);
        out.println("c1.getClass(): " + c1.getClass());
        out.println("c1.getName(): " + c1.getName());
        out.println("---------------- Method[] m = c1.getMethods(); ----------------");
        Method[] m = c1.getMethods();
        Arrays.stream(m).forEach(out::println);
        //Arrays.stream(m).map(Method::getName).forEach(out::println); // only my the name
        out.println("---------------- Method[] md = c1.getDeclaredMethods(); ----------------");
        Method[] md = c1.getDeclaredMethods();
        Arrays.stream(md).forEach(out::println);
        //Arrays.stream(md).map(Method::getName).forEach(out::println); // only my the name
        out.println("---------------- Field[] f = c1.getFields(); ----------------");
        Field[] f = c1.getFields();
        Arrays.stream(f).map(Field::getName).forEach(out::println);
        out.println("---------------- Field[] fd = c1.getDeclaredFields(); ----------------");
        Field[] fd = c1.getDeclaredFields();
        Arrays.stream(fd).map(Field::getName).forEach(out::println);

        out.println("---------------- Class loaders ----------------");

        out.println("String.class.getClassLoader(): " + String.class.getClassLoader());
        out.println("Main.class.getClassLoader(): " + Main.class.getClassLoader());
        out.println("Student.class.getClassLoader(): " + Student.class.getClassLoader());
    }
    static void datetimeTest(){
        out.println("-*-*-*-*-* datetimeTest() *-*-*-*-*-");
        // year, month, day,
        Date date = new Date();
        out.println("date: " + date);
        Instant instant = date.toInstant();
        out.println("instant: " + instant);

        Date date2 = new Date(1970,1, 1);
        Date date3 = new Date(1970, Calendar.FEBRUARY, 2);

        out.println(date); // .toString()
        out.println(date.toString());
        out.println();
        out.println("date2.getTime(): " + date2.getTime()); // ms
        out.println("date2.after(date3): " + date2.after(date3));
        out.println("date3.after(date2): " + date3.after(date2));
        out.println("date2.before(date3): " + date2.before(date3));
        out.println("date3.before(date2): " + date3.before(date2));
        out.println("date3.equals(date2): " + date3.equals(date2));

        Date date4 = new Date();
        out.println("date4: " + date4);
        date4.setTime(0); // ms
        out.println("date4.setTime(0): " + date4);
        date4.setTime(-10); // ms
        out.println("date4.setTime(-10): " + date4);
        date4.setTime(date2.getTime()); // ms
        out.println("date4.setTime(date2.getTime()): " + date4);

        // date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("G dd.MM.yyyy");
        out.println("dateFormat.format(date4): " + dateFormat.format(date4));

        out.print("\nPlease Enter a date(dd-MM-yyyy): ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        SimpleDateFormat dateFormatInput = new SimpleDateFormat("dd-MM-yyyy");

        try {
            out.println("dateFormatInput.parse(input): " + dateFormatInput.parse(input));
        } catch (ParseException e) {
            out.println("Unparseable using " + dateFormatInput);
        }

        // pause the thread
        try {
            long start = System.currentTimeMillis();
            out.println(new Date());
            sleep(1000);
            long end = System.currentTimeMillis();
            out.println(new Date());
            long diff = end - start;
            out.println("Difference is: " + diff);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    static void arraysClass(){
        int[] intArr = {10, 15, 20, 25, 30, 25, 20, 15, 10};
        out.println("intArr: " + intArr);
        out.println("Arrays.asList(intArr): " + Arrays.asList(intArr));
        out.println("List.of(intArr): " + List.of(intArr));
        // sort methods
        IntStream sorted = Arrays.stream(intArr).sorted();
        out.println("Arrays.toString(sorted.toArray()): " + Arrays.toString(sorted.toArray()));
        Arrays.sort(intArr);
        out.println("Arrays.toString(intArr): " + Arrays.toString(intArr));

        // search
        int keysearch = 25;
        out.println(keysearch + " found at index = " + Arrays.binarySearch(intArr, 1, 3, keysearch));
        out.println(keysearch + " found at index = " + Arrays.binarySearch(intArr, keysearch));

        int[] intArr1 = intArr.clone();
        int[] intArr2 = {10,15,20};
        out.println("Integer Arrays on comparion: " + Arrays.compare(intArr, intArr1));
        out.println("Integer Arrays on comparion: " + Arrays.compare(intArr, intArr2));
        // mismatch
        int result1 = Arrays.mismatch(intArr, intArr1);
        int result2 = Arrays.mismatch(intArr, intArr2);
        out.println(result1 < 0 ? "No Mismatch" : "Integer Arrays on mismatch index of: " + result1);
        out.println(result2 < 0 ? "No Mismatch" : "Integer Arrays on mismatch index of: " + result2);

        Arrays.fill(intArr1, 0);
        out.println("Arrays.fill(intArr1, 0): " + Arrays.toString(intArr1));
        // shuffle methods
        Integer[] numbers = Arrays.stream(intArr)             // IntStream
                                        .boxed()              // Box each int to Integer
                                        .toArray(Integer[]::new);
         int[] primitiveArray = Arrays.stream(numbers)  // Stream<Integer>
                                      .mapToInt(Integer::intValue)  // Unbox to int
                                      .toArray();
        // Converting the array to a list
        List<Integer> numberList = Arrays.asList(numbers);
        Collections.shuffle(numberList);
        numbers = numberList.toArray(new Integer[0]);
        out.println("Collections.shuffle(Arrays.asList(intArr)): " + Arrays.toString(numbers));
        // shuffleArray
        shuffleArray(intArr);
        out.println("shuffleArray(intArr): " + Arrays.toString(intArr));

        // spliterator
        out.println("Arrays.spliterator(intArr).toString(): " + Arrays.spliterator(intArr).toString());

        // parallel algorithms
        Arrays.parallelSort(intArr);
        out.println("Arrays.parallelSort(intArr): " + Arrays.toString(intArr));

        // fill with random
        int[] arr1 = new int[10];
        int min=5, max=10;
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (int) (Math.random()*(max-min) + min);
        }
        out.println("arr: " + Arrays.toString(arr1));

        int[] arr2 = new int[10];
        Random rand = new Random();
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = rand.nextInt(5,10);
        }
        out.println("arr: " + Arrays.toString(arr1));

    }
    static void patternMathing(){
        String s = "ABC";
        Character character = s.toCharArray()[0];
        Integer I = 1;

        Animal a= new Animal();
        Mammal m = new Mammal();
        Dog dog = new Dog();
        Reptile r = new Reptile();
        Crocodile crocodile = new Crocodile();

        Human h = new Human();
        Persona p = new Persona();
        Child ch = new Child();

        Vehicle v = new Vehicle();
        Truck t = new Truck();
        Car car = new Car();

        printPatternInstanceOf(s);
        printPatternInstanceOf(character);
        printPatternInstanceOf(I);

        printPatternInstanceOf(a);
        printPatternInstanceOf(m);
        printPatternInstanceOf(dog);
        printPatternInstanceOf(r);
        printPatternInstanceOf(crocodile);

        printPatternInstanceOf(h);
        printPatternInstanceOf(p);
        printPatternInstanceOf(ch);

        printPatternInstanceOf(v);
        printPatternInstanceOf(t);
        printPatternInstanceOf(car);
    }
    static void boxingBenchmark(){
        // primitive data types
        long start = System.currentTimeMillis();
        for (long i = 0L; i < Integer.MAX_VALUE* 10L; i++);
        long end = System.currentTimeMillis();
        long duration = end - start;
        out.printf("unboxed version duration: %d ms\n", duration);

        // boxed version of primitive data types
        start = System.currentTimeMillis();
        for (Integer i = 0; i < Integer.MAX_VALUE; i++);
        end = System.currentTimeMillis();
        duration = end - start;
        out.printf("boxed version duration: %d ms\n", duration);
    }
    static void wrapperClasses(){
        boolean isEmpty = true;
        Boolean isEmptyWrapper = isEmpty;
        Boolean b1 = Boolean.TRUE;
        Boolean b2 = Boolean.valueOf(false);
        Boolean b3 = Boolean.valueOf("true");
        out.println(b1.compareTo(b2)); // 0-> equal | 1-> b1 is true, b2 is false | -1-> b1 is false, b2 is true

        // parse data type from string
        out.println(Integer.parseInt("123456"));
        out.println(Long.parseLong("123456"));
        out.println(Double.parseDouble("123.456"));
        out.println(Boolean.parseBoolean("False"));
        out.println(Boolean.parseBoolean("123.456"));
        out.println(Boolean.parseBoolean(""));
        out.println(Boolean.parseBoolean(""));
        out.println(Integer.min(1,2));
        out.println(Float.max(0.1f,0.2f));

        // unboxing
        Byte b = 127;
        out.println("Byte: " + b.byteValue());

        // Character
        int cp=0x10000-1;
        out.println(Character.charCount(cp));

        char c = 'c';
        out.println("getType: " + Character.getType(c));
        out.println("isDigit: " + Character.isDigit(c));
        out.println("isWhitespace: " + Character.isWhitespace(c));
        out.println("isAlphabetic: " + Character.isAlphabetic(c));
        out.println("reverseBytes: " + Character.reverseBytes(c));
        out.println("isLowerCase: " + Character.isLowerCase(c));
        out.println("isUpperCase: " + Character.isUpperCase(c));
        out.println("getNumericValue: " + Character.getNumericValue(c));
    }

    public static void main(String[] args) {
//        System.out.printf("NotInitalizedGlobalVariable: %d\n", NotInitalizedGlobalVariable);
//        System.out.printf("NotInitalizedGlobalArray[0]: %d", NotInitalizedGlobalArray[0]);
//        asciiTablePrint();
//        arrays();
//        strings();
        collections();
        iterators();
//        files();
//        exceptions();
//        CarTester.main(args);
//        EmployeeTester.main(args);
//        ShapeTester.main(args);
//        MyClass.display("MyClass.display(\"\");");

//        scannerTest1();
//        scannerTest2();
//        howJvmWorks();
//        datetimeTest();
//        arraysClass();
//        Main m1 = new Main();
//        Student s = new Student(); s.main(args); s.x=1;
//        main(args); // stackoverflow

//        printMax(new double[]{5,9,6,2,4,89,6,5,4,58,4,8541,85,5,454,85415,8,5,784545});
//        printMax(5,9,6,2,4,89,6,5,4,58,4,8541,85,5,454,85415,8,5,784545);

//        patternMathing();
//        boxingBenchmark();
//        wrapperClasses();


    }
}
