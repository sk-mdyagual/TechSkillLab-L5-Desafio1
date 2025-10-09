import resources.Empleado;
import principles.interfaces.FormatoFichaPersonal;
import principles.interfaces.FormatoNomina;
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

        FormatoNomina formatoNomina = (Empleado emp) ->
                String.format("Empleado: %s - Departamento: %s - Cargo: %s - Fecha de Ingreso: %s - Salario: %s",
                        emp.getNombre()+emp.getApellido(),
                        emp.getDepartamento(),
                        emp.getCargo(),
                        emp.getFechaIng().toString(),
                        emp.getSalario().toString());

        FormatoFichaPersonal formatoFichaPersonal = (Empleado emp) ->
                String.format("Primer Nombre: %s - Apellido: %s -  Genero: %s - Cargo: %s - Departamento: %s - Fecha de Ingreso: %s - Salario: %s",
                        emp.getNombre(),
                        emp.getApellido(),
                        emp.getGenero(),
                        emp.getCargo(),
                        emp.getDepartamento(),
                        emp.getFechaIng().toString(),
                        emp.getSalario().toString());

        //////////////////////////////////////////////////////////////////////////////////////////////
        //Actividad 1
        //Generar Reporte Nomina
        System.out.println("=== Formato Nómina ===");
        empleados.forEach(e -> {
            System.out.println(formatoNomina.format(e));
        });

        //////////////////////////////////////////////////////////////////////////////////////////////
        //Actividad 2
        //Generar Reporte Ficha Personal
        System.out.println("=== Formato Ficha Personal ===");
        empleados.forEach(e -> {
            System.out.println(formatoFichaPersonal.format(e));
        });

        //////////////////////////////////////////////////////////////////////////////////////////////
        //Actividad 3 - Interfaz Funcional Predicate
        //Predicado para obtener los empleados activos y con salario inferior a 700USD
        Predicate<Empleado> filtroEmpleadosActivos = emp ->
                emp.getActive().equals(true) && emp.getSalario().compareTo(new BigDecimal("700")) < 0;
        //Impresión de los Empleados
        System.out.println("=== Empleados activos y con salario inferior a 700USD ===");
        empleados.forEach(e -> {
            if (filtroEmpleadosActivos .test(e)) {
                System.out.println(e);
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////
        //Actividad 4 - Interfaz Funcional Function
        //Función para crear mapa con clave el departamento y el supervisor como valor
        Function<List<Empleado>, Map<String,String>> obtenerSupervisoresPorDepartamento = listaEmpleados -> {
            Map<String, String> mapa = new HashMap<>();
            for (Empleado empleado : listaEmpleados) {
                if (empleado.getSupervisor() != null) {
                    String departamento = empleado.getDepartamento();
                    String supervisor = empleado.getSupervisor();

                    if (!mapa.containsKey(departamento)) {
                        mapa.put(departamento, supervisor);
                    }
                }
            }

            return mapa;
        };

        //Impresión de Supervisores por Departamento
        Map<String, String> supervisoresPorDepartamento = obtenerSupervisoresPorDepartamento.apply(empleados);
        System.out.println("=== Mapa Supervisores por Departamento ===");
        for (String departamento : supervisoresPorDepartamento.keySet()) {
            String supervisor = supervisoresPorDepartamento.get(departamento);
            System.out.println(departamento + " -> " + supervisor);
        }

        //////////////////////////////////////////////////////////////////////////////////////////////
        //Actividad 5 - Interfaz Funcional Consumer
        //Imprimir en consola los empleados para un departamento en especifico
        String departamento = "Informática";

        Consumer<Empleado> mostrarEmpleado =
                (empleado) -> System.out.println(empleado.getNombre() + " " + empleado.getApellido() +
                        " (" + empleado.getCargo() + ")");

        //Impresión de Empleados por Departamento
        System.out.println("=== Empleados por el Departamento de " + departamento + " ===");
        empleados.forEach(e -> {
            if(e.getDepartamento().equals(departamento))
                mostrarEmpleado.accept(e);
        });

        //////////////////////////////////////////////////////////////////////////////////////////////
        //Actividad 6 - Interfaz Funcional Consumer
        //Ordenar a los empleados por su apellido en orden alfabético
        Comparator<Empleado> ordenarEmpleadosPorApellido = Comparator.comparing(Empleado::getApellido);
        empleados.sort(ordenarEmpleadosPorApellido);
        System.out.println("=== Empleados Ordenados Alfabéticamente por Apellido ===");
        System.out.println(empleados);



    }

    public static void loadEmpleados(List<Empleado> empleadoList){
        // Contabilidad - Supervisor: Camilo López
        empleadoList.add(new Empleado("María", "Rodríguez", "F", "Contabilidad", "Asistente Contable", new BigDecimal(700), LocalDate.parse("2021-04-01"), "Camilo López"));

        // Talento Humano - Supervisora: Estefanía Mendoza
        empleadoList.add(new Empleado("Juan", "Gutierrez", "M", "Talento Humano", "Reclutador", new BigDecimal(500), LocalDate.parse("2023-03-11"), LocalDate.parse("2024-04-01"), true, "Estefanía Mendoza"));

        // Contabilidad - Supervisor: Camilo López
        empleadoList.add(new Empleado("José", "Albornoz", "M","Contabilidad", "Asistente Contable", new BigDecimal(800), LocalDate.parse("2020-08-15"), LocalDate.parse("2023-05-01"), false, "Camilo López"));

        // Informática - Supervisora: Cecilia Marín
        empleadoList.add(new Empleado("Julián", "Flores", "M", "Informática", "Soporte TI", new BigDecimal(800), LocalDate.parse("2023-11-01"), "Cecilia Marín"));

        // Informática - Supervisora: Cecilia Marín
        empleadoList.add(new Empleado("Camila", "Mendoza","F", "Informática", "Desarrollador UI/UX", new BigDecimal(1000), LocalDate.parse("2021-07-08"), "Cecilia Marín"));

        // Contabilidad - Supervisor: Camilo López
        empleadoList.add(new Empleado("Camilo", "López", "M", "Contabilidad", "Supervisor Contable", new BigDecimal(1500), LocalDate.parse("2020-04-11"), "Camilo López"));

        // Contabilidad - Supervisor: Camilo López
        empleadoList.add(new Empleado("Manuel", "Játiva", "M", "Contabilidad", "Asistente Contable", new BigDecimal(850), LocalDate.parse("2023-06-03"), "Camilo López"));

        // Talento Humano - Supervisora: Estefanía Mendoza
        empleadoList.add(new Empleado("Carlos", "Franco", "M", "Talento Humano", "Reclutador", new BigDecimal(650), LocalDate.parse("2023-01-07"), LocalDate.parse("2024-12-09"), false, "Estefanía Mendoza"));

        // Informática - Supervisora: Cecilia Marín
        empleadoList.add(new Empleado("Raúl", "Echeverría", "M", "Informática", "Infraestructura TI", new BigDecimal(950), LocalDate.parse("2020-02-14"), "Cecilia Marín"));

        // Talento Humano - Estefanía Mendoza
        empleadoList.add(new Empleado("Estefanía", "Mendoza", "F", "Talento Humano", "Supervisora TH", new BigDecimal(1600), LocalDate.parse("2021-09-21"), "Estefanía Mendoza"));

        // Informática - Supervisora: Cecilia Marín
        empleadoList.add(new Empleado("Julie", "Flores", "F", "Informática", "Desarrollador", new BigDecimal(1200), LocalDate.parse("2021-12-10"), "Cecilia Marín"));

        // Contabilidad - Supervisor: Camilo López
        empleadoList.add(new Empleado("Melissa", "Morocho", "F","Contabilidad", "Asistente Contable", new BigDecimal(820), LocalDate.parse("2022-05-22"), LocalDate.parse("2023-07-09"), false, "Camilo López"));

        // Contabilidad - Supervisor: Camilo López
        empleadoList.add(new Empleado("Camila", "Mendez", "F", "Contabilidad", "Asistente Cuentas", new BigDecimal(860), LocalDate.parse("2020-10-01"), "Camilo López"));

        // Informática - Supervisora: Cecilia Marín
        empleadoList.add(new Empleado("José", "Rodríguez", "M","Informática", "Tester QA", new BigDecimal(1100), LocalDate.parse("2021-10-01"), "Cecilia Marín"));

        // Talento Humano - Supervisora: Estefanía Mendoza
        empleadoList.add(new Empleado("Esteban", "Gutierrez","M", "Talento Humano", "Reclutador", new BigDecimal(700), LocalDate.parse("2023-04-01"), "Estefanía Mendoza"));

        // Contabilidad - Supervisor: Camilo López
        empleadoList.add(new Empleado("María", "López","F", "Contabilidad", "Asistente Contable", new BigDecimal(840), LocalDate.parse("2020-02-20"), LocalDate.parse("2024-07-15"), false, "Camilo López"));

        // Informática - Supervisora: Cecilia Marín
        empleadoList.add(new Empleado("Cecilia", "Marín","F", "Informática", "Supervisora TI", new BigDecimal(2000), LocalDate.parse("2020-04-21"), "Cecilia Marín"));

        // Informática - Supervisora: Cecilia Marín
        empleadoList.add(new Empleado("Edison", "Cáceres","M", "Informática", "Desarrollador TI", new BigDecimal(1300), LocalDate.parse("2023-07-07"), "Cecilia Marín"));

        // Contabilidad - Supervisor: Camilo López
        empleadoList.add(new Empleado("María", "Silva", "F","Contabilidad", "Asistente Contable", new BigDecimal(900), LocalDate.parse("2021-11-15"), LocalDate.parse("2022-08-09"), false, "Camilo López"));

    }



}