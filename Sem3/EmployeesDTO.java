package Sem3;

import java.util.ArrayList;
import java.util.List;

public class EmployeesDTO {
    private List<Employee> employees;
    
    public EmployeesDTO(){
        employees = new ArrayList<>();
    }

    public Employee get(int index){
        if (index >= employees.size()) return null;
        return employees.get(index);
    }

    public void add(Employee e){
        employees.add(e);
    }

    public String toString(){
        StringBuilder returnString = new StringBuilder();
        for(Employee e: employees){
            returnString.append(e.toString());
            returnString.append("\n");
        }

        return returnString.toString();
     }

    public int getSize(){
        return employees.size();
    }
}
