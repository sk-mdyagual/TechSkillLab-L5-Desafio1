import resources.Empleado;
import resources.EmpleadoFormatter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        List<Empleado> empleados = new ArrayList<>();
        cargarEmpleados(empleados);

        // 1) Interfaz funcional personalizada: EmpleadoFormatter
        EmpleadoFormatter formatoNomina = e ->
                String.format("NÓMINA | %s %s | Dep: %s | Cargo: %s | Salario: %s | Activo: %s",
                        e.getNombre(), e.getApellido(), e.getDepartamento(), e.getCargo(),
                        e.getSalario().toPlainString(), e.getIsActive());

        EmpleadoFormatter formatoFicha = e ->
                String.format("FICHA | %s %s (%s) | %s | %s | Ing: %s | Sal: %s | Activo: %s",
                        e.getNombre(), e.getApellido(), e.getGenero(),
                        e.getDepartamento(), e.getCargo(),
                        e.getFechaIng(), e.getFechaSal(), e.getIsActive());

        System.out.println("-- Formatos personalizados --");
        for (Empleado e : empleados) {
            System.out.println(formatoNomina.format(e));
            System.out.println(formatoFicha.format(e));
        }

        // 2) Predicate: empleados activos con salario < 700 USD
        Predicate<Empleado> activosYMenor700 = e -> Boolean.TRUE.equals(e.getIsActive())
                && e.getSalario().compareTo(new BigDecimal("700")) < 0;

        System.out.println("\n-- Empleados activos con salario < 700 --");
        for (Empleado e : empleados) {
            if (activosYMenor700.test(e)) {
                System.out.println(e.getNombre() + " " + e.getApellido() + " | $" + e.getSalario());
            }
        }

        // 3) Function: mapa Departamento -> Supervisor
        Function<List<Empleado>, Map<String, String>> mapaDeptSupervisor = lista -> {
            Map<String, String> resultado = new HashMap<>();
            for (Empleado e : lista) {
                String cargoLower = e.getCargo().toLowerCase(Locale.ROOT);
                boolean esSupervisor = cargoLower.contains("supervisor") ||
                                       cargoLower.contains("jefe") ||
                                       cargoLower.contains("líder") ||
                                       cargoLower.contains("lider");

                if (esSupervisor) {
                    // Guardamos el primero que encontremos por departamento
                    resultado.putIfAbsent(e.getDepartamento(), e.getNombre() + " " + e.getApellido());
                }
            }
            return resultado;
        };

        Map<String, String> deptToSupervisor = mapaDeptSupervisor.apply(empleados);
        System.out.println("\n-- Mapa Departamento -> Supervisor --");
        for (Map.Entry<String, String> entry : deptToSupervisor.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 4) Consumer: imprimir empleados de un departamento dado
        final String departamentoBuscado = "IT"; // cambia este valor para probar
        Consumer<Empleado> impresor = e ->
                System.out.println(e.getNombre() + " " + e.getApellido() + " | " + e.getCargo());

        System.out.println("\n-- Empleados del departamento: " + departamentoBuscado + " --");
        for (Empleado e : empleados) {
            if (departamentoBuscado.equalsIgnoreCase(e.getDepartamento())) {
                impresor.accept(e);
            }
        }

        // 5) Comparator: ordenar por apellido (alfabético)
        empleados.sort(new Comparator<Empleado>() {
            @Override
            public int compare(Empleado a, Empleado b) {
                return a.getApellido().compareToIgnoreCase(b.getApellido());
            }
        });

        System.out.println("\n-- Empleados ordenados por apellido --");
        for (Empleado e : empleados) {
            System.out.println(e.getApellido() + ", " + e.getNombre());
        }
    }

    private static void cargarEmpleados(List<Empleado> lista) {
        lista.add(new Empleado("Ana", "Zapata", "F", "IT", "Desarrolladora", new BigDecimal("650"), LocalDate.parse("2023-01-10")));
        lista.add(new Empleado("Bruno", "Alvarez", "M", "IT", "Supervisor de Desarrollo", new BigDecimal("1800"), LocalDate.parse("2020-03-05")));
        lista.add(new Empleado("Carla", "Moreno", "F", "Ventas", "Vendedora", new BigDecimal("900"), LocalDate.parse("2022-07-01")));
        lista.add(new Empleado("Diego", "Ortega", "M", "Ventas", "Jefe Comercial", new BigDecimal("2200"), LocalDate.parse("2019-11-20")));
        lista.add(new Empleado("Elena", "Pérez", "F", "Talento", "Analista", new BigDecimal("700"), LocalDate.parse("2021-02-15")));
        lista.add(new Empleado("Fabio", "García", "M", "Talento", "Líder de Talento", new BigDecimal("2000"), LocalDate.parse("2018-06-30")));
    }
}
