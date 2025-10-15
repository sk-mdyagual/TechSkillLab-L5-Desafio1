package principles.interfaces;
import resources.Empleado;
@FunctionalInterface
public interface reporteNomina {
    String format(Empleado emp);
}