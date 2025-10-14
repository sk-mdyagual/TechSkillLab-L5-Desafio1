import resources.Empleado;
import principles.interfaces.EmpleadoFormat;

import java.math.BigDecimal;
import java.text.Collator;
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

        EmpleadoFormat formatoNomina = e ->
                String.format("%s %s | %s | %s | $%,.2f",
                        e.getNombre(), e.getApellido(),
                        e.getDepartamento(), e.getCargo(),
                        e.getSalario());

        EmpleadoFormat formatoFichaPersonal = e -> {
            String estado = Boolean.TRUE.equals(e.getActive()) ? "Activo" : "Inactivo";
            String egreso = (e.getFechaSal() != null) ? ("\nEgreso     : " + e.getFechaSal()) : "";
            return "---- FICHA PERSONAL ----\n" +
                    "Nombre     : " + e.getNombre() + " " + e.getApellido() + "\n" +
                    "Género     : " + e.getGenero() + "\n" +
                    "Departamento: " + e.getDepartamento() + "\n" +
                    "Cargo      : " + e.getCargo() + "\n" +
                    "Ingreso    : " + e.getFechaIng() + egreso + "\n" +
                    "Estado     : " + estado + "\n" +
                    String.format("Salario    : $%,.2f", e.getSalario()) + "\n" +
                    "-------------------------";
        };

        Predicate<Empleado> activosSalarioMenor700 = e ->
                Boolean.TRUE.equals(e.getActive()) &&
                        e.getSalario().compareTo(BigDecimal.valueOf(700)) < 0;

        System.out.println("Predicate: activos con salario < 700 USD");
        List<Empleado> filtrados = new ArrayList<>();
        for (Empleado e : empleados) {
            if (activosSalarioMenor700.test(e)) {
                filtrados.add(e);
            }
        }
        // 4) Function (criterio: 10 pts)
        Function<List<Empleado>, Map<String, String>> deptToSupervisor = lista -> {
            Map<String, String> mapa = new HashMap<>();
            for (Empleado e : lista) {
                String cargo = e.getCargo() == null ? "" : e.getCargo().toLowerCase(Locale.ROOT);
                if (cargo.contains("supervisor")) {
                    mapa.putIfAbsent(e.getDepartamento(), e.getNombre() + " " + e.getApellido());
                }
            }
            return mapa;
        };

        System.out.println("Function: departamento -> supervisor");
        Map<String, String> mapaSup = deptToSupervisor.apply(empleados);
        for (Map.Entry<String, String> entry : mapaSup.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        // 5) Consumer (criterio: 10 pts)
        Consumer<List<Empleado>> imprimirInformatica = imprimirPorDepartamento("Informática");
        Consumer<List<Empleado>> imprimirContabilidad = imprimirPorDepartamento("Contabilidad");

        System.out.println("Consumer: empleados de Informática");
        imprimirInformatica.accept(empleados);

        System.out.println("Consumer: empleados de Contabilidad");
        imprimirContabilidad.accept(empleados);
        // 6) Comparator (criterio: 10 pts)
        Collator collator = Collator.getInstance(new Locale("es", "ES"));
        collator.setStrength(Collator.PRIMARY);

        Comparator<Empleado> porApellido = (a, b) -> collator.compare(a.getApellido(), b.getApellido());

        System.out.println("Comparator: orden por apellido (ascendente)");
        List<Empleado> copiaOrdenada = new ArrayList<>(empleados);
        copiaOrdenada.sort(porApellido);
        for (Empleado e : copiaOrdenada) {
            System.out.println(e);
        }
    }

    private static Consumer<List<Empleado>> imprimirPorDepartamento(String departamento) {
        return lista -> {
            for (Empleado e : lista) {
                if (departamento.equalsIgnoreCase(e.getDepartamento())) {
                    System.out.println(e);
                }
            }
        };
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
    }
}