package sort;

import static sort.ArrayUtils.copyArray;
import static sort.ArrayUtils.generateRandomArray;
import static sort.ArrayUtils.printArray;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 计算小和之和，每个数左边比它小的数之和
 * @Author: csx
 * @Date: 2017-11-19
 */
public class SmallSum {
    public static int smallSum(int[] arr){
        if(arr==null||arr.length<2){
            return 0;
        }
        return mergeSort(arr,0,arr.length-1);
    }
    public static int mergeSort(int[] arr,int l,int r){
        if(l==r){
            return 0;
        }
        int mid = l+((r-l)>>1);
        return mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r) + merge(arr, l, mid, r);
    }
    public static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int res = 0;
        while (p1 <= m && p2 <= r) {
            //arr[p1]<arr[p2]时p2右边的数一定比arr[p1]大,同理也可求逆序对
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
