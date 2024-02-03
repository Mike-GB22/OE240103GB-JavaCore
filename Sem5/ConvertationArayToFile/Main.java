package Sem5.ConvertationArayToFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.List;


public class Main {
        public static void main(String[] args) {
        //System.out.println(new AnalysisArrayToConvertation(1,-2000));

        System.out.println("---------------------");
        int[][] array = new int[4][4];
        array[0][0] = 4;
        array[0][1] = 3;
        array[0][2] = 2;
        array[0][3] = 1;

        array[1][0] = 4;
        array[1][1] = 3;
        array[1][2] = 2;
        array[1][3] = 1;

        array[2][0] = 4;
        array[2][1] = 3;
        array[2][2] = 2;
        array[2][3] = 1;

        array[3][0] = 4;
        array[3][1] = 3;
        array[3][2] = 2;
        array[3][3] = 1;

        //array[2][0] = Integer.MAX_VALUE;
        //array[2][1] = Integer.MIN_VALUE;

        System.out.println("У нас есть массив:");
        print2DArray(array);
        System.out.println("\nЕго анализ и разобор говорят нам о том, что:");
        List<Character> list = ArrayToCharList.ArryToComplexList(array);
        System.out.println("\nСодержимое для записи в файл, по байтово:");
        System.out.println(list);
        System.out.println("\nВсего байт: " + list.size());

         //Сохраняем переменную в файл
         try (FileOutputStream fos = new FileOutputStream(Settings.FILE_NAME)){
                //Byte[] buffer = list.toArray(new Byte[list.size()]);
                byte[] buffer = new byte[list.size()];
                int index = 0;
                for(Character oneByte: list){
                    buffer[index++] = (byte) ((char) oneByte);
                }
                fos.write(buffer, 0, buffer.length);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        

        System.out.println("\n---------------------");
        System.out.println("Передаем полученный список, на прямую в функцию декодирования");
        System.out.println("Его анализ и разобор говорят нам о том, что:");
        int[][] decodirtArray = CharListToArray.ComplexListToArry(list);

        System.out.println("\nМы получили массив:");
        print2DArray(decodirtArray);

    }

    public static void print2DArray(int[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                Formatter formatter = new Formatter();
                formatter.format("%5d", array[i][j]);
                System.out.print(formatter);
            }
            System.out.println("");
        }
    }
}
