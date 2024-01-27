package Sem3;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        EmployeesDTO employees = new EmployeesDTO();
        employees.add(new Employee("Ivanov", "Ivan", "Ivanovich", "Developer", "+7123456789", BigDecimal.valueOf(5000), LocalDate.of(2000,1,1)));
        employees.add(new Employee("Ivanova", "Ivana", "Ivanovich", "Developer", "+7123456780", BigDecimal.valueOf(5001), LocalDate.of(2000,1,2)));
        
        Employee schef = new Schef("Pertrov", "Petr", "Petrovich", "Schef", "+7123456787", BigDecimal.valueOf(10000), LocalDate.of(1990,1,2));
        employees.add(schef);
        
        System.out.println(employees);

        System.out.println("--- compareTo --- employees.get(0).compareTo(employees.get(1)");
        System.out.println(employees.get(0).compareTo(employees.get(1)));

        System.out.println("\n--- DZ1 --- Employee.compareTwoDate(\"2000-01-01\", \"2003-01-01\")");
        System.out.println(Employee.compareTwoDate("2000-01-01", "2003-01-01"));

        System.out.println("\n--- Incremetn Salary for Alles, außer Schef ---");
        ((Schef) schef).incrementSalaryForAlle(employees, BigDecimal.valueOf(1000));
        System.out.println(employees);
    }
}