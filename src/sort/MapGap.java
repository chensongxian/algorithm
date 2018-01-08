package sort;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 给定一个未排序的数组，返回其排序后的数组中 相邻元素之差 最大的值
 * @Author: csx
 * @Date: 2018/01/08
 */
public class MapGap {
    public static int maxGap(int[] nums){
        int minLength=2;
        if(nums==null||nums.length<minLength){
            return 0;
        }
        int len=nums.length;
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            max=nums[i]>max?nums[i]:max;
            min=nums[i]<min?nums[i]:min;
        }
        if(max==min){
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        int i = 1;
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }
}
