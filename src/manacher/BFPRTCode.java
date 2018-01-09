package manacher;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: BFPRT,TOP-K问题，从n个元素中选出第k大的元素
 * @Author: csx
 * @Date: 2018/01/05
 */
public class BFPRTCode {
    /**
     * 利用堆排序求TOP-K
     * @param arr
     * @param k
     * @return
     */
    public static int[] getMinKNumsByHeap(int[] arr,int k){
        if(k<1||k>arr.length){
            return arr;
        }
        /*
         * 构建一个大小为k的大堆,而且该大堆是由arr[0]~arr[k]的数构建
         */
        int[] heapArr = new int[k];
        for(int i=0;i!=k;i++){
            heapInsert(heapArr,arr[i],i);
        }
        for(int i=k;i!=arr.length;i++){
            /*
             * 如果arr[i]小于堆顶元素，即做交换，再次调整堆的有序
             * 而heapArr[0]是k堆中最大的元素，当arr[i]小于heapArr[0]时交换
             * 这样就能保证heapArr是最小的k个元素
             */
            if(arr[i]<heapArr[0]){
                heapArr[0]=arr[i];
                heapify(heapArr,0,k);
            }
        }
        return heapArr;
    }

    public static void  heapInsert(int[] arr,int value,int index){
//        arr[index]=value;
//        while (index!=0){
//            int parent = (index-1)/2;
//            if(arr[parent]<arr[index]){
//                swap(arr,parent,index);
//                index = parent;
//            }else{
//                break;
//            }
//        }

        arr[index]=value;
        while (arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index=(index-1)/2;
        }
    }





    public static void heapify(int[] arr,int index,int heapSize){
        int left = index*2+1;
        while (left<heapSize){
            int largest = arr[left+1]>arr[left]&&left+1<heapSize?left+1:left;
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest==index){
                break;
            }
            swap(arr,largest,index);
            index = largest;
            left = index*2+1;
        }
    }

    /**
     * BFPRT求TOP-K问题
     * @param arr
     * @param k
     * @return
     */
    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int minKth = getMinKthByBFPRT(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }
        for (; index != res.length; index++) {
            res[index] = minKth;
        }
        return res;
    }

    /**
     * 获得第k小的元素
     * @param arr
     * @param K
     * @return
     */
    public static int getMinKthByBFPRT(int[] arr, int K) {
        int[] copyArr = copyArray(arr);
        return select(copyArr, 0, copyArr.length - 1, K - 1);
    }

    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i != res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static int select(int[] arr, int begin, int end, int i) {
        /*
         * 递归调用终止条件
         */
        if (begin == end) {
            return arr[begin];
        }
        int pivot = medianOfMedians(arr, begin, end);
        int[] pivotRange = partition(arr, begin, end, pivot);
        //
        if (i >= pivotRange[0] && i <= pivotRange[1]) {
            return arr[i];
        } else if (i < pivotRange[0]) {
            return select(arr, begin, pivotRange[0] - 1, i);
        } else {
            return select(arr, pivotRange[1] + 1, end, i);
        }
    }

    /**
     * 分组取中位数
     * 把数组分成5个元素为一组,分别取中位数
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    public static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        /*
         * 判断数组长度是否是5的倍数，不是则要再加一个组
         */
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        /*
         * 递归调用select方法，直到数组长度小于等于5，也就是刚好只能按5个元素一组只能划分成一组
         */
        return select(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    /**
     * partition过程
     * @param arr
     * @param begin
     * @param end
     * @param pivotValue
     * @return
     */
    public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < pivotValue) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > pivotValue) {
                swap(arr, cur, --big);
            } else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    /**
     * 取中位数,当子数组长度为偶数时取上中位数
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    public static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    /**
     * 插入排序
     * @param arr
     * @param begin
     * @param end
     */
    public static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }


    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9,1 };
        // sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
        printArray(getMinKNumsByHeap(arr,10));
        printArray(getMinKNumsByBFPRT(arr, 10));

    }
}
