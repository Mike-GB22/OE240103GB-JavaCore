package Sem5.ConvertationArayToFile;

import java.util.List;

public class CharListToArray {
    public static int[][] ComplexListToArry(List<Character> list){
        int byte1_sizeArray = list.get(0);
        int sizeX = byte1_sizeArray>>>4;
        int sizeY = byte1_sizeArray & 0xF;
        sizeX++;
        sizeY++;
        int sizeXY = sizeX * sizeY;

        System.out.println("1st byte:  " + byte1_sizeArray);
        System.out.println("Размер массива, x: " + sizeX + ", y: " + sizeY);        

        int byte2_maxNumericAndSing =  list.get(1);
        int weUseBitsForNumber = byte2_maxNumericAndSing & 0x1F;
        boolean isNegativSign = false;
        if(byte2_maxNumericAndSing>>>7 == 1) isNegativSign = true;
        
        System.out.println("\n2st byte:  " + byte2_maxNumericAndSing);
        System.out.println("Количество бит для числа:  " + weUseBitsForNumber);
        System.out.println("Есть бит знака:  " + isNegativSign);

        list.add((Character)((char) 0));
        list.add((Character)((char) 0));
        list.add((Character)((char) 0));
        list.add((Character)((char) 0));
        int[] tempArray = new int[sizeXY + 4];
        int inTempWeHaveBits = 0;
        int temp = 0;
        int countReadNumbers = 0;
        final int BITS_IN_TEMP = 32; 
        final int BITS_IN_BYTE = 8; 
        for(int i = 2; i < list.size(); i++){
            temp = temp<<BITS_IN_BYTE;
            temp += list.get(i);
            inTempWeHaveBits += BITS_IN_BYTE;
            if(inTempWeHaveBits >= BITS_IN_TEMP){
                //Перебираем биты в буфере, пока не останется меньше чем достаточно для одного числа - weUseBitsForNumber
                do{
                    int multiplicatorForNegativSing = 1;
                    if(isNegativSign){
                        //Считываем первый бит, в буфере. 0 - число положительное, 1 - отрицательное
                        int firstBit = temp >>> (BITS_IN_TEMP - 1);
                        multiplicatorForNegativSing = 1 - (2 * firstBit);
                        temp = temp<< 1;
                        inTempWeHaveBits --;
                    }
                    int result = temp >>> (BITS_IN_TEMP - weUseBitsForNumber);
                    result *= multiplicatorForNegativSing;
                    tempArray[countReadNumbers] = result;
                    countReadNumbers++;
                    temp = temp << weUseBitsForNumber;
                    inTempWeHaveBits -= weUseBitsForNumber;

                } while ((inTempWeHaveBits >= weUseBitsForNumber) && countReadNumbers < sizeXY);
            }
            
        }

        int[][] array = new int[sizeX][sizeY];
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                array[x][y]=tempArray[sizeX*x + y];
            }
        }

        return array;
    }
   
}
