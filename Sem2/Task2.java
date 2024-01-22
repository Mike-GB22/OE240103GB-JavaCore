package Sem2;

import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        int[] arr = new int[]{2,2,3,4,5,6,6,7,8,9,10,11,12,12,13,14};
        System.out.println(Arrays.toString(arr) + ", max: " + findMax(arr)+ ", min: " + findMin(arr) + ", diferez: " + findDiferez(arr));
    }

    public static int findDiferez(int[] arr){
        int min = findMin(arr);
        int max = findMax(arr);
        return max-min;
    }

    public static int findMax(int[] arr){
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max) max = arr[i];
        }
        return max;
    }

    public static int findMin(int[] arr){
        int min = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < min) min = arr[i];
        }
        return min;
    }

}
