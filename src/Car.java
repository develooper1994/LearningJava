import static java.lang.System.*;

interface Entity{}
// IS-A relationship
interface Live extends Entity{
    void eat();
    void shit();
    void sleep();
    void makeSound();
    void travel();
}
class Animal implements Live{
    @Override
    public void eat() {

    }

    @Override
    public void shit() {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void makeSound() {

    }

    @Override
    public void travel() {

    }
}
class Mammal extends Animal{}
class Dog extends Mammal{}
class Reptile extends Animal{}
class Crocodile extends Reptile{}
class Human extends Animal{}
class Person extends Human{}
class Child extends Human{}

interface Soulless extends Entity{}
class Speed{}
class Vehicle implements Soulless{
    // HAS-A relationship
    private Speed speed;
}
class Truck extends Vehicle {}
class Car {
    String model;
    int year;
    // initializer block
    {
        model = "Megan";
        year = 2024;
    }

    Car(){}

    public Car(String model, int year){
        this.model = model;
        this.year = year;
    }

    public void start(){
        out.printf("%s çalıştırıldı.\n", model);
    }
   public void engineType() {
      System.out.println("Turbo Engine");
   }

}


class CarTester{
    public static void main(String[] args) {
        Car scoda = new Car("Scoda", 2010);
        Car reuno = new Car("Reuno", 2020);
        scoda.start();
        reuno.start();

        // Anonymous classes
        Live anonymousLife = new Live() {

            @Override
            public void eat() {
                out.println("anonymousLife eat");
            }

            @Override
            public void shit() {
                out.println("anonymousLife shit");
            }

            @Override
            public void sleep() {
                out.println("anonymousLife shit");
            }

            @Override
            public void makeSound() {
                out.println("anonymousLife I am alive");
            }

            @Override
            public void travel() {
                out.println("anonymousLife rap rap rap rap...");
            }
        };
        anonymousLife.eat();

        // Anonymous class override method
        Car car = new Car(){
            final String motortype = "V2 Engine";
             @Override
             public void engineType() {
                 System.out.println("V2 Engine");
                System.out.println(motortype);
             }
        };
        car.engineType();

    }

}
