package Sem2;
import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        int[] arr1 = new int[]{2,1,2,3,4}; 
        int[] arr2 = new int[]{2,2,0};
        int[] arr3 = new int[]{1,3,5};

        System.out.println(Arrays.toString(arr1) + " - " + countOddElements(arr1));
        System.out.println(Arrays.toString(arr2) + " - " + countOddElements(arr2));
        System.out.println(Arrays.toString(arr3) + " - " + countOddElements(arr3));
    }

    public static int countOddElements(int[] arr){
        if (arr == null || arr.length <= 0){return 0;}
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i]%2 == 0) count++;
        }
        return count;
    }
}