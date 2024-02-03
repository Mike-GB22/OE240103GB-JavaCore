//2024/02/02 mip24
package Sem5.ConvertationArayToFile.backup;
/*
 * Структура файла для записи массива:
 * 1й байт:
 *  4 старших бита - 1я размерность массива,
 *  4 младших бита - 2я размерность массива,
 *  (к числу полуенному прибавляем 1, это и есть максимальная размерность 15 + 1 = 16),
 *
 * 2й байт:
 *  1 старший бит - есть ли отрицательные числа (тогда размер битов под число увелчивается на 1)
 *  5 младших бита - число отведеных под бит (т.е. максимальное число может быть представленно 2 в 5, 32 бита, это 4 байта, а значит Инт, хотя со знаком выходит за пределы),
 *
 * 3й байт и дальше, начиная с старших битов:
 *  При наличии отрицательного бита:
 *  (1 бит знака, N битов числа), далее это повторяется
 *  При отсуствия отрицательного бита:
 *  (N битов числа), далее это повторяется
 * 
 *  Негативное число возращаем так: положительно число * -1 
 */

import java.util.ArrayList;
import java.util.List;

import Sem5.ConvertationArayToFile.Settings;

public class ArrayToFileCopy {
    public static void main(String[] args) {
        System.out.println(new AnalisationArrayToConvertation(1,-2000));
        int[][] array = new int[2][2];
        array[0][0] = 1;
        array[1][1] = 0;
        System.out.println(ArryToComplexList(array));
    }

    public static List<Character> ArryToComplexList(int[][] array){
        int sizeX = array.length;
        int sizeY = array[0].length;

        //Проверка что мы не выходим за пределы допустимого
        if(sizeX > Settings.MAXIMAL_ARRAY_SIZE_X)
            throw new RuntimeException
            ("1я размерность массива "+ sizeX +", выходит за пределы допустимого " + Settings.MAXIMAL_ARRAY_SIZE_X);
        if(sizeY > Settings.MAXIMAL_ARRAY_SIZE_Y)
            throw new RuntimeException
            ("2я размерность массива "+ sizeY +", выходит за пределы допустимого " + Settings.MAXIMAL_ARRAY_SIZE_Y);    

        //Создаем 1й байт. Размерность массива
        int sizeUpperBits = (sizeX - 1)<<4;
        int sizeLowerBits = (sizeY - 1);
        char byte1_sizeArray = (char)(sizeLowerBits + sizeUpperBits);
        System.out.println("size x: " + sizeX + ", size y: " + sizeY);        
        System.out.println("sizeUpperBits: " + sizeUpperBits);
        System.out.println("sizeLowerBits: " + sizeLowerBits);
        System.out.println("sizeByte: " + (int)byte1_sizeArray);
                
        //Создаем 2й байт. Количество бит под число, и есть ли отрицательный бит
        AnalisationArrayToConvertation analisiredParrametrsFromArray = findMaxAndMinInArray(array);
        System.out.println(analisiredParrametrsFromArray);

        char byte2_maxNumericAndSing = (char) analisiredParrametrsFromArray.weNeedBits;
        if(analisiredParrametrsFromArray.isNegativ) byte2_maxNumericAndSing += 128;
        System.out.println("byte2_maxNumericAndSing: " + (int)byte2_maxNumericAndSing);

        //Начинаем числа из массива преобразовывать в биты, и по байтово ложить в лист
        List<Character> bytes3AndMore = new ArrayList<>(); 
        //Временное хранилище бит, для работы, добавляем биты с права
        int temp = 0;
        //Счетчик, сколько мы уже бит положили в хранилище
        int inTempWeHaveBits = 0;
        //Сколько мы отводим бит для хранения числа
        int bitsLimit = analisiredParrametrsFromArray.weNeedBits;
        //Маска, для уверенности что старше этих бит, находятся 0       
        int maskBorderForBits = (0xFFFFFFFF>>>(32-bitsLimit));        
        //Будем ли мы записывать знак числа
        boolean haveWeNegativeSing = analisiredParrametrsFromArray.isNegativ;
        //Количество бит которые мы будем вырезать для записи в файл (серва в список)
        final int BITS_IN_BYTE = 8;
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                System.out.println(maskBorderForBits);
                int thisNumber = array[x][y];
                int isThisNumberNegativ = 0;
                //Проверяем знак, числа, и делаем число положительным
                if(thisNumber < 0){
                    isThisNumberNegativ = 1;
                    thisNumber *= -1;
                }
                //Если стоит флаг, что мы должны писать знак числа, какой бы он не был 0 это "+", 1 это "-"
                if(haveWeNegativeSing){
                    //Добавляем знак текущего числа в хранилище бит, с права
                    temp = temp << 1;
                    temp += isThisNumberNegativ;
                    //Увеличивает счетчик бит в хранилище                    
                    inTempWeHaveBits += 1;    
                }
                //Обрезаем маской все старшие биты текущего числа
                int result = thisNumber * maskBorderForBits;
                //Добравляем неше число в хранилище бит, с права
                temp = temp << bitsLimit;
                temp += result;
                //Увеличивает счетчик бит в хранилище
                inTempWeHaveBits += bitsLimit;

                //Если в хранилище накопилось 8 или больше байт, забераем их в список
                if(inTempWeHaveBits >= BITS_IN_BYTE){
                    //остаток бит, число бит которое останется в счетчики, после забора 1 байта
                    int newInTempWeHaveBits = inTempWeHaveBits - BITS_IN_BYTE;
                    //получаем 8 байт которые мы должны записать в файл и ложим их в список
                    char resultToList = (char)(temp>>newInTempWeHaveBits);
                    bytes3AndMore.add((Character) resultToList);                    
                    //биты во временном хранилище бит, которые остались, после забора байта
                    temp -= resultToList;
                }
            }
        }
        //Если после перебора всего массива, в буфере, во временном хранилище остались не забранные биты, мы должны их тоже добавить в список
        if(inTempWeHaveBits>0){
            //сдвигаем все биты к старшему концу
            temp = temp<<(BITS_IN_BYTE - inTempWeHaveBits);
            bytes3AndMore.add((Character) ((char)temp));
        }

        List<Character> returnList = new ArrayList<>();
        returnList.add(byte1_sizeArray);
        returnList.add(byte2_maxNumericAndSing);
        returnList.addAll(bytes3AndMore);
        return returnList;
    }

    public static AnalisationArrayToConvertation findMaxAndMinInArray(int[][] array){
        int max = array[0][0];
        int min = max;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                if(array[i][j] > max) max = array[i][j];
                if(array[i][j] < min) min = array[i][j];
            }
        }

        return new AnalisationArrayToConvertation(max, min);
    }

    static class AnalisationArrayToConvertation {
        public int max;
        public int min;
        public int maxAbsolut;        
        public boolean isNegativ = false;
        public byte weNeedBits;

        public AnalisationArrayToConvertation(int max, int min){
            if(max >= min) {
                this.max = max;
                this.min = min;
            } else {
                this.max = min;
                this.min = max;
            }

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
            return "MaxMinArrayToFile [max=" + max + ", min=" + min + ", maxAbsolut=" + maxAbsolut + ", isNegativ="
                    + isNegativ + ", weNeedBits=" + weNeedBits + "]";
        }
        
    }


}


