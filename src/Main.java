import principles.interfaces.EmpleadoFormatter;
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

//        //To-do: Filtrar empleados por un atributo: departamento
//        Predicate<Empleado> deptInfo = empleado -> empleado.getDepartamento().equals("Informática");
//
//        //To-do: Ordenar empleados por un atributo: Nombre
//        Comparator<Empleado> porNombre = Comparator.comparing(empleado -> empleado.getNombre());
//
//        //To-do: Generar un mapa que me permita tener como clave los departamentos y como valor el total de empleados por departamento
//        Function<List<Empleado>, Map<String, Integer>> deptCount = l_empleados -> {
//            Map<String, Integer> total = new HashMap<>();
//            l_empleados.forEach(empleado -> {
//                total.merge(empleado.getDepartamento(), 1, Integer::sum);
//            });
//            return total;
//        };
//
//        //To-do: Mostrar empleados por un consumer: Contratados en determinado mes
//        Consumer<List<Empleado>> empEnero = emps -> {
//            emps.forEach(empleado -> {
//                if(empleado.getFechaIng().getMonth() == Month.JANUARY){
//                    System.out.println(empleado);
//                }
//            });
//        };

        //**** DESAFÍO 1*****
        //To-do: Filtrar empleados activos con salario menor a 700 USD.
        Predicate<Empleado> empActiveSalMenor700 = empleado ->
                empleado.getActive() && empleado.getSalario().compareTo(new BigDecimal("700")) < 0;

        //To-do: Generar un mapa que almacene como clave un departamento y como valor el supervisor de dicho departamento.
        Function<List<Empleado>, Map<String, String>> mapDeptoSupervisor = l_empleados -> {
            Map<String, String> mapa = new HashMap<>();
            l_empleados.forEach(empleado -> {
                if (empleado.getCargo().contains("Supervis")) {
                    mapa.put(empleado.getDepartamento(), empleado.getNombre() + ' ' + empleado.getApellido());
                }
            });
            return mapa;
        };

        //To-do: Imprimir en consola un listado de empleados de un determinado departamento.
        Consumer<List<Empleado>> empInformatica = emps -> {
            emps.forEach(empleado -> {
                if(empleado.getDepartamento().contains("Informática") ){
                    System.out.println(empleado);
                }
            });
        };

        //To-do: Ordenar empleados por su apellido en orden alfabético
        Comparator<Empleado> porApellido = Comparator.comparing(empleado -> empleado.getApellido());


        //To-do: Uso de las funciones     //1. Predicate
        ////        System.out.println("Predicate resultado");
        ////        List<Empleado> infoEmp = new ArrayList<>();
        ////        empleados.forEach(empleado -> {
        ////            if(deptInfo.test(empleado)){
        ////                infoEmp.add(empleado);
        ////            }
        ////        });
        ////
        ////        System.out.println(infoEmp);
        ////        //2. Comparator
        ////        System.out.println("Comparator resultado");
        ////        List<Empleado> empleados1 = new ArrayList<>(List.copyOf(empleados));
        ////        empleados1.sort(porNombre);
        ////        System.out.println(empleados1);
        ////
        ////        //3. Function
        ////        System.out.println("Function resultado");
        ////        Map<String, Integer> totalPorDept = deptCount.apply(empleados);
        ////        System.out.println(totalPorDept);
        ////
        ////        //4. Consumer
        ////        System.out.println("Consumer resultado");
        ////        empEnero.accept(empleados);

//

        //***** DESAFÍO 1 ******
        Empleado empleado = new Empleado("María",
                "Rodríguez",
                "F",
                "Contabilidad",
                "Asistente Contable",
                new BigDecimal(700),
                LocalDate.parse("2021-04-01"));

        //Formato de nómina
        EmpleadoFormatter formatoNomina = emp -> String.format(
                "******* Reporte de Nómina ******%n" +
                        "Nombre completo: %s %s%n" +
                        "Departamento: %s%n" +
                        "Cargo: %s%n" +
                        "Salario: $%,.2f%n" +
                        "Estado: %s%n",
                emp.getNombre(), emp.getApellido(),
                emp.getDepartamento(), emp.getCargo(),
                emp.getSalario(),
                emp.getActive() ? "Activo" : "Inactivo"
        );

        // Formato de ficha personal
        EmpleadoFormatter formatoFicha = e -> String.format(
                "****** Ficha Personal ******%n" +
                        "Nombre completo: %s %s%n" +
                        "Género: %s%n" +
                        "Departamento: %s%n" +
                        "Cargo: %s%n" +
                        "Fecha ingreso: %s%n" +
                        "Salario actual: $%,.2f%n",
                e.getNombre(), e.getApellido(),
                e.getGenero(), e.getDepartamento(),
                e.getCargo(), e.getFechaIng(),
                e.getSalario()
        );

        // Mostrar usando método estático
        EmpleadoFormatter.show(formatoNomina.format(empleado));
        EmpleadoFormatter.show(formatoFicha.format(empleado));

        // PREDICATE
        System.out.println("Predicate resultado");
        System.out.println("Empleados activos con salario menor a 700 USD");

        boolean encontrado = false;

        for (Empleado e : empleados) {
            if (empActiveSalMenor700.test(e)) {
                System.out.println(e);
                encontrado = true;
            }
        };

        if (!encontrado) {
            System.out.println("No existen empleados activos con salario menor a 700 USD.");
        }

        //FUNCTION
        System.out.println("Function resultado");
        System.out.println("Mapa departamento y valor supervisor");
        Map<String, String> supervisoresxDepto = mapDeptoSupervisor.apply(empleados);
        System.out.println(supervisoresxDepto);

        //CONSUMER
        System.out.println("Consumer resultado");
        System.out.println("Lista de empleados del departamento de Informática");
        empInformatica.accept(empleados);

        //COMPARATOR
        System.out.println("Comparator resultado");
        System.out.println("Empleados ordenados por su apellido");
        List<Empleado> empleados1 = new ArrayList<>(List.copyOf(empleados));
        empleados1.sort(porApellido);
        System.out.println(empleados1);
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