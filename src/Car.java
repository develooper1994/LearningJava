public class Car {
    String model;
    int year;

    public Car(String model, int year){
        this.model = model;
        this.year = year;
    }

    public void start(){
    System.out.printf("%s çalıştırıldı.\n", model);
    }
}


class CarTester{
    public static void Main(){
        Car scoda = new Car("Scoda", 2010);
        Car reuno = new Car("Reuno", 2020);
        scoda.start();
        reuno.start();
    }
}
