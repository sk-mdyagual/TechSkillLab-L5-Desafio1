package challenge;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ChallengeMain {
    public static void main(String[] args) {
        customFunctionalInterfaces();
        filterInactiveEmployeesWithSalaryUnder(new BigDecimal(7000));
    }

    static void customFunctionalInterfaces() {
        System.out.println("\n==========CUSTOM FUNCTIONAL INTERFACES==========");

        var employees = Utils.generateEmployees();

        EmployeeFormatter financialFormatter = (Employee e) -> {
            var fullName = e.name() + " " + e.lastName();
            var formattedSalary = NumberFormat.getInstance().format(e.salary());
            var monthsWorked = ChronoUnit.MONTHS.between(e.startDate(), e.terminationDate() == null ? LocalDate.now(): e.terminationDate());
            var totalPayedFromBaseSalary = BigDecimal.valueOf(monthsWorked).multiply(e.salary());
            var formattedTotalPayed = NumberFormat.getInstance().format(totalPayedFromBaseSalary);

            return String.format(
                    "Financial report of %-20s |\t Monthly salary=%s \t|\t Months worked=%s, \t|\t Sum of base salaries paid=$%s",
                    fullName, formattedSalary, monthsWorked, formattedTotalPayed
            );
        };
        System.out.println("\n----------Financial Report Format----------");
        employees.forEach(employee -> System.out.println(financialFormatter.format(employee)));

        EmployeeFormatter profileFormatter = (Employee e) -> {
            var pronoun = e.gender().name().equals("MALE") ? "He" : "She";
            var month = e.startDate().getMonth().toString().toLowerCase();
            var statusString = e.isActive() ? "Currently working" : "Worked";

            return String.format(
                    "Welcome to %s's profile! %s arrived here on a %s of %s on the year %s. %s on the %s department as a %s",
                    e.name(), pronoun, e.startDate().getDayOfMonth(), month, e.startDate().getYear(), statusString, e.department(), e.position()
            );
        };
        System.out.println("\n----------CRM Profile Description Format----------");
        employees.forEach(employee -> System.out.println(profileFormatter.format(employee)));

    }

    static void filterInactiveEmployeesWithSalaryUnder(BigDecimal minSalary) {
        System.out.println("\n==========FILTER ACTIVE EMPLOYEES WITH SALARY OVER " + minSalary + "==========");

        var baseEmployees = Utils.generateEmployees();
        List<Employee> filteredEmployees = new ArrayList<>();

        Predicate<Employee> isSalaryHigherAndIsActive = (Employee e) -> e.isActive() && e.salary().compareTo(minSalary) < 0;

        baseEmployees.forEach(employee -> {
            if (isSalaryHigherAndIsActive.test(employee)) {
                filteredEmployees.add(employee);
            }
        });
        filteredEmployees.forEach(System.out::println);
    }
}
