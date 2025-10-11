package principles.interfaces;

import resources.Empleado;

@FunctionalInterface
public interface MessageFormatter {
    String formatter(Empleado employee);

    static void printHeader(String format, String... columns) {
        System.out.printf(format, (Object[]) columns);
    }

    static void print(Object s){
        System.out.println(s);
    }
}
