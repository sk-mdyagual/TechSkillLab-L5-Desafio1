package desafio.principales;

import resources.Empleado;

@FunctionalInterface
public interface MessageFormatter {

    String formatter(Empleado empleado);

    static void printHeader(String format, String... columns) {
        System.out.printf(format, (Object[]) columns);
    }

    static void print(Object obj) {
        System.out.println(obj);
    }
}
