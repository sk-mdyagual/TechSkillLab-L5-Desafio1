package principles.interfaces;

import resources.Empleado;

@FunctionalInterface
public interface Formatter {
    String format(Empleado employee);


    static void printHeader(String formato, String... columnas) {
        System.out.printf(formato, (Object[]) columnas);
    }

    static void print(Object mensaje) {
        System.out.println(mensaje);
    }

}