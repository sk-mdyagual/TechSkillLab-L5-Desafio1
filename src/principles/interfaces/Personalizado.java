package principles.interfaces;
import resources.Empleado;

@FunctionalInterface
public interface Personalizado {
    String format(Empleado empleado);
}