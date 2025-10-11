package challenge;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Employee(
        String name,
        String lastName,
        Gender gender,
        Department department,
        String position,
        BigDecimal salary,
        LocalDate startDate,
        LocalDate terminationDate,
        Boolean isActive,
        Boolean isSupervisor
) {
    public enum Gender { MALE, FEMALE }

    public enum Department { ACCOUNTING, HUMAN_RESOURCES, IT, SECURITY, DATA, ADMINISTRATIVE }

    public Employee(
            String name,
            String lastName,
            Gender gender,
            Department department,
            String position,
            BigDecimal salary,
            LocalDate startDate,
            Boolean isSupervisor
    ) {
        this(name, lastName, gender, department, position, salary, startDate, null, Boolean.TRUE, isSupervisor);
    }

    public Employee(
            String name,
            String lastName,
            Gender gender,
            Department department,
            String position,
            BigDecimal salary,
            LocalDate startDate,
            LocalDate terminationDate
    ) {
        this(name, lastName, gender, department, position, salary, startDate, terminationDate, Boolean.FALSE, Boolean.FALSE);
    }
}
