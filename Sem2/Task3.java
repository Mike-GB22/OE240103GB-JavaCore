package Sem2;

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,3,4,5,6,7,8,9,0,0,1};
        int[] arr2 = new int[]{1,2,3,4,5,6,7,8,9,0,2,0,1};

        System.out.println(Arrays.toString(arr1) + " - " + checkTwoNollInRow(arr1));
        System.out.println(Arrays.toString(arr1) + " - " + checkTwoNollInRow(arr2));
    }

    public static boolean checkTwoNollInRow(int[] arr){
        for(int i = 1; i < arr.length; i++){
            if(arr[i-1] == 0 && arr[i] == 0) return true;
        }
        return false;
    }
}
