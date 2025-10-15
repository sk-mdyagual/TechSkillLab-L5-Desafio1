package principles.interfaces;

import resources.Empleado;

@FunctionalInterface
public interface EmpFormat {
    String format(Empleado emp);
}
