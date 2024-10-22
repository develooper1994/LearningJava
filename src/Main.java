import java.util.Arrays;

class Main {
    static int NotInitalizedGlobalVariable;
    // static double[] NotInitalizedGlobalArray = new double[10]; // global empty array usage, cause runtime exception

    static void asciiTablePrint(){
        System.out.println("-*-*-*-*-* asciiTablePrint *-*-*-*-*-");
        for (short i=0;i<256;++i){
            System.out.printf("%d = %c | ", i, (char)i);
            if (i % 16 == 15)
                System.out.println();
        }
    }
    static void printfArray(int[] arr2){
        for (int i = 0; i < arr2.length; i++){
            System.out.printf("arr2[%d]: %d | ", i, arr2[i]);
        }
        System.out.println("\n------------");
    }
    static int[] reverseArray(int[] arr){
        int[] result = new int[arr.length];

        for (int begin=0, end=arr.length-1; begin<arr.length; begin++, end--){
            result[end] = arr[begin];
        }

        return result;
    }
    static void arrays(){
        double[] arr1 = new double[10]; // empty array
        double[] arr2 = {Math.E, Math.PI, Math.TAU};

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

        var arr3 = new int[]{0,1,2,3,4,5,6,7,8,9};
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


    public static void main(String[] args) {
        System.out.printf("NotInitalizedGlobalVariable: %d\n", NotInitalizedGlobalVariable);
        // System.out.printf("NotInitalizedGlobalArray[0]: %d", NotInitalizedGlobalArray[0]);
//        asciiTablePrint();
        arrays();
        strings();
//        CarTester.Main();
//        EmployeeTester.Main();


    }
}
