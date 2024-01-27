//2024.01.27 m24
import java.time.LocalDate;

public enum FeiertagEnum{
    NEY_YEAR("Новый год", 1, 1),
    MART_8TH("Женский день", 1, 1),
    FEBREAR_23("Мужской день", 1, 1),
    HEUTE("День когда я делал эту домашнию работу", 1, 27),
    KEIN_FEIERTAG("Мы сегодня ни чего не отмечаем", 0, 0);

    private String name;
    private int month;
    private int day;

    FeiertagEnum(String title, int month, int day){
        this.name = title;
        this.month = month;
        this.day = day;
    }

    public boolean isThisToday(){
        LocalDate now = LocalDate.now();
        if(month == now.getMonthValue() && day == now.getDayOfMonth()) return true;
        return false;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        if(day == 0) return name;
        return name + " мы отмечаем: " + day + "." + month ;
    }
}