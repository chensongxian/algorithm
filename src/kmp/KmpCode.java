package kmp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: kmp字符串匹配
 * @Author: csx
 * @Date: 2018/01/09
 */
public class KmpCode {
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    /**
     * 求next数组
     * next数组就是求前缀和后缀的最大匹配(即公有元素的长度)
     * next[0]默认-1，next[1]默认为0
     * 具体分析:
     * "前缀"指除了最后一个字符以外，一个字符串的全部头部组合
     * "后缀"指除了第一个字符以外，一个字符串的全部尾部组合
     * 例如:
     * A B C D A B D
     * next[0]=-1
     * next[1]=0
     * next[2]=0
     * next[3]=0
     * next[4]=1
     * next[5]=2
     * next[6]=0
     *
     * @param ms
     * @return
     */
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        //匹配开始位置
        int pos = 2;
        //前缀和后缀最大匹配
        int cn = 0;
        /*
         * 快速求next关键在于利用之前求得next缩短匹配距离
         * 具体为何要cn=next[cn]可以看如下链接的博客
         * https://www.cnblogs.com/tangzhengyue/p/4315393.html
         */
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                /*
                 * 利用之前求的next数组值缩短距离
                 */
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "aba";
        System.out.println(getIndexOf(str, match));

    }
}
