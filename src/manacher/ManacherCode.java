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
        char[] charArr=manacherString(str);
        /*
         * 回文半径数组，用来记录关于charArr上每个元素的回文半径
         */
        int[] pArr=new int[charArr.length];
        /*
         * 最右回文边界
         * 回文字符串能够到达最右的边界
         */
        int pR=-1;
        /*
         * 最右回文边界的对称点
         */
        int pos=-1;
        /*
         * 记录最大回文长度
         */
        int max=Integer.MIN_VALUE;
        for(int i=0;i!=charArr.length;i++){

            /*
             * 下面这边操作是为了利用已经计算过的回文半径来缩短求回文半径的过程
             * 具体分析:
             * i<pR,判断i是否在最右回文边界内，不在则表明不能利用之前的回文半径，直接等于1
             * i在最右回文边界内，则可利用回文半径的对称性来缩短求回文半径的过程
             * 根据回文的对称性可知，一定有一位置j是关于pos和i对称，关于i对称的回文和关于j对称的回文,有一部分一定是对称的
             * 那么此时有分两种情况，关于j对称的回文是否在最右回边界pR关于pos对称的点内,j可用2*pos-1求得
             * 所以需要Math.min(pArr[2*pos-i],pR-i)，pArr[2*pos-i]是j的回文半径,pR-i是j回文半径能在pR关于pos对称的位置内的最大值
             */
            pArr[i]=i<pR?Math.min(pArr[2*pos-i],pR-i):1;
            /*
             * 此时pArr[i]此时已是i位置已知的回文半径
             * 可用i+pArr[i]和i-pArr[i]继续往两边扩，两边的值相等则表明回文半径可以更大pArr[i]++
             */
            while (i+pArr[i]<charArr.length&&i-pArr[i]>-1){
                if(charArr[i+pArr[i]]==charArr[i-pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            /*
             * i的回文的右边界，是否已经超过最右回文边界
             * 超过则更新pR和pos
             */
            if(i+pArr[i]>pR){
                pR=i+pArr[i];
                pos=i;
            }
            //得到最大回文
            max=Math.max(max,pArr[i]);
        }
        //max是加了"#"的最大回文半径，通过规律可知max-1即为原字符串的回文长度
        return max-1;
    }

    public static void main(String[] args) {

        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }
}
