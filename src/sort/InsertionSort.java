package sort;

import java.rmi.Naming;
import java.util.Arrays;

import static sort.ArrayUtils.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 插入排序
 * @Author: csx
 * @Date: 2017-11-14
 */
public class InsertionSort {

    /**
     * 进行插入排序
     * @param arr
     */
    public static void insertSort(int[] arr){
        int minLength=2;
        if(arr==null||arr.length<minLength){
            return;
        }
        /*
            每次将i之前的数值进行排序，i-1之前的是有序的
            只需把第i个位置的数值插入之前已经排序的数值即可
         */
        for(int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0&&arr[j]>arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }
    }


    /**
     * jdk Array.sort()中的插入排序
     * @param arr
     */
    public static void insertSortOfJdk(int[] arr){
        int minLength=2;
        if(arr == null || arr.length<minLength){
            return;
        }
        for(int i=0,j=i;i<arr.length-1;j=++i){
            //要插入的数值
            int ai = arr[i+1];
            /*
                当ai小于arr[j]时，把arr[j]往后移动
                简而言之，下面这段代码就是把比ai大的数值统统往后移一位
                把ai插入到合适的位置
             */
            while(ai<arr[j]){
                arr[j+1]=arr[j];
                if(j--==0){
                    break;
                }
            }
            arr[j+1] = ai;
        }
    }



    // 测试
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            insertSortOfJdk(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");


    }
}
