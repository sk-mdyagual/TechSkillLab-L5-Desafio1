package principles;

import principles.interfaces.Formatter;
import resources.Empleado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class TareaUno {
    public static void main(String[] args) {

        List<Empleado> empleados = new ArrayList<>();
        loadEmpleados(empleados);

        Formatter formatoFicha = (e) -> String.format(
                "Nombre: %s %s%nGénero: %s%nDepartamento: %s%nCargo: %s%nSalario: $%s%nFecha de Ingreso: %s%nFecha de Salida: %s%nEstado: %s%n",
                e.getNombre(), e.getApellido(), e.getGenero(), e.getDepartamento(), e.getCargo(), e.getSalario(),
                e.getFechaIng(), (e.getFechaSal() != null ? e.getFechaSal() : "N/A"),
                e.getActive() ? "Activo" : "Inactivo");


        Formatter formatoNomina = (e) -> String.format("%-10s %-12s %-20s %-25s $%-10s%n",
                e.getNombre(), e.getApellido(), e.getDepartamento(), e.getCargo(), e.getSalario());

        Formatter.printHeader("%-10s %-12s %-20s %-25s %-10s%n", "Nombre", "Apellido", "Departamento", "Cargo", "Salario");
        for (Empleado e : empleados) {
            Formatter.print(formatoNomina.format(e));
        }




        for (Empleado e : empleados) {
            Formatter.print(formatoFicha.format(e));
        }


        List<Empleado> empleadosFiltrados = new ArrayList<>();
        Predicate<Empleado> criterioSalario = e -> e.getActive() && e.getSalario().compareTo(new BigDecimal(700)) < 0;

        for (Empleado e : empleados) {
            if (criterioSalario.test(e)) {
                empleadosFiltrados.add(e);
            }
        }

        Formatter.printHeader("%-10s %-12s %-20s %-25s %-10s%n", "Nombre", "Apellido", "Departamento", "Cargo", "Salario");
        for (Empleado e : empleadosFiltrados) {
            Formatter.print(formatoNomina.format(e));
        }

        Function<List<Empleado>, Map<String, String>> obtenerSupervisores = lista -> {
            Map<String, String> mapa = new HashMap<>();
            for (Empleado e : lista) {
                if (e.getCargo().toLowerCase().contains("supervisor")) {
                    mapa.put(e.getDepartamento(), e.getNombre() + " " + e.getApellido());
                }
            }
            return mapa;
        };

        Map<String, String> mapaSupervisores = obtenerSupervisores.apply(empleados);
        System.out.println("Supervisores por departamento:");
        for (Map.Entry<String, String> entry : mapaSupervisores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        Consumer<List<Empleado>> mostrarContabilidad = lista -> {
            for (Empleado e : lista) {
                if ("Contabilidad".equals(e.getDepartamento())) {
                    Formatter.print(formatoFicha.format(e));
                }
            }
        };

        mostrarContabilidad.accept(empleados);

        Comparator<Empleado> ordenarPorApellido = Comparator.comparing(Empleado::getApellido);
        List<Empleado> listaOrdenada = new ArrayList<>(empleados);
        listaOrdenada.sort(ordenarPorApellido);

        System.out.println("Empleados ordenados por apellido:");
        for (Empleado e : listaOrdenada) {
            Formatter.print(formatoNomina.format(e));
        }

    }

    public static void loadEmpleados(List<Empleado> empleadoList) {
        empleadoList.add(new Empleado("María", "Rodríguez", "F", "Contabilidad", "Asistente Contable", new BigDecimal(700), LocalDate.parse("2021-04-01")));
        empleadoList.add(new Empleado("Juan", "Gutierrez", "M", "Talento Humano", "Reclutador", new BigDecimal(500), LocalDate.parse("2023-03-11"), LocalDate.parse("2024-04-01"), false));
        empleadoList.add(new Empleado("José", "Albornoz", "M", "Contabilidad", "Asistente Contable", new BigDecimal(800), LocalDate.parse("2020-08-15"), LocalDate.parse("2023-05-01"), false));
        empleadoList.add(new Empleado("Julián", "Flores", "M", "Informática", "Soporte TI", new BigDecimal(800), LocalDate.parse("2023-11-01")));
        empleadoList.add(new Empleado("Camila", "Mendoza", "F", "Informática", "Desarrollador UI/UX", new BigDecimal(1000), LocalDate.parse("2021-07-08")));
        empleadoList.add(new Empleado("Camilo", "López", "M", "Contabilidad", "Supervisor Contable", new BigDecimal(1500), LocalDate.parse("2020-04-11")));
        empleadoList.add(new Empleado("Manuel", "Játiva", "M", "Contabilidad", "Asistente Contable", new BigDecimal(850), LocalDate.parse("2023-06-03")));
        empleadoList.add(new Empleado("Carlos", "Franco", "M", "Talento Humano", "Reclutador", new BigDecimal(650), LocalDate.parse("2023-01-07")));
        empleadoList.add(new Empleado("Raúl", "Echeverría", "M", "Informática", "Infraestructura TI", new BigDecimal(950), LocalDate.parse("2020-02-14")));
        empleadoList.add(new Empleado("Estefanía", "Mendoza", "F", "Talento Humano", "Supervisora TH", new BigDecimal(1600), LocalDate.parse("2021-09-21")));
        empleadoList.add(new Empleado("Julie", "Flores", "F", "Informática", "Desarrollador", new BigDecimal(1200), LocalDate.parse("2021-12-10")));
        empleadoList.add(new Empleado("Melissa", "Morocho", "F", "Contabilidad", "Asistente Contable", new BigDecimal(820), LocalDate.parse("2022-05-22"), LocalDate.parse("2023-07-09"), false));
        empleadoList.add(new Empleado("Camila", "Mendez", "F", "Contabilidad", "Asistente Cuentas", new BigDecimal(860), LocalDate.parse("2020-10-01")));
        empleadoList.add(new Empleado("José", "Rodríguez", "M", "Informática", "Tester QA", new BigDecimal(1100), LocalDate.parse("2021-10-01")));
        empleadoList.add(new Empleado("Esteban", "Gutierrez", "M", "Talento Humano", "Reclutador", new BigDecimal(700), LocalDate.parse("2023-04-01")));
        empleadoList.add(new Empleado("María", "López", "F", "Contabilidad", "Asistente Contable", new BigDecimal(840), LocalDate.parse("2020-02-20"), LocalDate.parse("2024-07-15"), false));
        empleadoList.add(new Empleado("Cecilia", "Marín", "F", "Informática", "Supervisora TI", new BigDecimal(2000), LocalDate.parse("2020-04-21")));
        empleadoList.add(new Empleado("Edison", "Cáceres", "M", "Informática", "Desarrollador TI", new BigDecimal(1300), LocalDate.parse("2023-07-07")));
        empleadoList.add(new Empleado("María", "Silva", "F", "Contabilidad", "Asistente Contable", new BigDecimal(900), LocalDate.parse("2021-11-15"), LocalDate.parse("2022-08-09"), false));

    }


}



