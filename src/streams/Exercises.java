package streams;

import principles.interfaces.EmpFormat;
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

public class Exercises {
    public static void main(String[] args) {


        List<Empleado> empleados = new ArrayList<>();
        loadEmpleados(empleados);

        //1. Dada la clase "Empleado" implementa una interfaz funcional personalizada con un metodo llamado format que
        // recibe como parametro un Empleado y devuelve un String . Posteriormente crea dos formatos diferentes para
        // mostrar la información del empleado. Ej Reporte de nomina y Ficha Personal

        System.out.println("------------------Punto 1--------------------------------");

        EmpFormat empNominaFormatter = e -> String.format("Reporte de Nómina:\nNombre: %s %s\nDepartamento: %s\nCargo: %s\nSalario: $%.2f\nFecha de Ingreso: %s\n",
                e.getNombre(), e.getApellido(), e.getDepartamento(), e.getCargo(), e.getSalario(), e.getFechaIng());

        System.out.println(empNominaFormatter.format(empleados.get(0)));

        EmpFormat empPersonalFormatter = e -> String.format("Ficha Personal:\nNombre: %s %s\nGénero: %s\nDepartamento: %s\nCargo: %s\nFecha de Ingreso: %s\n",
                e.getNombre(), e.getApellido(), e.getGenero(), e.getDepartamento(), e.getCargo(), e.getFechaIng());
        System.out.println(empPersonalFormatter.format(empleados.get(0)));

        //2. Predicate: Filtrar empleados activos con salario menor a 700 USD
        System.out.println("------------------Punto 2--------------------------------");

        Predicate<Empleado> empActivoSalario = e -> e.getActive() && e.getSalario().compareTo(new BigDecimal(700)) < 0;

        for (Empleado empleado : empleados) {
            if (empActivoSalario.test(empleado)) {
                System.out.println(empleado);
            }
        }



        //3. Function: Generar un mapa que almacene como clave un departamento y como valor el supervisor de dicho departamento
        System.out.println("------------------Punto 3--------------------------------");

        Function<List<Empleado>, Map<String, String>> deptSupervisor = l_empleados -> {
            Map<String, String> supervisorMap = new HashMap<>();
            l_empleados.forEach(empleado -> {
                if(empleado.getCargo().toLowerCase().contains("supervisor")){
                    supervisorMap.put(empleado.getDepartamento(), empleado.getNombre() + " " + empleado.getApellido());
                }
            });
            return supervisorMap;
        };
        Map<String, String> supervisorByDept = deptSupervisor.apply(empleados);
        supervisorByDept.forEach((dept, sup) -> System.out.println("Departamento: " + dept + " - Supervisor: " + sup));

        //4. Consumer : Imprimir en consola un listado de empleados de un determinado departamento
        System.out.println("\n------------------Punto 4--------------------------------");
        Consumer<Empleado> printEmpleado = e -> {
            if(e.getDepartamento().equals("Informática")){
                System.out.println(e);
            }
        };

        empleados.forEach(printEmpleado);

        //5. Comparator: Ordenar a los empleados por su apellido en orden alfabético

        System.out.println("\n------------------Punto 5--------------------------------");

        Comparator<Empleado> orderBySurname = Comparator.comparing(Empleado::getApellido);

        empleados.stream().sorted(orderBySurname).forEach(System.out::println);

        System.out.println("--------------------------------------------------");




        //Comparator<Empleado> porApellido = (e1, e2) -> e1.getApellido().compareTo(e2.getApellido());



        // Comparator: Ordenar empleados por fecha de ingreso (de más reciente a más antiguo)
        Comparator<Empleado> porFechaIngreso = (e1, e2) -> e2.getFechaIng().compareTo(e1.getFechaIng());
        empleados.stream().sorted(porFechaIngreso).forEach(System.out::println);






    }

    public static void loadEmpleados(List<Empleado> empleadoList){
        empleadoList.add(new Empleado("María", "Rodríguez", "F", "Contabilidad", "Asistente Contable", new BigDecimal(600), LocalDate.parse("2021-04-01")));
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
