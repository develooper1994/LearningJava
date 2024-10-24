import static java.lang.System.out;

abstract class Shape {
    abstract void draw();
    public void start(){
        out.println("Starting to drawing...");
    }
}

class Square extends Shape {
    @Override
    void draw() {
        out.println("▢");
    }

    public void roll(){
        out.println("♢▢♢▢♢▢♢▢♢▢♢▢");
    }
}

class ShapeTester{
    public static void main(String[] args) {
        // Shape shape = new Shape(); // compile error
        Square square1 = new Square();
        Shape square2 = new Square();

        square1.draw();
        square1.roll();

        square2.draw();
        // square2.roll(); // compile error
    }
}
