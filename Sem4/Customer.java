//2024.01.27 m24
import java.time.LocalDate;

public class Customer {
    private String FIO;
    private LocalDate dateOfBirth;
    private String phone;
    private CustomerGender gender;

    public Customer(String FIO, LocalDate dateOfBirth, String phone) {
        this.FIO = FIO;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
    }

    public CustomerGender getGender(){
        return gender;
    }

    public void setGender(CustomerGender gender){
        this.gender = gender;
    }
}
