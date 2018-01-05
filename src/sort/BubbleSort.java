package sort;

import sort.ArrayUtils;

import static sort.ArrayUtils.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 冒泡排序
 * @Author: csx
 * @Date: 2017-11-14
 */
public class BubbleSort {
    /**
     * 进行冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int minLength=2;
        if (arr == null || arr.length < minLength) {
            return;
        }
        /*
            冒泡排序,进行arr.length-1次比较,每次比较把最大的往底沉
            为什么e=arr.length-1,没必要进行arr.length次比较因为此时已经有序
            同时为了arr[i+1]时不会越界
         */
        for(int e=arr.length-1;e>0;e--){
            /*
                每次进行e次比较
             */
            for(int i=0;i<e;i++){
                if(arr[i]>arr[i+1]){
                    swap(arr,i,i+1);
                }
            }
            printArrayInLine(arr);
            System.out.println("-----");
        }
    }

    public static void bubbleSort1(int[] arr) {
        int minLength=2;
        if (arr == null || arr.length < minLength) {
            return;
        }
        /*
            该种实现冒泡方法与上面的冒泡排序基本一致
         */
        for(int i=0;i<arr.length-1;i++){
            /*
                每进行一次比较，第arr.length-i-1就会有序
                那么之后的位置就不用再进行比较
             */
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }



    public static void main(String[] args) {
        int testTime = 1;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            System.out.println("初始数组");
            printArrayInLine(arr1);
            bubbleSort(arr1);
//            bubbleSort_1(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }
}
