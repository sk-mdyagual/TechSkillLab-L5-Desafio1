package principles.interfaces;

import resources.Empleado;

@FunctionalInterface
public interface EmpleadoMessage {
    String format(Empleado empleado);

}
