package sort;

import java.util.Arrays;

import static sort.ArrayUtils.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @Author: csx
 * @Date: 2017-11-20
 */
public class BucketSort {

    public static void bucketSort(int[] arr){
        int minLength=2;
        if(arr==null||arr.length<minLength){
            return;
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }
        int[] bucket = new int[max+1];
        for (int i=0;i<arr.length;i++){
            bucket[arr[i]]++;
        }
        int i=0;
        for(int j=0;j<bucket.length;j++){
            while (bucket[j]-->0){
                arr[i++]=j;
            }
        }

    }


    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            bucketSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        bucketSort(arr);
        printArray(arr);

    }
}
