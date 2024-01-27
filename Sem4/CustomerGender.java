public enum CustomerGender {
    MAN ("Мужской"),
    WOMAN  ("Женский");

    private String name;

    CustomerGender(String title){
        this.name = title;
    }

    @Override
    public String toString(){
        return this.name;
    }
}

