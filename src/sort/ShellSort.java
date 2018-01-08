package sort;

import static sort.ArrayUtils.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 希尔排序
 * @Author: csx
 * @Date: 2018/01/08
 */
public class ShellSort {
    /**
     * 希尔排序
     * 希尔排序实质是分组插入排序
     * @param arr
     */
    public static void shellSort(int[] arr){
        int minLength=2;
        if(arr==null||arr.length<minLength){
            return;
        }
        int f=arr.length/2;
        while (f>0){
            System.out.println("gap:"+f);
            for(int i=f;i<arr.length;i++){
                for(int j=i;j>=f&&arr[j]<arr[j-f];j-=f){
                    swap(arr,j-f,j);
                }
            }
            f>>=1;
        }
    }



    public static void main(String[] args) {
        int testTime = 0;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            shellSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                printArrayInLine(arr3);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }
}
