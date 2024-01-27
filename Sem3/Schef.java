package Sem3;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Schef extends Employee{
    public Schef(String vorname, String nachname, String vatername, String position, String phone, BigDecimal salary,
            LocalDate birthDate) {
        super(vorname, nachname, vatername, position, phone, salary, birthDate);
        //TODO Auto-generated constructor stub
    }

    public void incrementSalary(Employee e, BigDecimal increment){
        if(e instanceof Schef) return;
        e.incremetnSalary(increment);
    }

    public void incrementSalaryForAlle(EmployeesDTO eDTO, BigDecimal increment){
        for(int i = 0; i < eDTO.getSize(); i++){
            this.incrementSalary(eDTO.get(i), increment);
        }
    }
}
