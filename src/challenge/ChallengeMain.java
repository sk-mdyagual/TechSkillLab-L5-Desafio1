package challenge;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChallengeMain {
    public static void main(String[] args) {
        System.out.println("==========CUSTOM FUNCTIONAL INTERFACES==========");
        customFunctionalInterfaces();
    }

    static void customFunctionalInterfaces() {
        var employees = Utils.generateEmployees();

        EmployeeFormatter financialFormatter = (Employee e) -> {
            var fullName = e.name() + " " + e.lastName();
            var formattedSalary = NumberFormat.getInstance().format(e.salary());
            var monthsWorked = ChronoUnit.MONTHS.between(e.startDate(), LocalDate.now());
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

            return String.format(
                    "Welcome to %s's profile! %s arrived here on a %s of %s on the year %s. Currently working on the %s department as a %s",
                    e.name(), pronoun, e.startDate().getDayOfMonth(), month, e.startDate().getYear(), e.department(), e.position()
            );
        };
        System.out.println("\n----------CRM Profile Description Format----------");
        employees.forEach(employee -> System.out.println(profileFormatter.format(employee)));

    }
}
