package Sem2;

import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) {
        int x = 10;
        int y = 10;
        int[][] arr = generateMatrixAsSnake(y, x);
        printArr(arr);
    }

    public static int[][] generateMatrixAsSnake(int y, int x){
        int[][] arr = new int[y][x];
        int count = 1;
        for(int ring = 0; count < x*y; ring++){
            int i = ring;
            int j = ring;
            for(; i < x - ring - 1; i++, count++){
                arr[j][i] = count;
            }
            for(; j < y - ring - 1; j++, count++){
                arr[j][i] = count;
            }
            for(; i > ring; i--, count++){
                arr[j][i] = count;
            }
            for(; j > ring; j--, count++){
                arr[j][i] = count;
            }
        }
        return arr;
    }

    public static void printArr(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}
