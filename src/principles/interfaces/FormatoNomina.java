package principles.interfaces;
import resources.Empleado;
@FunctionalInterface
public interface FormatoNomina {
    String format(Empleado emp);
}
