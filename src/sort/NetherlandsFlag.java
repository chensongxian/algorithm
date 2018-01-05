package sort;
import static sort.ArrayUtils.*;
/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 荷兰国旗问题
 * @Author: csx
 * @Date: 2018/01/05
 */
public class NetherlandsFlag {
    /**
     * 直接把1当作pivot，作为划分基准进行partiton即可
     * @param arr
     * @param l
     * @param r
     * @param p
     * @return
     */
    public static int[] partition(int[] arr, int l, int r, int p) {
        int less = l - 1;
        int more = r + 1;
        while (l < more) {
            if (arr[l] < p) {
                swap(arr, ++less, l++);
            } else if (arr[l] > p) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        /*
         * 划分完成之后
         * 0区域（小于区）:l到less
         * 1区域（等于区）:less+1到more-1
         * 2区域（大于区）:more到r
         */
        return new int[] { less + 1, more - 1 };
    }


    /**
     * 随机产生一个0到2的数组
     * @return
     */
    public static int[] generateArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 3);
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] test = generateArray();

        printArray(test);
        int[] res = partition(test, 0, test.length - 1, 1);
        printArray(test);
        System.out.println(res[0]);
        System.out.println(res[1]);

    }
}
