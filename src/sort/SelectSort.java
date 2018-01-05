package sort;

import static sort.ArrayUtils.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 选择排序，不稳定排序
 * @Author: csx
 * @Date: 2017-11-14
 */
public class SelectSort {


    /**
     * 进行选择排序
      * @param arr
     */
    public static void selectSort(int[] arr){
        int minLength=2;
        if(arr==null||arr.length<minLength){
            return;
        }
        /*
            每趟选择最小的数值的位置,并进行交换
            这样，每个位置就能在正确的位置上了
         */
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                minIndex=arr[j]<arr[minIndex]?j:minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            selectSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                printArrayInLine(arr1);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");


    }
}
