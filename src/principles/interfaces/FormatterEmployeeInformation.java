package principles.interfaces;

import resources.Empleado;

@FunctionalInterface
public interface FormatterEmployeeInformation {

    String format(Empleado empleado);

    static void show(String s) {
        System.out.println(s);
    }
}
