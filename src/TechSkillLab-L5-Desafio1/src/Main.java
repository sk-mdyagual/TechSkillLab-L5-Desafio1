import principles.interfaces.ResultMessage;
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

        //1. Forma personalizado.
        ResultMessage reporteNomina = empleado -> "Empleado: " + empleado.getNombre() + " " + empleado.getApellido() + " - Salario: $" + empleado.getSalario();
        ResultMessage fichaPersonal = empleado -> "\nNombre: " + empleado.getNombre() + "\nApellido: " + empleado.getApellido() + "\nDepartamento: " + empleado.getDepartamento() + "\nCargo: " + empleado.getCargo();

        System.out.println("Formato Reporte de Nómina:");
        empleados.forEach(empleado -> System.out.println(reporteNomina.format(empleado)));

        System.out.println("\nFormato Ficha Personal:");
        empleados.forEach(empleado -> System.out.println(fichaPersonal.format(empleado)));

        // 2. Predicate - Empleados con salario inferior a 700 USD dolares.
        Predicate<Empleado> SalarioBajo = empleado -> empleado.getSalario().compareTo(new BigDecimal("700")) < 0;
        List<Empleado> resultadoSalarios = new ArrayList<>();

        for (Empleado empleado : empleados) {
            if (SalarioBajo.test(empleado)) {
                resultadoSalarios.add(empleado);
            }
        }
        System.out.println("\nEmpleados con salario < 700:");
        resultadoSalarios.forEach(System.out::println);

        // 3. Function - Clave y Valor de cada Departamento y su Supervisor
        Function<List<Empleado>, Map<String, String>> mapaDeptoSupervisor = lista -> {
            Map<String, String> mapa = new HashMap<>();
            for (Empleado empleado : lista) {
                if (!mapa.containsKey(empleado.getDepartamento())) {
                    mapa.put(empleado.getDepartamento(), empleado.getCargo());
                }
            }
            return mapa;
        };

        Map<String, String> resultadoMapa = mapaDeptoSupervisor.apply(empleados);
        System.out.println("\nMapa - Departamento : Supervisor");
        resultadoMapa.forEach((i, j) -> System.out.println(i + " : " + j));


        // 4. Consumer - Lista de empleados del departamente de Contabilidad
        Consumer<List<Empleado>> empleadosContabilidad = lista -> {
            System.out.println("\nEmpleados del departamento de Contabilidad:");
            for (Empleado empleado : lista) {
                if ("Contabilidad".equals(empleado.getDepartamento())) {
                    System.out.println(empleado);
                }
            }
        };
        empleadosContabilidad.accept(empleados);


        // 5. Comparator - Empleados ordenados alfabeticamente
        Comparator<Empleado> porApellido = Comparator.comparing(Empleado::getApellido);
        List<Empleado> temporal = new ArrayList<>(empleados);
        temporal.sort(porApellido);
        System.out.println("\nEmpleados ordenados por apellido:");
        temporal.forEach(System.out::println);



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