package Sem3;

import java.util.Comparator;

//Сравнение по дате рождения
public class EmployeeAgeComparator implements Comparator<Employee>{
    @Override
    public int compare(Employee e1, Employee e2){
        if( e1 == e2) return 0;
        return e2.getBirthDate().compareTo(e1.getBirthDate());
    }
}
