import principles.interfaces.EmpleadoFormatter;
import resources.Empleado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Empleado> empleados = new ArrayList<>();
        loadEmpleados(empleados);

        // Interfaz personalizada: EmpleadoFormatter
        System.out.println("----- Formato Ficha Personal -----");
        EmpleadoFormatter fichaPersonalFormatter = empleado -> "Ficha Personal: " + empleado.getNombre() + " " + empleado.getApellido() + ", Cargo: " + empleado.getCargo();
        for (Empleado e : empleados) {
            System.out.println(fichaPersonalFormatter.format(e));
        }

        System.out.println("\n----- Formato Reporte de Nómina -----");
        EmpleadoFormatter nominaFormatter = empleado -> "Reporte de Nómina: " + empleado.getNombre() + " " + empleado.getApellido() + ", Salario: $" + empleado.getSalario();
        for (Empleado e : empleados) {
            System.out.println(nominaFormatter.format(e));
        }

        // Predicate: Filtrar empleados activos con salario menor a 700 USD.
        System.out.println("\n----- Empleados activos con salario < 700 USD (Predicate) -----");
        Predicate<Empleado> salarioPredicate = e -> e.getActive() && e.getSalario().compareTo(new BigDecimal("700")) < 0;
        for (Empleado e : empleados) {
            if (salarioPredicate.test(e)) {
                System.out.println(e);
            }
        }

        // Function: Generar un mapa que almacene como clave un departamento y como valor el supervisor de dicho departamento.
        // Nota: Se asume que el supervisor es el que tiene el cargo "Supervisor/a" en el departamento.
        System.out.println("\n----- Mapa de Departamentos y Supervisores (Function) -----");
        Map<String, Empleado> supervisoresPorDepto = new HashMap<>();
        Function<List<Empleado>, Map<String, Empleado>> obtenerSupervisores = listaEmpleados -> {
            Map<String, Empleado> mapa = new HashMap<>();
            for (Empleado e : listaEmpleados) {
                if (e.getCargo().contains("Supervisor")) {
                    mapa.put(e.getDepartamento(), e);
                }
            }
            return mapa;
        };
        supervisoresPorDepto = obtenerSupervisores.apply(empleados);
        System.out.println(supervisoresPorDepto);


        // Consumer: Imprimir en consola un listado de empleados de un determinado departamento.
        System.out.println("\n----- Empleados de Contabilidad (Consumer) -----");
        Consumer<Empleado> imprimirContabilidad = e -> {
            if ("Contabilidad".equals(e.getDepartamento())) {
                System.out.println(e);
            }
        };
        for (Empleado e : empleados) {
            imprimirContabilidad.accept(e);
        }

        // Comparator: Ordena a los empleados por su apellido en orden alfabético.
        System.out.println("\n----- Empleados ordenados por apellido (Comparator) -----");
        Comparator<Empleado> apellidoComparator = (e1, e2) -> e1.getApellido().compareTo(e2.getApellido());
        empleados.sort(apellidoComparator);
        for (Empleado e : empleados) {
            System.out.print(e);
        }
    }

    public static void loadEmpleados(List<Empleado> empleadoList){
        empleadoList.add(new Empleado("María", "Rodríguez", "F", "Contabilidad", "Asistente Contable", new BigDecimal(700), LocalDate.parse("2021-04-01")));
        empleadoList.add(new Empleado("Juan", "Gutierrez", "M", "Talento Humano", "Reclutador", new BigDecimal(500), LocalDate.parse("2023-03-11"), LocalDate.parse("2024-04-01"), false));
        empleadoList.add(new Empleado("José", "Albornoz", "M","Contabilidad", "Asistente Contable", new BigDecimal(800), LocalDate.parse("2020-08-15"), LocalDate.parse("2023-05-01"), false));
        empleadoList.add(new Empleado("Julián", "Flores", "M", "Informática", "Soporte TI", new BigDecimal(800), LocalDate.parse("2023-11-01")));
        empleadoList.add(new Empleado("Camila", "Mendoza","F", "Informática", "Desarrollador UI/UX", new BigDecimal(1000), LocalDate.parse("2021-07-08")));
        empleadoList.add(new Empleado("Camilo", "López", "M", "Contabilidad", "Supervisor Contable", new BigDecimal(1500), LocalDate.parse("2020-04-11")));
        empleadoList.add(new Empleado("Manuel", "Játiva", "M", "Contabilidad", "Asistente Contable", new BigDecimal(850), LocalDate.parse("2023-06-03")));
        empleadoList.add(new Empleado("Carlos", "Franco", "M", "Talento Humano", "Reclutador", new BigDecimal(650), LocalDate.parse("2023-01-07"), LocalDate.parse("2024-12-09"), false));
        empleadoList.add(new Empleado("Raúl", "Echeverría", "M", "Informática", "Infraestructura TI", new BigDecimal(950), LocalDate.parse("2020-02-14")));
        empleadoList.add(new Empleado("Estefanía", "Mendoza", "F", "Talento Humano", "Supervisora TH", new BigDecimal(1600), LocalDate.parse("2021-09-21")));
        empleadoList.add(new Empleado("Julie", "Flores", "F", "Informática", "Desarrollador", new BigDecimal(1200), LocalDate.parse("2021-12-10")));
        empleadoList.add(new Empleado("Melissa", "Morocho", "F","Contabilidad", "Asistente Contable", new BigDecimal(820), LocalDate.parse("2022-05-22"), LocalDate.parse("2023-07-09"), false));
        empleadoList.add(new Empleado("Camila", "Mendez", "F", "Contabilidad", "Asistente Cuentas", new BigDecimal(860), LocalDate.parse("2020-10-01")));
        empleadoList.add(new Empleado("José", "Rodríguez", "M","Informática", "Tester QA", new BigDecimal(1100), LocalDate.parse("2021-10-01")));
        empleadoList.add(new Empleado("Esteban", "Gutierrez","M", "Talento Humano", "Reclutador", new BigDecimal(700), LocalDate.parse("2023-04-01")));
        empleadoList.add(new Empleado("María", "López","F", "Contabilidad", "Asistente Contable", new BigDecimal(840), LocalDate.parse("2020-02-20"), LocalDate.parse("2024-07-15"), false));
        empleadoList.add(new Empleado("Cecilia", "Marín","F", "Informática", "Supervisora TI", new BigDecimal(2000), LocalDate.parse("2020-04-21")));
        empleadoList.add(new Empleado("Edison", "Cáceres","M", "Informática", "Desarrollador TI", new BigDecimal(1300), LocalDate.parse("2023-07-07")));
        empleadoList.add(new Empleado("María", "Silva", "F","Contabilidad", "Asistente Contable", new BigDecimal(900), LocalDate.parse("2021-11-15"), LocalDate.parse("2022-08-09"), false));
        // Nuevos registros para probar el Predicate
        empleadoList.add(new Empleado("Pedro", "Pascal", "M", "Informática", "Becario", new BigDecimal(600), LocalDate.parse("2024-01-15")));
        empleadoList.add(new Empleado("Ana", "De Armas", "F", "Talento Humano", "Pasante", new BigDecimal(550), LocalDate.parse("2024-02-01")));
    }



}