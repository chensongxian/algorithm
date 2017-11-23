package manacher;

/**
 * Created by Administrator on 2017/11/23.
 */
public class ManacherCode {

    /**
     * 每个字符左右都填充#,此时的字符数组就一定是一个奇数
     * @param str
     * @return
     */
    public static char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[charArr.length*2+1];
        int index = 0;
        for (int i = 0 ;i<res.length;i++){
            res[i]  = (i&1)==0?'#':charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String str){
        if(str==null||str.length()==0){
            return 0;
        }
        char[] charArr = manacherString(str);
        /*
         *  用来记录最大回文半径
         */
        int[] pArr = new int[charArr.length];
        int index = -1;
        //回文字串能到达的最右边界
        int pR = -1;
        int max = Integer.MIN_VALUE;
        for(int i =0;i!=charArr.length;i++){
            /*
             * 如果i在最右边界pR内,那么则判断i关于index对称的点j即2*index-i(代码中就是2*index-i)
             * 根据回文的对称性可以知道，关于i对称的回文和关于j对称的回文有一部分必定是相等的,由此可以得到i回文的一部分信息
             * 下一步判断j的回文半径是否超过边界，即与pR-i进行比较，如果超过回文边界，则表明超过部分不一定和i的回文相等，那么此时取pR-i即可
             * 接下来，再一步步进行判断即可，同时更新pR和index
             * 总结：manacher的关键在于利用已经计算过的回文半径来缩短求回文半径的过程
             *
             */
            pArr[i]= i<pR?Math.min(pArr[2*index-i],pR-i):1;
            while (i+pArr[i]<charArr.length&&i-pArr[i]>-1){
                if(charArr[i+pArr[i]]==charArr[i-pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max-1;
    }
    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }
}
