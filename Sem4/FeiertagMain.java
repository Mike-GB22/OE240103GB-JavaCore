public class FeiertagMain {
    public static void main(String[] args) {
        //FeiertagEnum feiertags = new F
        FeiertagEnum[] feiertagArray = FeiertagEnum.values();
        boolean habenWirHeuteFeirtag = false;

        for(int i = 0; i < feiertagArray.length; i++){
            FeiertagEnum feiertag = feiertagArray[i];
            if(feiertag.isThisToday()) {
                habenWirHeuteFeirtag = true;
                System.out.println(feiertag);
            }
        }
        if (!habenWirHeuteFeirtag) System.out.println(FeiertagEnum.KEIN_FEIERTAG);
    }
}
