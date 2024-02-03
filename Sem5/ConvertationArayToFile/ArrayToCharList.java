//2024/02/02 mip24
package Sem5.ConvertationArayToFile;


import java.util.ArrayList;
import java.util.List;

public class ArrayToCharList {

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
        System.out.println("size of array, x: " + sizeX + ", y: " + sizeY);        
        System.out.println("1st byte: sizeUpperBits: " + sizeUpperBits);
        System.out.println("1st byte: sizeLowerBits: " + sizeLowerBits);
        System.out.println("1st byte:  " + (int)byte1_sizeArray);
                
        //Создаем 2й байт. Количество бит под число, и есть ли отрицательный бит
        AnalysisArrayToConvertation analisiredParrametrsFromArray = new AnalysisArrayToConvertation(array);
        System.out.println(analisiredParrametrsFromArray);

        char byte2_maxNumericAndSing = (char) analisiredParrametrsFromArray.weNeedBits;
        if(analisiredParrametrsFromArray.isNegativ) byte2_maxNumericAndSing += 128;
        System.out.println("2nd byte (max number and sing): " + (int)byte2_maxNumericAndSing);

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
        final int BITS_IN_TEMP = 32; 
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                //System.out.println(maskBorderForBits);
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
                int result = thisNumber & maskBorderForBits;
                System.out.print("thisNumber: " + thisNumber + ", result: " + result);
                System.out.print(", temp: "+ temp);                
                //Добравляем неше число в хранилище бит, с права
                temp = temp << bitsLimit;
                System.out.print(", temp <<: "+ temp);                
                temp += result;
                System.out.print(", temp + result: "+ temp);                                
                //Увеличивает счетчик бит в хранилище
                inTempWeHaveBits += bitsLimit;
                System.out.println(", inTempWeHaveBits: "+ inTempWeHaveBits);                                

                //Если в хранилище накопилось 8 или больше байт, забераем их в список
                while(inTempWeHaveBits >= BITS_IN_BYTE){
                    //остаток бит, число бит которое останется в счетчики, после забора 1 байта
                    int restInTempWeHaveBits = inTempWeHaveBits - BITS_IN_BYTE;
                    //получаем 8 байт которые мы должны записать в файл и ложим их в список, смешя нужные биты в рамки байта
                    char resultToList = (char)(temp>>>restInTempWeHaveBits);
                    bytes3AndMore.add((Character) resultToList);                    
                    //очищаем буфер, оставляя только младшие биты, что мы не брали в предыдущий байт
                    //temp = temp << (BITS_IN_TEMP - restInTempWeHaveBits);
                    //temp = temp >>> (BITS_IN_TEMP - restInTempWeHaveBits-1);
                    temp -= ((int) resultToList) << restInTempWeHaveBits;
                    inTempWeHaveBits = restInTempWeHaveBits;
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
}


