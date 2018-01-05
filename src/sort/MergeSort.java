package sort;

import java.util.Arrays;

import static sort.ArrayUtils.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 归并排序
 * @Author: csx
 * @Date: 2017-11-19
 */
public class MergeSort {

    /**
     * 进行归并排序
     * @param arr
     */
    public static void mergeSort(int[] arr){
        int minLength=2;
        if(arr==null||arr.length<minLength){
            return;
        }
        mergeSort(arr,0,arr.length-1);
    }

    /**
     * 归并排序也是采用了分治的思想
     * 各层分治递归，最后进行合并
     * @param arr
     * @param l
     * @param r
     */
    public static void mergeSort(int[] arr,int l,int r){
        if(l==r){
            return;
        }
        int mid = l+((r-l)>>1);
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    /**
     * 归并排序核心操作，合并数组
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    public static void merge(int[] arr,int l,int mid,int r){
        int[] help = new int[r-l+1];
        int i = 0;
        int p1 = l;
        int p2 = mid+1;
        /*
         * 将arr[p1]和arr[p2]进行比较
         * 并赋值给辅助方式help
         */
        while (p1<=mid&&p2<=r){
            help[i++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        /*
         * 当p1<=mid时表明mid右边子数组还有没合并完的
         */
        while (p1<=mid){
            help[i++] = arr[p1++];
        }
         /*
         * 当p2<=r时表明mid左边子数组还有没合并完的
         */
        while (p2<=r){
            help[i++] = arr[p2++];
        }

        /*
         * 合并完的help数组已经有序，再把他赋值到原来的arr数组
         */
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }

    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        mergeSort(arr);
        printArray(arr);
    }
}
