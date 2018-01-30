package tree;

import java.util.concurrent.ForkJoinPool;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * morris方法实现算法复杂度达到O(n),空间复杂度达到O(1)的二叉树遍历
 * @Author: csx
 * @Date: 2018/01/30
 */
public class MorrisTraversal {

    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * Morris先序遍历
     * @param head
     */
    public static void morrisPre(Node head){
        if(head==null){
            return;
        }

        Node cur1=head;
        Node cur2=null;

        while (cur1!=null){
            cur2=cur1.left;
            if(cur2!=null){
                while (cur2.right!=null&&cur2.right!=cur1){
                    cur2=cur2.right;
                }
                if(cur2.right==null){
                    cur2.right=cur1;
                    System.out.print(cur1.value+" ");
                    cur1=cur1.left;
                    continue;
                }else{
                    cur2.right=null;
                }
            }else{
                System.out.print(cur1.value+" ");
            }
            cur1=cur1.right;
        }
        System.out.println();
    }

    /**
     * Morris遍历利用二叉树的叶节点right为null来存储上一级的父节点
     * 例如：根节点的信息存储在其左子树的最右节点的right上
     * @param head
     */
    public static void morrisIn(Node head){
        if(head==null){
            return;
        }
        Node cur1=head;
        Node cur2=null;
        while (cur1!=null){
            //cur1的左子树
            cur2=cur1.left;
            if(cur2!=null){
                //查找cur1左子树即cur2的最右节点
                while (cur2.right!=null&&cur2.right!=cur1){
                    cur2=cur2.right;
                }
                /*
                 * 假如最右节点的right为null则设置其right为cur1,将上一级的信息存储起来
                 * 否则将其置空
                 */
                if(cur2.right==null){
                    cur2.right=cur1;
                    cur1=cur1.left;
                }else{
                    cur2.right=null;
                }
            }
            System.out.print(cur2.value+" ");
            cur1=cur1.right;
        }
        System.out.println();
    }


    /**
     * morris后序遍历
     * 后序遍历的关键在于逆序打印左子树的右边界
     * @param head
     */
    public static void morrisPos(Node head){
        if(head==null){
            return;
        }

        Node cur1=head;
        Node cur2=null;
        while (cur1!=null){
            cur2=cur1.left;
            if(cur2!=null){
                while (cur2.right!=null&&cur2.right!=cur1){
                    cur2=cur2.right;
                }
                if(cur2.right==null){
                    cur2.right=cur1;
                    cur1=cur1.left;
                    continue;
                }else{
                    cur2.right=null;
                    printEdge(cur1.left);
                }
            }
            cur1=cur1.right;
        }
        printEdge(head);
        System.out.println();
    }

    /**
     * 逆序打印
     * @param head
     */
    public static void printEdge(Node head){
        Node tail=reverseEdge(head);
        Node cur=tail;
        while (cur!=null){
            System.out.print(cur.value+" ");
            cur=cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node from){
        Node pre=null;
        Node next=null;

        while (from!=null){
            next=from.right;
            from.right=pre;
            pre=from;
            from=next;
        }
        return pre;
    }

    public static void main(String[] args) {
//        Node head = new Node(5);
//        head.left = new Node(3);
//        head.right = new Node(8);
//        head.left.left = new Node(2);
//        head.left.right = new Node(4);
//        head.left.left.left = new Node(1);
//        head.right.left = new Node(7);
//        head.right.left.left = new Node(6);
//        head.right.right = new Node(10);
//        head.right.right.left = new Node(9);
//        head.right.right.right = new Node(11);


//        morrisIn(head);
        System.out.println("-------------");
//        morrisPre(head);

        Node head=new Node(4);
        head.left=new Node(2);
        head.left.right=new Node(3);
        head.left.left=new Node(1);
        head.right=new Node(6);
        head.right.left=new Node(5);
        head.right.right=new Node(7);


        morrisPos(head);
    }
}
