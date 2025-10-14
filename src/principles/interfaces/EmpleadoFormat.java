package principles.interfaces;

import resources.Empleado;

@FunctionalInterface
public interface EmpleadoFormat {
    String format(Empleado e);
}
