package sort;

import static sort.ArrayUtils.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 基数排序
 * @Author: csx
 * @Date: 2018/01/08
 */
public class RadixSort {
    /**
     * 进行基数排序
     * @param arr
     */
    public static void radixSort(int[] arr){
        int minLength=2;
        if(arr==null&&arr.length<minLength){
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    /**
     * 求最大值的长度
     * @param arr
     * @return
     */
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }


    /**
     * 进行基数排序
     * 基数排序:
     * 按照最大值的位数，其他数不足位数补0，然后逐次按个位、百位等上的值排序
     * 例如:
     * 000010
     * 000009
     * 000012
     * 000006
     * 000100
     * 100000
     * 只要把上述的数按照个位到十万位上的数，一个个排序，就能得到想要的结果
     * @param arr
     * @param begin
     * @param end
     * @param digit 最大值长度
     */
    public static void radixSort(int[] arr, int begin, int end, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        /*
         * 用来统计arr上每个数在相应位上是否有值
         * 例如:23,在个位上是3，那么把count[3]=1,即表明23在个位上是3
         */
        int[] count = new int[radix];
        //用来辅助排序
        int[] bucket = new int[end - begin + 1];
        /*
         * digit是最大值的长度即有多少位
         * 从个位即1开始遍历，依次比较排序
         */
        for (int d = 1; d <= digit; d++) {
            for (i = 0; i < radix; i++) {
                count[i] = 0;
            }
            /*
             * 统计arr数组每个数在相应位d上的值
             * 例如:
             * i=0,d=1
             * d=1表示个位,j=getDigit(arr[0], 1)即得到arr[0]在个位上的值j
             * count[j]++,则表明有一个数个位上值为j
             * count[j]为2时表明有两个数个位上值为j
             */
            for (i = begin; i <= end; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            /*
             * 此时count数组已经统计了arr数组上相应位数上的值从0到9存在几个
             * 下面操作，则是要统计count[i]之前存在几个数
             * 例如:
             * count[3]=count[3]+count[2]
             * 通过这步操作之后count[3]的含义则表示在d位上值为3之前还在存在count[3]个数在d位上值为0-3
             */
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            /*
             * count数组已经表示了d位上的值排序的信息
             * bucket[count[j] - 1] = arr[i];表示arr[i]在d位上按大小排在count[j]-1位
             *
             */
            for (i = end; i >= begin; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = begin, j = 0; i <= end; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
//                printArray(arr1);
//                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

//        int[] arr = generateRandomArray(maxSize, maxValue);
//        printArray(arr);
//        radixSort(arr);
//        printArray(arr);

    }
}
