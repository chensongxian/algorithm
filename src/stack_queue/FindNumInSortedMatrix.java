package stack_queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * 在行列都排好序的矩阵中找数
 * @author: csx
 * @Date: 2018-01-20
 */
public class FindNumInSortedMatrix {
    /**
     * 从右上角往左找，比k小则往下找
     * @param matrix
     * @param k
     * @return
     */
    public static boolean isContains(int[][] matrix,int k){
        int row=0;
        int col=matrix[0].length-1;
        while (row<matrix.length&&col>-1){
            if(matrix[row][col]==k){
                return true;
            }else if(matrix[row][col]>k){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }
}
