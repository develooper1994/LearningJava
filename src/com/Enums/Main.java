package com.Enums;

import static java.lang.System.out;

enum Days {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    @Override
    public String toString() {
        return switch (this){
            case MONDAY: yield "Bugün Pazartesi. İş başı";
            case TUESDAY: yield "Bugün Salı";
            case WEDNESDAY: yield "Bugün Çarşamba";
            case THURSDAY: yield "Bugün Perşembe";
            case FRIDAY: yield "Bugün Cuma. Hayırlı Cumalar. Enseyi kapa";
            // java language doesn't have "or" by default
            case SATURDAY:
            case SUNDAY: yield "Hafta Sonu 😉";
        };
    }
}
enum Color{
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
        /*
        public boolean hasAnyWindowProps(){
            return windowProp!=0;
        }
        public boolean hasProperties(WindowProp prop){
            return (windowProp & prop.getWindowProp()) != 0;
        }
         */
    }
    private int properties;

    public boolean hasAnyProperty(){
        return properties !=0;
    }
    public boolean hasProperty(WindowProp prop){
        return (properties & prop.getWindowProp()) != 0;
    }

    public void setWindowProperties(WindowProp... props){
        for (WindowProp prop : props) {
            properties |= prop.getWindowProp();
        }
    }
    public void printProperties(){
        out.println("Window has these properties:");
        WindowProp[] values = WindowProp.values();
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            WindowProp prop = values[i];
            if (hasProperty(prop))
                out.printf("- prop.values[%d]: %s\n", i, prop);
        }
    }
    public static void Tester(){
        Window myWindow = new Window();
        myWindow.setWindowProperties(WindowProp.RESIZABLE, WindowProp.WINDOWLESS, WindowProp.MINIMIZE);

        out.println("MINIMIZE özelliği var mı? " + myWindow.hasProperty(Window.WindowProp.MINIMIZE));
        out.println("FULLSCREEN özelliği var mı? " + myWindow.hasProperty(Window.WindowProp.FULLSCREEN));

        myWindow.printProperties();
    }
}

public class Main {

    static void enums(){
        Days today = Days.FRIDAY;
        switch (today){
            case MONDAY: out.println("Bugün Pazartesi. İş başı\n"); break;
            case TUESDAY: out.println("Bugün Salı.\n"); break;
            case WEDNESDAY: out.println("Bugün Çarşamba.\n"); break;
            case THURSDAY: out.println("Bugün Perşembe.\n"); break;
            case FRIDAY: out.println("Bugün Cuma. Hayırlı Cumalar. Enseyi kapa\n"); break;
            // java language doesn't have "or" by default
            case SATURDAY:
            case SUNDAY: out.println("Hafta Sonu 😉\n"); break;
            default: out.println("Böyle bir gün yok\n"); break;
        }

        for (var d : Days.values()) {
            out.printf("%s - Sıra: %d - Adı: %s\n", d, d.ordinal(), d.name()); // "d" calls "d.toString()"
        }

        Color color = Color.RED;
        out.println("\n" + color.getHexCode());

        Window.Tester();
    }

    public static void main(String[] args){
        enums();
    }
}
