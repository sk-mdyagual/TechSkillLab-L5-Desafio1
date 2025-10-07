package principles.interfaces;

import resources.Empleado;

@FunctionalInterface
public interface EmpleadoFormatter {
    String format(Empleado empleado);
}

