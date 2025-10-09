import principles.interfaces.FormatterEmployeeInformation;
import resources.Empleado;
import resources.EstadoEmpleado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {


    public static void main(String[] args) {
        List<Empleado> empleados = new ArrayList<>();
        loadEmpleados(empleados);

        //Desarrollo desafio 1:

        // 1. uso de la interfaz funcional personalizada
        //Formato 1: Ficha de nomina
        FormatterEmployeeInformation nominaEmpleado = (empleado) -> String.format(
                "**** NOMINA EMPLEADO ****\n" +
                        " - Datos del empleado \n" +
                        "Nombre completo: %s \n" +
                        "Departamento: %s \n" +
                        "Cargo: %s \n" +
                        " - Informacion salarial \n" +
                        "salario: %d USD \n" +
                        "Fecha de ingreso: %s \n" +
                        "estado del empleado: %s",
                empleado.getNombre().concat(" ").concat(empleado.getApellido()),
                empleado.getDepartamento(),
                empleado.getCargo(),
                empleado.getSalario().longValue(),
                empleado.getFechaIng(),
                EstadoEmpleado.fromBoolean(empleado.getActive()).getDescripcion());

        //Formato 2: Reporte de seguimiento del empleado
        FormatterEmployeeInformation empleadoTracking = (empleado) -> String.format(
                "**** SEGUIMIENTO EMPLEADO ****\n" +
                        "Nombre completo: %s \n" +
                        "genero: %s \n" +
                        "Cargo / Departameto: %s / %s \n" +
                        "Fecha de ingreso: %s \n" +
                        "Fecha de salida: %s \n" +
                        "estado del empleado: %s",
                empleado.getNombre().concat(" ").concat(empleado.getApellido()),
                empleado.getGenero(),
                empleado.getCargo(),
                empleado.getDepartamento(),
                empleado.getFechaIng(),
                empleado.getFechaSal() == null ? "No registra" : empleado.getFechaSal(),
                EstadoEmpleado.fromBoolean(empleado.getActive()).getDescripcion());

        // se aplican las interfaces al primer empleado
        System.out.println("------------------------------------------------------------------------");
        System.out.println(nominaEmpleado.format(empleados.get(0)));
        System.out.println(empleadoTracking.format(empleados.get(0)));


        // aplicando las interfaces a cada empleado de la lista
        empleados.forEach( empleado -> {
            FormatterEmployeeInformation.show(" ----------- // ----------");
            FormatterEmployeeInformation.show(nominaEmpleado.format(empleado));
            FormatterEmployeeInformation.show(empleadoTracking.format(empleado));
        });


        //2. Interfaces de JAVA
        // Predicate: Filtrar empleados activos con salario menor a 700 USD
        BigDecimal salarioLimite = new BigDecimal(700);
        Predicate<Empleado> filtroEmpleados = empleado -> empleado.getActive().equals(true) && empleado.getSalario().compareTo(salarioLimite) < 0;
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("RESULTADO FILTRO PREDICATE : EMPLEADOS ACTIVOS CON SALARIO MENOR A %s%n", salarioLimite);
        List<Empleado> empleadosFiltrados = new ArrayList<>();
        empleados.forEach(
                empleado -> {
                    if (filtroEmpleados.test(empleado)) {
                        empleadosFiltrados.add(empleado);
                    }
                }
        );
        System.out.println(empleadosFiltrados);

        // Function: Generar un mapa que almacene como clave un departamento y como valor un supervisor de dicho departamento
        Function<List<Empleado>, Map<String, String>> deptSup = listaEmpleados -> {
            Map<String, String> newList = new HashMap<>();
            listaEmpleados.forEach(empleado -> {
                String cargo = empleado.getCargo();
                if (cargo.contains("Supervisor")) {
                    newList.put(empleado.getDepartamento(), empleado.getNombre().concat(" ").concat(empleado.getApellido()));
                }
            });
            return newList;
        };
        System.out.println("------------------------------------------------------------------------");
        System.out.println("RESULTADO FUNCTION : MAPA DEPARTAMENTO VS SUPERVISOR");
        Map<String, String> departamentoSupervisorMap = deptSup.apply(empleados);
        departamentoSupervisorMap.forEach( (dep,sup) -> {
            String result = String.format("Departamento: %s , Supervisor: %s ", dep, sup);
            System.out.println(result);
        });

        //Consumer: Imprimir en consola un listado de empleados de un determinado departamento
        String nombreDepartamento = "Contabilidad";
        Consumer<List<Empleado>> empeladoDepartamento = listEmpleados -> {
            listEmpleados.forEach( empleado -> {
                if(empleado.getDepartamento().equalsIgnoreCase(nombreDepartamento)){
                    System.out.println(empleado);
                }
            });
        };
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("RESULTADO CONSUMER: EMPLEADOS QUE PERTENECEN A %s \n", nombreDepartamento);
        empeladoDepartamento.accept(empleados);

        //Comparator: Ordena los empleados por su apellido en orden alfabetico
        Comparator<Empleado> apellidoAlfabetico = Comparator.comparing(Empleado::getApellido);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("RESULTADO COMPARATOR: EMPLEADOS ORDENADOS POR APELLIDO ALFABETICAMENTE");
        List<Empleado> empleadosOrdenados = new ArrayList<>(List.copyOf(empleados));
        empleadosOrdenados.sort(apellidoAlfabetico);
        System.out.println(empleadosOrdenados);


    }

    public static void loadEmpleados(List<Empleado> empleadoList) {
        empleadoList.add(new Empleado("María", "Rodríguez", "F", "Contabilidad", "Asistente Contable", new BigDecimal(700), LocalDate.parse("2021-04-01")));
        empleadoList.add(new Empleado("Juan", "Gutierrez", "M", "Talento Humano", "Reclutador", new BigDecimal(500), LocalDate.parse("2023-03-11"), LocalDate.parse("2024-04-01"), false));
        empleadoList.add(new Empleado("José", "Albornoz", "M", "Contabilidad", "Asistente Contable", new BigDecimal(800), LocalDate.parse("2020-08-15"), LocalDate.parse("2023-05-01"), false));
        empleadoList.add(new Empleado("Julián", "Flores", "M", "Informática", "Soporte TI", new BigDecimal(800), LocalDate.parse("2023-11-01")));
        empleadoList.add(new Empleado("Camila", "Mendoza", "F", "Informática", "Desarrollador UI/UX", new BigDecimal(1000), LocalDate.parse("2021-07-08")));
        empleadoList.add(new Empleado("Camilo", "López", "M", "Contabilidad", "Supervisor Contable", new BigDecimal(1500), LocalDate.parse("2020-04-11")));
        empleadoList.add(new Empleado("Manuel", "Játiva", "M", "Contabilidad", "Asistente Contable", new BigDecimal(850), LocalDate.parse("2023-06-03")));
        empleadoList.add(new Empleado("Carlos", "Franco", "M", "Talento Humano", "Reclutador", new BigDecimal(650), LocalDate.parse("2023-01-07"), LocalDate.parse("2024-12-09"), false));
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
        //empleado prueba predicate
        empleadoList.add(new Empleado("Cristian", "Roa", "M", "Informática", "Desarrollador back", new BigDecimal(500), LocalDate.parse("2021-11-15"), LocalDate.parse("2022-08-09"), true));

    }


}