package stack_queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 之字形打印矩阵
 * @Author: csx
 * @Date: 2018-01-13
 */
public class ZigZagPrintMatrix {
    /**
     * 1 2 3 4
     * 5 6 7 8
     * 9 10 11 12
     *
     * t点和d点都从左上角出发
     * t点沿矩阵第一行移动tC++,当达到第一行最右边的元素之后，再沿着矩阵最后一列移动tR++
     * d点沿矩阵第一列移动dR++,当达到第一列最下边的元素时，再沿着矩阵最后一列移动dC++
     * t点和d点同步移动，某次的t点和d点都构成一条斜线，打印斜线即可
     * 注意每次打印方向相反，第一次打印1，第二次从2到5，第三次从9到3
     * @param matrix
     */
    public static void printMatrixZigZag(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (tR != endR + 1) {
            printLevel(matrix, tR, tC, dR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;

            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    public static void printLevel(int[][] m, int tR, int tC, int dR, int dC,
                                  boolean f) {
        if (f) {
            while (tR != dR + 1) {
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            while (dR != tR - 1) {
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
}
