package tree;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * 请把一段纸条竖着放在桌子上， 然后从纸条的下边向上方对折1次， 压出折痕后展开。 此时
 * 折痕是凹下去的， 即折痕突起的方向指向纸条的背面。 如果从纸条的下边向上方连续对折2
 * 次， 压出折痕后展开， 此时有三条折痕， 从上到下依次是下折痕、 下折痕和上折痕。 给定一
 * 个输入参数N， 代表纸条都从下边向上方连续对折N次， 请从上到下打印所有折痕的方向。
 * 例如： N=1时， 打印：
 * down
 * N=2时， 打印：
 * down
 * down
 * up
 * @Author: csx
 * @Date: 2018-01-27
 */
public class PaperFolding {
    public static void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    /**
     * 从统计折叠的规律会发现，down和up刚好构成一个二叉树，左节点为down，右节点为false
     * @param i
     * @param N
     * @param down
     */
    public static void printProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? "down " : "up ");
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 4;
        printAllFolds(N);

    }
}
