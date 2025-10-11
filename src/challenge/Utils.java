package challenge;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    static List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Gabriel", "Ronaldo", Employee.Gender.MALE, Employee.Department.IT, "Software Engineer", new BigDecimal(10000), LocalDate.parse("2024-09-01"), Boolean.TRUE));
        employees.add(new Employee("Sofia", "Messi", Employee.Gender.FEMALE, Employee.Department.SECURITY, "Security Analyst", new BigDecimal(8500), LocalDate.parse("2024-07-21"), Boolean.TRUE));
        employees.add(new Employee("Pablo", "Bale", Employee.Gender.MALE, Employee.Department.DATA, "Data Engineer", new BigDecimal(15540), LocalDate.parse("2023-01-02"), Boolean.TRUE));
        employees.add(new Employee("Luisa", "Benzema", Employee.Gender.FEMALE, Employee.Department.DATA, "Data Analyst", new BigDecimal(8200), LocalDate.parse("2020-09-06"), Boolean.FALSE));
        employees.add(new Employee("Maria José", "Yamal", Employee.Gender.FEMALE, Employee.Department.HUMAN_RESOURCES, "Psychologist", new BigDecimal(5500), LocalDate.parse("2025-06-10"), Boolean.TRUE));
        employees.add(new Employee("Esteban", "Moreno", Employee.Gender.MALE, Employee.Department.IT, "Software Engineer", new BigDecimal(9800), LocalDate.parse("2023-09-01"), Boolean.FALSE));
        employees.add(new Employee("Dayro", "Moreno", Employee.Gender.MALE, Employee.Department.IT, "AI Prompt Engineer", new BigDecimal(30000), LocalDate.parse("2024-08-01"), LocalDate.parse("2024-12-31")));
        employees.add(new Employee("Santiago", "Falcao", Employee.Gender.MALE, Employee.Department.ACCOUNTING, "Accountant", new BigDecimal(7300), LocalDate.parse("2022-09-01"), Boolean.TRUE));
        employees.add(new Employee("Gabriela", "Rooney", Employee.Gender.FEMALE, Employee.Department.ACCOUNTING, "Accountant", new BigDecimal(6750), LocalDate.parse("2021-09-01"), Boolean.FALSE));
        employees.add(new Employee("Rene", "Higuita", Employee.Gender.MALE, Employee.Department.DATA, "AI Data BI Prompter", new BigDecimal(17500), LocalDate.parse("2025-06-01"), LocalDate.parse("2025-07-01")));
        employees.add(new Employee("Laura", "Chicharito", Employee.Gender.FEMALE, Employee.Department.ADMINISTRATIVE, "CEO", new BigDecimal(23000), LocalDate.parse("2020-09-01"), Boolean.FALSE));
        employees.add(new Employee("John", "Mbappe", Employee.Gender.MALE, Employee.Department.HUMAN_RESOURCES, "Psychologist", new BigDecimal(3400), LocalDate.parse("2024-08-01"), Boolean.FALSE));
        employees.add(new Employee("Leonel", "Alvarez", Employee.Gender.MALE, Employee.Department.HUMAN_RESOURCES, "AI Psychology Prompts expert", new BigDecimal(1500), LocalDate.parse("2025-08-01"), LocalDate.parse("2025-08-02")));
        return employees;
    }
}
