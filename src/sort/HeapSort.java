package sort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2017-11-19
 */
public class HeapSort {
    public static void heapSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int i=0;i<arr.length;i++){
            heapInsert(arr,i);
        }
        /*
         * 通过构建大根堆，arr[0]永远是最大的值
         * 通过把arr[0]和arr[--size]做交换，那么arr[--size]变成了最大值，
         * 也就是说在这个过程中已经相当于做选择排序，把较大的数不断往后放
         * 同时在把arr[0]和arr[--size]交换之后，做heapify调整已保证arr[0]位置的最大
         */
        int size = arr.length;
        swap(arr,0,--size);
        while (size>0){
            heapify(arr,0,size);
            swap(arr,0,--size);
        }
    }

    public static void heapInsert(int[] arr,int index){
        while (arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    /**
     * 把arr[index]与其子节点比较做下沉处理，到最后size内重新成为一个大根堆，即arr[index]再次成为最值
     * @param arr
     * @param index
     * @param size
     */
    public static void heapify(int[] arr,int index,int size){
        int left = index*2+1;
        while (left<size){
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
        System.out.println("下沉:"+arr[index]);
        printArray(arr);
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 10;
        boolean succeed = true;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr2 = copyArray(arr1);
//            heapSort(arr1);
//
//            comparator(arr2);
//            if (!isEqual(arr1, arr2)) {
//                succeed = false;
//                break;
//            }
//        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }


}
