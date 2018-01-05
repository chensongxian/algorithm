package sort;

import java.util.Arrays;

import static sort.ArrayUtils.generateRandomArray;
import static sort.ArrayUtils.printArray;
import static sort.ArrayUtils.swap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 堆排序
 * @Author: csx
 * @Date: 2017-11-19
 */
public class HeapSort {
    /**
     * 进行堆排序
     * 任意一节点i:
     * 父节点:(i-1)/2
     * 左节点:i*2+1
     * 右节点:i*2+2
     * @param arr
     */
    public static void heapSort(int[] arr){
        int minLength=2;
        if(arr==null||arr.length<minLength){
            return;
        }
        /*
         * 构建大根堆，arr[0]是堆顶
         */
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
        //把arr[0]和arr[size-1]交换,此时arr[size-1]是最大值
        swap(arr,0,--size);
        while (size>0){
            /*
             * 调整大根堆,再次构建0到size的大根堆，使arr[0]再次成为0到size的最大值
             */
            heapify(arr,0,size);
            swap(arr,0,--size);
        }
    }

    /**
     * 调整堆，将节点和父节点作比较，不断调整堆
     * @param arr
     * @param index
     */
    public static void heapInsert(int[] arr,int index){
        /*
         * (index-1)/2是父节点
         */
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
        /*
         * 下沉操作:将index与子节点作比较,通过此操作arr[index]再次成为最值,同时把原来arr[index]放到对的位置上
         */
        int left = index*2+1;
        while (left<size){
            //比较左节点和右节点
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            //比较较大子节点和父节点的大小
            largest = arr[largest] > arr[index] ? largest : index;
            //表明index已经是最大值，直接break，不用再进行下沉操作
            if (largest == index) {
                break;
            }
            //交换较大值和父节点
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
        System.out.println("下沉:"+arr[index]);
        printArray(arr);
    }



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
