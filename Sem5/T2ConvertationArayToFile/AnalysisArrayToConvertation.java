package Sem5.T2ConvertationArayToFile;

public class AnalysisArrayToConvertation {
    public int max;
    public int min;
    public int maxAbsolut;        
    public boolean isNegativ = false;
    public byte weNeedBits;

    public AnalysisArrayToConvertation(int[][] array){

        int tmpMax = array[0][0];
        int tmpMin = tmpMax;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                if(array[i][j] > tmpMax) tmpMax = array[i][j];
                if(array[i][j] < tmpMin) tmpMin = array[i][j];
            }
        }
        this.max = tmpMax;
        this.min = tmpMin;
        analisis();
    }

    public AnalysisArrayToConvertation(int max, int min){
        if(max >= min) {
            this.max = max;
            this.min = min;
        } else {
            this.max = min;
            this.min = max;
        }
        analisis();
    }

    private void analisis(){
        int negativeMin = min * -1;
        if(max >= negativeMin) maxAbsolut = max;
        else maxAbsolut = negativeMin;
        if (maxAbsolut < 0) maxAbsolut *= -1;

        int tempMaxAbsolut = maxAbsolut;
        for(weNeedBits = 1; weNeedBits < Settings.MAXIMAL_BITE_OF_NUMERIC_TO_FILE; weNeedBits++){
            if((tempMaxAbsolut = tempMaxAbsolut>>1) == 0) break;
        }

        if(max < 0 || min < 0) isNegativ = true;
    }

    @Override
    public String toString() {
        return "AnalysisArrayToConvertation [max=" + max + ", min=" + min + ", maxAbsolut=" + maxAbsolut + ", isNegativ="
                + isNegativ + ", weNeedBits=" + weNeedBits + "]";
    }
}