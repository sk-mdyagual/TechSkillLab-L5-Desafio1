import principles.interfaces.EmpleadoMessage;
import resources.Empleado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Empleado> empleados = new ArrayList<>();
        loadEmpleados(empleados);

        //To do: Solución taller 1 - Intefaces Funcionales
        System.out.println();
        EmpleadoMessage reporteNomina = empleado -> "Reporte nomina Empleado: " + empleado.getNombre() +
                " " + empleado.getApellido() + ", Cargo: " + empleado.getCargo() +
                ", Salario: " + empleado.getSalario() + " USD";

        EmpleadoMessage fichaPersonal = empleado -> "Ficha persona Empleado: " + empleado.getNombre() +
                " " + empleado.getApellido() + ", Género: " + empleado.getGenero() +
                ", Departamento: " + empleado.getDepartamento() +
                ", Fecha de Ingreso: " + empleado.getFechaIng() +
                (empleado.getActive() ? ", Estado: Activo" : ", Estado: Inactivo, Fecha de Salida: " + empleado.getFechaSal());


        System.out.println("Predicate: Filtrar empleados activos con salario menor a 700 USD");
        System.out.println();
        Predicate<Empleado> filtroSalario = empleado -> empleado.getActive() &&
                empleado.getSalario().compareTo(new BigDecimal(700)) < 0;

        empleados.forEach(empleado -> {
            if (filtroSalario.test(empleado)) {
                System.out.println(reporteNomina.format(empleado) + "\n" +
                        fichaPersonal.format(empleado));
                System.out.println();
            }
        });

        System.out.println();
        System.out.println("Function: Generar un mapa que almacene como clave un departamento y como valor el supervisor de dicho departamento");

        Function<List<Empleado>, Map<String, Empleado>> mapaSupervisores = empleadoList -> {
            Map<String, Empleado> supervisorMap = new HashMap<>();
            empleados.forEach(empleado -> {
                if (empleado.getCargo().toLowerCase().contains("supervisor")) {
                    supervisorMap.put(empleado.getDepartamento(), empleado);
                }
            });
            return supervisorMap;
        };

        Map<String, Empleado> supervisores = mapaSupervisores.apply(empleados);
        supervisores.forEach((departamento, empleado) ->
                System.out.println("Departamento: " + departamento
                        + ", Supervisor: " + empleado.getNombre()
                        + " " + empleado.getApellido()));

        System.out.println();
        System.out.println("Consumer: Imprimir por consola un listado de empleados de un determinado departamento");
        String departamentoBuscado = "Contabilidad";
        System.out.println("Departamento buscado: " + departamentoBuscado);

        Consumer<List<Empleado>> listarEmpleadosDepartamento = empleadoList -> {
            empleadoList.forEach(empleado -> {
                if (empleado.getDepartamento().equalsIgnoreCase(departamentoBuscado)) {
                    System.out.println("Empleado: " + empleado.getNombre()
                            + " " + empleado.getApellido()
                            + ", Cargo: " + empleado.getCargo()
                            + ", Salario: " + empleado.getSalario() + " USD");
                }
            });
        };

        listarEmpleadosDepartamento.accept(empleados);

        System.out.println();
        System.out.println("Comparator: Ordena a los empleados por su apellido en orden alfabetico");

        Comparator<Empleado> ordenarPorApellido = Comparator.comparing(Empleado::getApellido);
        List<Empleado> empleadosPorApellido = new ArrayList<>(empleados);
        empleadosPorApellido.sort(ordenarPorApellido);
        empleadosPorApellido.forEach(empleado -> System.out.println(
                empleado.getApellido() + ", " + empleado.getNombre()
        ));
    }

    public static void loadEmpleados(List<Empleado> empleadoList){
        empleadoList.add(new Empleado("María", "Rodríguez", "F", "Contabilidad", "Asistente Contable", new BigDecimal(700), LocalDate.parse("2021-04-01"), LocalDate.parse("2023-03-01"), true));
        empleadoList.add(new Empleado("Juan", "Gutierrez", "M", "Talento Humano", "Reclutador", new BigDecimal(500), LocalDate.parse("2023-03-11"), LocalDate.parse("2024-04-01"), true));
        empleadoList.add(new Empleado("José", "Albornoz", "M","Contabilidad", "Asistente Contable", new BigDecimal(800), LocalDate.parse("2020-08-15"), LocalDate.parse("2023-05-01"), true));
        empleadoList.add(new Empleado("Julián", "Flores", "M", "Informática", "Soporte TI", new BigDecimal(800), LocalDate.parse("2023-11-01"), LocalDate.parse("2024-11-01"), true));
        empleadoList.add(new Empleado("Camila", "Mendoza","F", "Informática", "Desarrollador UI/UX", new BigDecimal(1000), LocalDate.parse("2021-07-08"), LocalDate.parse("2023-09-01"), true));
        empleadoList.add(new Empleado("Camilo", "López", "M", "Contabilidad", "Supervisor Contable", new BigDecimal(1500), LocalDate.parse("2020-04-11"), LocalDate.parse("2023-06-01"), true));
        empleadoList.add(new Empleado("Manuel", "Játiva", "M", "Contabilidad", "Asistente Contable", new BigDecimal(850), LocalDate.parse("2023-06-03"), LocalDate.parse("2024-06-01"), true));
        empleadoList.add(new Empleado("Carlos", "Franco", "M", "Talento Humano", "Reclutador", new BigDecimal(650), LocalDate.parse("2023-01-07"), LocalDate.parse("2024-12-09"), true));
        empleadoList.add(new Empleado("Raúl", "Echeverría", "M", "Informática", "Infraestructura TI", new BigDecimal(950), LocalDate.parse("2020-02-14"), LocalDate.parse("2023-08-01"), true));
        empleadoList.add(new Empleado("Estefanía", "Mendoza", "F", "Talento Humano", "Supervisora TH", new BigDecimal(1600), LocalDate.parse("2021-09-21"), LocalDate.parse("2023-11-01"), true));
        empleadoList.add(new Empleado("Julie", "Flores", "F", "Informática", "Desarrollador", new BigDecimal(1200), LocalDate.parse("2021-12-10"), LocalDate.parse("2023-10-01"), true));
        empleadoList.add(new Empleado("Melissa", "Morocho", "F","Contabilidad", "Asistente Contable", new BigDecimal(820), LocalDate.parse("2022-05-22"), LocalDate.parse("2023-07-09"), true));
        empleadoList.add(new Empleado("Camila", "Mendez", "F", "Contabilidad", "Asistente Cuentas", new BigDecimal(860), LocalDate.parse("2020-10-01"), LocalDate.parse("2023-12-01"), true));
        empleadoList.add(new Empleado("José", "Rodríguez", "M","Informática", "Tester QA", new BigDecimal(1100), LocalDate.parse("2021-10-01"), LocalDate.parse("2023-12-01"), true));
        empleadoList.add(new Empleado("Esteban", "Gutierrez","M", "Talento Humano", "Reclutador", new BigDecimal(700), LocalDate.parse("2023-04-01"), LocalDate.parse("2024-05-01"), true));
        empleadoList.add(new Empleado("María", "López","F", "Contabilidad", "Asistente Contable", new BigDecimal(840), LocalDate.parse("2020-02-20"), LocalDate.parse("2024-07-15"), true));
        empleadoList.add(new Empleado("Cecilia", "Marín","F", "Informática", "Supervisora TI", new BigDecimal(2000), LocalDate.parse("2020-04-21"), LocalDate.parse("2023-09-01"), true));
        empleadoList.add(new Empleado("Edison", "Cáceres","M", "Informática", "Desarrollador TI", new BigDecimal(1300), LocalDate.parse("2023-07-07"), LocalDate.parse("2024-07-07"), true));
        empleadoList.add(new Empleado("María", "Silva", "F","Contabilidad", "Asistente Contable", new BigDecimal(900), LocalDate.parse("2021-11-15"), LocalDate.parse("2022-08-09"), true));

    }



}