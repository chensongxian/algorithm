package sort;

import java.util.Arrays;

/**
 * 快排，最差情况复杂度O(N²),平均情况N*O(logN)
 */
public class QuickSort {

    public static int partition_1(int[] arr,int l,int r){
        //固定的切分方式
       int pivot = arr[l+(int)(Math.random()*(r-l+1))];
       while (l<=r){
           /*
            * arr[l]小于划分值时,l++继续往右扩
            * arr[r]大于划分值时，r--继续往左扩
            * 两个while循环之后,arr[l]一定是大于pivot的，arr[r]一定是小于pivot的
            * 如果还满足l<=r，则交换l和r两个位置的数，l++和r--之后继续while循环
            */
           while (arr[l]<pivot){
               l++;
           }
           while (arr[r]>pivot){
               r--;
           }
           if(l<=r){
               swap(arr,l,r);
               l++;
               r--;
           }
       }
       return l;
    }

    public static void sort(int[] arr,int l ,int r){
        if(l>=r){
            return ;
        }
        int index=partition_1(arr,l,r);
        sort(arr,l,index-1);
        sort(arr,index,r);
    }

    public static void quickSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    public static void quickSort(int[] arr,int l,int r){
        if(l<r){
            //随机一个数来当划分值,并把它和最后一个数进行交换，
            // 这个数将在划分过程中不动，知道划分完成之后才进行处理
            swap(arr,l+(int)(Math.random()*(r-l+1)),r);
            int[] p = partition(arr,l,r);
            quickSort(arr,l,p[0]-1);
            quickSort(arr,p[1]+1,r);
        }
    }

    public static int[] partition(int[] arr,int l,int r){
        //小于区边界，从最左边前一个位置开始，即l-1,这个位置在第一次划分时是-1.此时并不存在这个位置
        int less = l-1;
        //大于区，从最右边的一个数开始，注意此时它是划分值，这个值在划分过程中将不会交换
        int more = r;
        while (l<more){
            if(arr[l]<arr[r]){
                //当arr[l]比划分值小时，小于区边界往右扩
                swap(arr,++less,l++);
            }else if(arr[l]>arr[r]){
                //当arr[l]大于划分值时，大于区往左扩
                swap(arr,--more,l);
            }else{
                //相等时，比较值往下走，小于区不变
                l++;
            }
        }
        //最后处理反正arr[r]上的划分值，把它与大于区的左边界进行交换
        //此时小于划分值的已经在划分值的右边，大于的已经在左边
        swap(arr,more,r);
        //less+1和more是等于区的边界，less是小于区右边界，more+1是大于区左边界
        return new int[]{less+1,more};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    //用以测试比较
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random() + (int) (maxValue) * Math.random());
        }
        return arr;
    }

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

    public static void printArray(int[] arr){
        if(arr==null){
            return;
        }
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]+" ");
        }
        System.out.println();
    }

    // 测试
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 50;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            sort(arr1,0,arr1.length-1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        quickSort(arr);
        printArray(arr);
    }
}
