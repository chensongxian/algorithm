package sort;

import java.util.Arrays;

import static sort.ArrayUtils.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 快排，最差情况复杂度O(N²),平均情况N*O(logN),不稳定排序
 * @Author: csx
 * @Date: 2017-11-14
 */
public class QuickSort {

    /**
     * 快排，第一种写法
     * @param arr
     */
    public static void quickSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    /**
     * 快排第一种写法
     * 快排使用了分治法
     * 从数组中挑出一个基准，划分成两个序列
     * 把子序列再进行划分，递归调用
     * 需要注意的这种写法将划分成三个区域，小于区、等于区、大于区
     * @param arr
     * @param l 左边界
     * @param r 右边界
     */
    public static void quickSort(int[] arr,int l,int r){
        if(l<r){
            /*
             *   第一步要做的是，用随机数得到一个l到r之间的数值作为划分的基准pivot
             *   同时为了方便，把他和arr[r]最右边的值进行交换,arr[r]就成了划分基准
             *   arr[r]暂时先不处理到了最后才处理
             */
            swap(arr,l+(int)(Math.random()*(r-l+1)),r);
            int[] p = partition(arr,l,r);
            quickSort(arr,l,p[0]-1);
            quickSort(arr,p[1]+1,r);
        }
    }

    /**
     * partition是快排的核心操作
     * 通过partition操作把比基准pivot小的放左边，大的放右边
     * 划分成小于区和大于区
     * 但是要注意的是，这种写法将会多划分一个等于区
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int[] partition(int[] arr,int l,int r){
        /*
         *  小于区边界，从最左边前一个位置开始，即l-1,
         *  这个位置在第一次划分时是-1.此时并不存在这个位置
         */
        int less = l-1;
        /*
         * 大于区，从最右边的一个数开始，注意通过第一步swap操作此时它是划分值即pivot，
         * 这个值在划分过程中将不会交换
         */
        int more = r;
        while (l<more){
            if(arr[l]<arr[r]){
                /*
                 * 当arr[l]比划分值小时，小于区边界往右扩即++less
                 * 同时l++,把比较值往右移动
                 */
                swap(arr,++less,l++);
            }else if(arr[l]>arr[r]){
                /*
                 * 当arr[l]大于划分值时，大于区往左扩即--more
                 * 注意此时l不进行操作,因为交换之后，要继续参与比较
                 */
                swap(arr,--more,l);
            }else{
                /*
                 * 相等时，比较值往下走，小于区不变
                 * 那么划分完成之后，这就形成了一个等于区
                 */
                l++;
            }
        }

        /*
         * 最后处理反正arr[r]上的划分值，把它与大于区的左边界进行交换
         * 交换完成之后，分区已经完成
         * 小于区:l-less
         * 等于区:less+1-more
         * 大于区:more+1-r
         */
        swap(arr,more,r);
        return new int[]{less+1,more};
    }


    /**
     * 第二种快排写法
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort1(int[] arr, int l , int r){
        if(l>=r){
            return;
        }
        int index=partition1(arr,l,r);
        quickSort1(arr,l,index-1);
        quickSort1(arr,index,r);
    }

    /**
     * 第二种写法的partition只有大于区和小于区
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int partition1(int[] arr,int l,int r){
        //用随机数得到划分基准
        int pivot = arr[l+(int)(Math.random()*(r-l+1))];
        while (l<=r){
           /*
            * arr[l]小于划分值时,l++继续往右扩
            * arr[r]大于划分值时，r--继续往左扩
            * 两个while循环之后,arr[l]一定是大于pivot的，arr[r]一定是小于pivot的
            * 如果还满足l<=r，则交换l和r两个位置的数，l++和r--之后继续while循环
            * 值得注意的是,在此partition中并没有区分等于划分基准pivot的，等于pivot的可能会到任何一边
            */
           //8 5 9 4 10 9 7 3 11
            // l=2
            while (arr[l]<pivot){
                l++;
            }
            //r=7
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


    /**
     * 第三种快排写法
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort_2(int[] arr,int l,int r){
        if(l>=r){
            return;
        }
        int index=partition_2(arr,l,r);
        quickSort_2(arr,l,index-1);
        quickSort_2(arr,index+1,r);
    }

    /**
     * 在此种partition写法种把arr[l]当做划分基准
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int partition_2(int[] arr,int l,int r){

        /*
         * 当把arr[l]当做中轴并赋值给pivot时
         * arr[l]上的值已经失去意义
         * 相当于把arr[l]的位置空了出来
         */
        int pivot = arr[l];
        int low = l;
        int high = r;
        //当小于区和大于区没有碰撞时，继续while循环
        while (low<high){
            /*
             * arr[high]>=pivot时表明，arr[high]的位置是正确的
             * 此时把high--往左推进，当while循环结束之后arr[high]一定是小于pivot的
             */
            while (low<high&&arr[high]>=pivot){
                high--;
            }

            /*
             * 经过上一步while循环之后,arr[high]一定是小于pivot的
             * 那么把arr[low]=arr[high],把arr[high]换到小于区
             * 注意:
             * arr[low]在第一次时arr[low]就是划分基准pivot
             * 在第一次之后经过下面的while操作,arr[low]上的值也一定是大于pivot的
             * arr[high]=arr[low]之后，arr[low]上的值被交换到了大于区，arr[low]也就相当于空位
             * arr[low]=arr[high]并不会造成值得覆盖
             */
            arr[low]=arr[high];
            //同上，low++小于区往右推进
            while (low<high&&arr[low]<pivot){
                low++;
            }
            arr[high]=arr[low];
        }
        /*
         * 因为一开始arr[low]上的值被当成划分基准，所以需要进行处理
         */
        arr[low]=pivot;
        return low;
    }






    public static void main(String[] args) {
        int testTime = 1;
        int maxSize = 10;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort1(arr1,0,arr1.length-1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
