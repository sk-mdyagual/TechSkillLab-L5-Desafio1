package desafio;

import desafio.principales.MessageFormatter;
import resources.Empleado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Desafio {
    public static void main(String[] args) {
        List<Empleado> empleados = new ArrayList<>();
        loadEmpleados(empleados);

        //1. Interfaz Peronalizada

        //1.1: Reporte de nómina
        MessageFormatter.print("*************** Reporte de Nómina ***************");
        MessageFormatter payrollReport = (employee) ->
                String.format("%-10s %-12s %-20s %-25s $%-10s%n",
                        employee.getNombre(), employee.getApellido(), employee.getDepartamento(), employee.getCargo(),
                        employee.getSalario());

        MessageFormatter.printHeader("%-10s %-12s %-20s %-25s %-10s%n", "Nombre", "Apellido", "Departamento", "Cargo", "Salario");
        empleados.forEach(employee -> MessageFormatter.print(payrollReport.formatter(employee)));

        //1.2: Ficha personal
        MessageFormatter.print("\n *************** Ficha Personal ***************");
        MessageFormatter personalInfoReport = (employee) ->
                String.format("Nombre: %s %s%nGénero: %s%nDepartamento: %s%nCargo: %s%nSalario: $%s%nFecha de Ingreso: %s%nFecha de Salida: %s%nEstado: %s%n",
                        employee.getNombre(), employee.getApellido(), employee.getGenero(), employee.getDepartamento(),
                        employee.getCargo(), employee.getSalario(), employee.getFechaIng(),
                        (employee.getFechaSal() != null) ? employee.getFechaSal() : "N/A",
                        employee.getActive()? "Activo" : "Inactivo");

        empleados.forEach(employee -> MessageFormatter.print(personalInfoReport.formatter(employee)));

        //Punto 2: Filtrar empleados activos con salario menor a 700 USD usando Predicate
        MessageFormatter.print("\n *************** Predicate Empleados activos con salario menor a 700 USD ***************");
        List<Empleado> activeAndSalaryGreaterThan700Employees = new ArrayList<>();
        Predicate<Empleado> isActiveAndSalaryGreaterThan700 = empleado ->
                empleado.getActive() && empleado.getSalario().compareTo(new BigDecimal(700)) < 0;

        empleados.forEach(empleado -> {
            if(isActiveAndSalaryGreaterThan700.test(empleado))
                activeAndSalaryGreaterThan700Employees.add(empleado);
        });

        MessageFormatter.printHeader("%-10s %-12s %-20s %-25s %-10s%n", "Nombre", "Apellido", "Departamento", "Cargo", "Salario");
        activeAndSalaryGreaterThan700Employees.forEach(e -> MessageFormatter.print(payrollReport.formatter(e)));

        //Punto 3: Function Mapa de departamentos y su supervisor usando
        MessageFormatter.print("\n *************** Function Mapa de departamentos y su supervisor ***************");
        Function<List<Empleado>, Map<String, String>> deptSupervisors = (employeeList) -> {
            Map<String, String> result = new HashMap<>();
            employeeList.forEach(employee -> {
                if(employee.getCargo().toLowerCase().contains("supervisor"))
                    result.put(employee.getDepartamento(), employee.getNombre() + " " + employee.getApellido());
            });
            return result;
        };
        Map<String, String> supervisorsByDept = deptSupervisors.apply(empleados);
        MessageFormatter.print(supervisorsByDept);

        //Punto 4: Empleados de un departamento usando Consumer
        MessageFormatter.print("\n *************** Consumer Empleados del departamento de Contabilidad ***************");
        Consumer<List<Empleado>> employeesByDept = (employeeList)->{
            employeeList.forEach(employee ->{
                if(Objects.equals(employee.getDepartamento(), "Contabilidad")){
                    MessageFormatter.print(employee);
                }
            });
        };
        employeesByDept.accept(empleados);

        //Punto 5: Ordenar empleados por apellido en orden alfabético usando Comparator
        MessageFormatter.print("\n *************** Comparator Empleados ordenados por apellido ***************");
        Comparator<Empleado> byLastName = Comparator.comparing(Empleado::getApellido);
        List<Empleado> empleadosSortedByLastName = new ArrayList<>(List.copyOf(empleados));
        empleadosSortedByLastName.sort(byLastName);
        MessageFormatter.print(empleadosSortedByLastName);
    }





    public static void loadEmpleados(List<Empleado> empleadoList){
        empleadoList.add(new Empleado("Raúl", "Echeverría", "M", "Informática", "Infraestructura TI", new BigDecimal(950), LocalDate.parse("2020-02-14")));
        empleadoList.add(new Empleado("Estefanía", "Mendoza", "F", "Talento Humano", "Supervisora TH", new BigDecimal(1600), LocalDate.parse("2021-09-21")));
        empleadoList.add(new Empleado("Julie", "Flores", "F", "Informática", "Desarrollador", new BigDecimal(1200), LocalDate.parse("2021-12-10")));
        empleadoList.add(new Empleado("Melissa", "Morocho", "F","Contabilidad", "Asistente Contable", new BigDecimal(820), LocalDate.parse("2022-05-22"), LocalDate.parse("2023-07-09"), false));
        empleadoList.add(new Empleado("María", "Rodríguez", "F", "Contabilidad", "Asistente Contable", new BigDecimal(700), LocalDate.parse("2021-04-01")));
        empleadoList.add(new Empleado("Juan", "Gutierrez", "M", "Talento Humano", "Reclutador", new BigDecimal(500), LocalDate.parse("2023-03-11"), LocalDate.parse("2024-04-01"), false));
        empleadoList.add(new Empleado("José", "Albornoz", "M","Contabilidad", "Asistente Contable", new BigDecimal(800), LocalDate.parse("2020-08-15"), LocalDate.parse("2023-05-01"), false));
        empleadoList.add(new Empleado("Julián", "Flores", "M", "Informática", "Soporte TI", new BigDecimal(800), LocalDate.parse("2023-11-01")));
        empleadoList.add(new Empleado("Camila", "Mendoza","F", "Informática", "Desarrollador UI/UX", new BigDecimal(1000), LocalDate.parse("2021-07-08")));
        empleadoList.add(new Empleado("Camilo", "López", "M", "Contabilidad", "Supervisor Contable", new BigDecimal(1500), LocalDate.parse("2020-04-11")));
        empleadoList.add(new Empleado("Manuel", "Játiva", "M", "Contabilidad", "Asistente Contable", new BigDecimal(850), LocalDate.parse("2023-06-03")));
        empleadoList.add(new Empleado("Carlos", "Franco", "M", "Talento Humano", "Reclutador", new BigDecimal(650), LocalDate.parse("2023-01-07")));
        empleadoList.add(new Empleado("Camila", "Mendez", "F", "Contabilidad", "Asistente Cuentas", new BigDecimal(860), LocalDate.parse("2020-10-01")));
        empleadoList.add(new Empleado("José", "Rodríguez", "M","Informática", "Tester QA", new BigDecimal(1100), LocalDate.parse("2021-10-01")));
        empleadoList.add(new Empleado("Esteban", "Gutierrez","M", "Talento Humano", "Reclutador", new BigDecimal(700), LocalDate.parse("2023-04-01")));
        empleadoList.add(new Empleado("María", "López","F", "Contabilidad", "Asistente Contable", new BigDecimal(840), LocalDate.parse("2020-02-20"), LocalDate.parse("2024-07-15"), false));
        empleadoList.add(new Empleado("Cecilia", "Marín","F", "Informática", "Supervisora TI", new BigDecimal(2000), LocalDate.parse("2020-04-21")));
        empleadoList.add(new Empleado("Edison", "Cáceres","M", "Informática", "Desarrollador TI", new BigDecimal(1300), LocalDate.parse("2023-07-07")));
        empleadoList.add(new Empleado("María", "Silva", "F","Contabilidad", "Asistente Contable", new BigDecimal(900), LocalDate.parse("2021-11-15"), LocalDate.parse("2022-08-09"), false));

    }
}
