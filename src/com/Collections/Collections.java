package com.Collections;

import java.util.*;

import static java.lang.System.out;

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
* 1. Ortak Özellikler
*   Koleksiyon Arayüzlerini Takip Etme: Tüm veri yapıları Java'nın koleksiyon çerçevesine (Java Collections Framework) bağlı arayüzleri uygular. Örneğin, List, Set, ve Queue yapıları Collection arayüzünü uygularken, Map kendi Map arayüzünü kullanır.
*   Generic Destek: Tüm veri yapıları, Java'da Generics özelliğini destekler ve bu sayede koleksiyonda saklanacak veri tipleri belirlenebilir. Örneğin, List<String> yalnızca String tipindeki verileri saklamak için kullanılır.
*   Çoğu Yapıda Iterator Kullanımı: List, Set ve Queue gibi veri yapılarında, veriler üzerinde gezinmek için Iterator arayüzü kullanılabilir. Bu, elemanları sırasıyla işlemek için önemli bir mekanizmadır.
*   Çoğu Yapıda Boş Veri Kontrolü: isEmpty() metodu sayesinde veri yapısının boş olup olmadığını kontrol etmek mümkündür. Bu metot, Collection arayüzünü uygulayan tüm veri yapılarında mevcuttur.
*    Eşzamansızlık (Thread-Safety) Sorunları: Koleksiyon çerçevesindeki çoğu veri yapısı (örneğin ArrayList, HashMap, HashSet), varsayılan olarak thread-safe değildir. Ancak Hashtable gibi bazı yapılar varsayılan olarak thread-safe'tir. Ayrıca Collections.synchronizedList() gibi metotlarla da eşzamanlı erişim sağlanabilir.
*
* 2. İşlem Metotları ve Genel İşlevler
*   Eleman Ekleme ve Çıkarma: Çoğu koleksiyon veri yapısı eleman eklemek için add() ve çıkarmak için remove() metodunu kullanır. Map yapıları ise put() ve remove() metotlarına sahiptir.
*   Elemanlara Erişim: List ve Map veri yapılarında belirli bir anahtar veya indeks ile doğrudan erişim mümkündür (get() metodu). Diğer yapılarda belirli bir sırayla veya anahtar ile erişim sağlanır.
*   Eleman Arama: Tüm veri yapılarında belirli bir elemanın var olup olmadığını kontrol etmek için contains() gibi metotlar bulunur. Map yapıları containsKey() ve containsValue() metodunu sağlar.
*   Koleksiyon Boyutu: size() metodu, koleksiyonların eleman sayısını döner. Bu, veri yapısının kaç eleman içerdiğini hızlıca öğrenmek için kullanılır.
*
* 3. Performans ve Veri Saklama Özellikleri
*   Hızlı Erişim (Hashing): HashMap, HashSet, ve Hashtable yapılarında hashing mekanizması kullanılır. Bu sayede elemanlar anahtarlarla hızlıca saklanır ve erişilir.
*   Sıralı ve Sırasız Saklama: ArrayList ve LinkedList ekleme sırasını korur; HashSet ve HashMap sırasızdır, ancak LinkedHashSet ve LinkedHashMap ekleme sırasını korur. TreeSet ve TreeMap ise elemanları sıralı olarak saklar.
*       Sırasız: HashSet, HashMap
*       Ekleme Sırasını korur: (ArrayList, LinkedList), (LinkedHashSet, LinkedHashMap)
*       Sıralı: TreeSet, TreeMap
*   Duplikasyon Kontrolü: Set yapıları (örneğin HashSet, TreeSet), aynı değere sahip elemanların birden fazla olmasını engeller. List ve Queue yapılarında ise bu kontrol yoktur; aynı eleman birden fazla defa eklenebilir.
*
* 4. Özet Tablo
* <img src="../images/JavaCollections.png" alt="Java Collections"/>
* ![Java Collections](../images/JavaCollections.png)
*
* 5. Pratik Durumlar
*   1. List:  Örneğin, kullanıcıların kayıt sırasını tutmak için
*   2. Set:   Örneğin, bir kullanıcıların benzersiz kimlik numaraları veya kullanıcı adları gibi veriler
*   3. Queue: Örneğin, yazıcı kuyrukları veya görev yönetim sistemlerinde
*   4. Map:   Örneğin, bir kullanıcı adı ile kullanıcı bilgilerini eşleştirmek için
*   5. Thread-Safe Durumlar: ConcurrentHashMap veya Hashtable gibi thread-safe veri yapıları tercih edilir
*   6. Özelleşmiş Durumlar:
*       1. Geçici Veri Depolama: Sadece geçici veri saklanması gerekiyorsa (örneğin, bir işlemin ara sonuçları için), ArrayList veya HashMap kullanımı hızlı ve etkilidir.
*       2. Statik ve Dinamik Veriler: Statik (değişmeyecek) veriler için Arrays.asList() ile oluşturulan sabit bir liste kullanılabilirken, dinamik veriler için ArrayList kullanılmalıdır.
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
public class Collections {

    static void LIST_INTERFACE(){
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
    }
    static void SET_INTERFACE(){
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
    }
    static void QUEUE_INTERFACE(){
        out.println("-*-*-*-*-* QUEUE INTERFACE *-*-*-*-*-");
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
    }
    static void MAP_INTERFACE(){
        out.println("-*-*-*-*-* MAP INTERFACE *-*-*-*-*-");
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

    public static void main(String[] args){
        LIST_INTERFACE();
        SET_INTERFACE();
        QUEUE_INTERFACE();
        MAP_INTERFACE();
    }
}
