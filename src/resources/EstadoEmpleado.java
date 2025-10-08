package resources;

public enum EstadoEmpleado {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private final String descripcion;

    EstadoEmpleado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public static EstadoEmpleado fromBoolean(Boolean isActive) {
        return isActive != null && isActive ? ACTIVO : INACTIVO;
    }
}