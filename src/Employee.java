class Person{
    protected String name;
    protected int age;

//private:
//protected:
//public:
    Person(){
        this(null, 0);
    }
    public Person(String name, int age){
        this.name=name;
        this.age=age;
    }
    // property.name
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    // property.age
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        if (age>0)
            this.age = age;
        else
            System.err.println("Invalid age!");
    }
    // methods
    public void eat(){
        System.out.printf("name: %s | age: %d | activity: %s\n", name, age, "🍽️");
    }
    public void shit(){
        System.out.printf("name: %s | age: %d | activity: %s\n", name, age, "💩");
    }
    public void sleep(){
        System.out.printf("name: %s | age: %d | activity: %s\n", name, age, "😴");
    }
    public void makeSound(){
        System.out.printf("name: %s | age: %d | sound: \n", name, age, "lalala");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class Employee extends Person {
    //members
    int salary;
    static final String department = "Development";
    private boolean employed;

//private:
//protected:
//public:

    Employee() {
        this(null, 0, 0);
    }
    Employee(int salary) {
        this(null, 0, salary);
    }
    public Employee(String name, int age, int salary) {
        super(name, age);
        if (salary>0) {
            employed=true;
            setSalary(salary);
        }
        else {
            employed=false;
            System.err.println("Not Employed!");
        }
    }

    // property.salaray
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    // property.employed
    public boolean isEmployed() {
        return employed;
    }
    public void work(){
        System.out.printf("name: %s | age: %d | activity: %s\n", name, age,
                isEmployed() ?
                "Working, Working, Working." :
                "Seeking, Seeking, Seeking.");
    }
    @Override
    public void makeSound(){
        System.out.printf("name: %s | age: %d | sound: %s\n", name, age,
                isEmployed() ?
                "My price is high, also my wages are higher than salary":
                "I can do every job whatever you want");
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", salary='" + salary + '\'' +
                ", department='" + department + '\'' +
                ", employed='" + employed +
                '}';
    }
}






// -*-*-*-*-* EmployeeTester *-*-*-*-*-
class EmployeeTester{

    static Person person = new Person("Mustafa", 30);
    static Employee employee = new Employee("Selçuk", 35, 50000);

    private static void PersonTest1(){
        System.out.println("-*-*-*-*-* PersonTest1 *-*-*-*-*-");
        person.eat();
        person.shit();
        person.sleep();
        System.out.println(person.toString());
    }
    private static void EmployeeTest1(){
        System.out.println("-*-*-*-*-* EmployeeTest1 *-*-*-*-*-");
        // employee = (Employee)person; // class downcasting: runtime error
        employee.eat();
        employee.shit();
        employee.sleep();
        employee.work();
        System.out.println(employee.toString());
    }
    private static void EmployeeTest2(){
        System.out.println("-*-*-*-*-* EmployeeTest2 *-*-*-*-*-");
        // class upcasting: working
        //person = (Person)employee;
        person = employee;
        person.eat();
        person.shit();
        person.sleep();
        employee.work();
    }
    public static void Main(){
        PersonTest1();
        EmployeeTest1();
        EmployeeTest2();
    }
}
