package resources;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.*;

public class DemoEmpleado {
    public static void main(String[] args) {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("María", "Rodríguez", "F", "Contabilidad", "Asistente Contable", new BigDecimal(700), LocalDate.parse("2021-04-01")));
        empleados.add(new Empleado("Juan", "Gutierrez", "M", "Talento Humano", "Reclutador", new BigDecimal(500), LocalDate.parse("2023-03-11"), LocalDate.parse("2024-04-01"), false));
        empleados.add(new Empleado("José", "Albornoz", "M","Contabilidad", "Asistente Contable", new BigDecimal(800), LocalDate.parse("2020-08-15"), LocalDate.parse("2023-05-01"), false));
        empleados.add(new Empleado("Julián", "Flores", "M", "Informática", "Soporte TI", new BigDecimal(800), LocalDate.parse("2023-11-01")));
        empleados.add(new Empleado("Camila", "Mendoza","F", "Informática", "Desarrollador UI/UX", new BigDecimal(1000), LocalDate.parse("2021-07-08")));
        empleados.add(new Empleado("Camilo", "López", "M", "Contabilidad", "Supervisor Contable", new BigDecimal(1500), LocalDate.parse("2020-04-11")));
        empleados.add(new Empleado("Manuel", "Játiva", "M", "Contabilidad", "Asistente Contable", new BigDecimal(850), LocalDate.parse("2023-06-03")));
        empleados.add(new Empleado("Carlos", "Franco", "M", "Talento Humano", "Reclutador", new BigDecimal(650), LocalDate.parse("2023-01-07"), LocalDate.parse("2024-12-09"), false));
        empleados.add(new Empleado("Raúl", "Echeverría", "M", "Informática", "Infraestructura TI", new BigDecimal(950), LocalDate.parse("2020-02-14")));
        empleados.add(new Empleado("Estefanía", "Mendoza", "F", "Talento Humano", "Supervisora TH", new BigDecimal(1600), LocalDate.parse("2021-09-21")));
        empleados.add(new Empleado("Julie", "Flores", "F", "Informática", "Desarrollador", new BigDecimal(1200), LocalDate.parse("2021-12-10")));
        empleados.add(new Empleado("Melissa", "Morocho", "F","Contabilidad", "Asistente Contable", new BigDecimal(820), LocalDate.parse("2022-05-22"), LocalDate.parse("2023-07-09"), false));
        empleados.add(new Empleado("Camila", "Mendez", "F", "Contabilidad", "Asistente Cuentas", new BigDecimal(860), LocalDate.parse("2020-10-01")));
        empleados.add(new Empleado("José", "Rodríguez", "M","Informática", "Tester QA", new BigDecimal(1100), LocalDate.parse("2021-10-01")));
        empleados.add(new Empleado("Esteban", "Gutierrez","M", "Talento Humano", "Reclutador", new BigDecimal(700), LocalDate.parse("2023-04-01")));
        empleados.add(new Empleado("María", "López","F", "Contabilidad", "Asistente Contable", new BigDecimal(840), LocalDate.parse("2020-02-20"), LocalDate.parse("2024-07-15"), false));
        empleados.add(new Empleado("Cecilia", "Marín","F", "Informática", "Supervisora TI", new BigDecimal(2000), LocalDate.parse("2020-04-21")));
        empleados.add(new Empleado("Edison", "Cáceres","M", "Informática", "Desarrollador TI", new BigDecimal(1300), LocalDate.parse("2023-07-07")));
        empleados.add(new Empleado("María", "Silva", "F","Contabilidad", "Asistente Contable", new BigDecimal(900), LocalDate.parse("2021-11-15"), LocalDate.parse("2022-08-09"), false));

        // 1. Dos formatos usando EmpleadoFormatter
        EmpleadoFormatter nominaFormatter = e -> String.format("%s %s | Departamento: %s | Cargo: %s | Salario: $%.2f", e.getNombre(), e.getApellido(), e.getDepartamento(), e.getCargo(), e.getSalario());
        EmpleadoFormatter fichaFormatter = e -> String.format("Nombre: %s %s\nGénero: %s\nDepartamento: %s\nCargo: %s\nIngreso: %s\nActivo: %s", e.getNombre(), e.getApellido(), e.getGenero(), e.getDepartamento(), e.getCargo(), e.getFechaIng(), e.getActive());

        System.out.println("###### Reporte de Nómina ######");
        for (Empleado e : empleados) {
            System.out.println(nominaFormatter.format(e));
        }
        System.out.println("\n###### Ficha Personal ######");
        for (Empleado e : empleados) {
            System.out.println(fichaFormatter.format(e)+"\n");
        }

        // 2. Predicate: empleados activos con salario < 700 USD
        System.out.println("\n###### Empleados activos con salario < 700 USD ######");
        for (Empleado e : empleados) {
            if (e.getActive() && e.getSalario().compareTo(new BigDecimal(700)) > 0) {
                System.out.println(e);
            }
        }

        // 3. Function: mapa departamento-supervisor
        Function<List<Empleado>, Map<String, String>> deptoSupervisor = lista -> {
            Map<String, String> mapa = new HashMap<>();
            for (Empleado e : lista) {
                if (e.getCargo().toLowerCase().contains("supervis")) {
                    mapa.put(e.getDepartamento(), e.getNombre() + " " + e.getApellido());
                }
            }
            return mapa;
        };
        Map<String, String> mapaSupervisores = deptoSupervisor.apply(empleados);
        System.out.println("\n###### Supervisores por departamento ######");
        for (Map.Entry<String, String> entry : mapaSupervisores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 4. Consumer: imprimir empleados de un departamento
        String departamentoBuscado = "Talento Humano";
        Consumer<List<Empleado>> imprimirDepto = lista -> {
            System.out.println("\n###### Empleados del departamento " + departamentoBuscado + " ######");
            for (Empleado e : lista) {
                if (e.getDepartamento().equalsIgnoreCase(departamentoBuscado)) {
                    System.out.println(e);
                }
            }
        };
        imprimirDepto.accept(empleados);

        // 5. Comparator: ordenar por apellido
        empleados.sort(new Comparator<Empleado>() {
            @Override
            public int compare(Empleado e1, Empleado e2) {
                return e1.getApellido().compareToIgnoreCase(e2.getApellido());
            }
        });
        System.out.println("\n###### Empleados ordenados por apellido ######");
        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }
}

