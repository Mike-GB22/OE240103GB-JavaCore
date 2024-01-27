package Sem3;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Employee implements Comparable<Employee>{
    private String vorname;
    private String nachname;
    private String vatername;

    private String position;
    private String phone;

    private BigDecimal salary;
    private LocalDate birthDate;

    public Employee(String vorname, String nachname, String vatername, String position, String phone, BigDecimal salary,
            LocalDate birthDate) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.vatername = vatername;
        this.position = position;
        this.phone = phone;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee [" + getName() + " position: "
                + position + ", tel.: " + phone + ", salary $" + salary + ", birthDate:" + birthDate + "]";
    }

    public String getName() {
        return nachname + " " + vorname + " " + vatername;
    }

    public String getPosition() {
        return position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getBirthDate(){
        return this.birthDate;
    }

    public int getAgeYears(){
        LocalDate now = LocalDate.now();
        return Period.between(this.birthDate, now).getYears();
    }

    public void incremetnSalary(BigDecimal increment){
        salary = salary.add(increment);
    }

    @Override
    public int compareTo(Employee o) {
        //if(!(o instanceof Employee)) throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
        //return this.birthDate.compareTo(((Employee) o).birthDate);
        return this.salary.compareTo(o.salary);
    }  

    //Написать прототип компаратора - метод внутри класса сотрудника, сравнивающий две даты, представленные в виде трёх чисел гггг-мм-дд, без использования условного оператора.
    public static int compareTwoDate(String date1, String date2){
        return date1.compareToIgnoreCase(date2);
    }
   
    
 


    


}
