package stack_queue;

import javax.print.attribute.standard.NumberUp;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 回文链表
 * @author: csx
 * @Date: 2018-01-20
 */
public class IsPalindromeList {
    public static class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 把链表节点压入栈，再把栈倒出来和原链表比较
     * 需要为n的额外空间
     * @param head
     * @return
     */
    public static boolean isPalindrome1(Node head){
        Stack<Node> stack=new Stack<>();
        Node cur=head;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        while (head!=null){
            if(head.value!=stack.pop().value){
                return false;
            }
            head=head.next;
        }
        return true;
    }

    /**
     * 找到中间节点，只需将右半部分做对比就可以了
     * 需要n/2的额外空间
     * @param head
     * @return
     */
    public static boolean isPalindrome2(Node head){
        if(head==null||head.next==null){
            return true;
        }

        Node right=head.next;
        Node cur=head;
        while (cur.next!=null&&cur.next.next!=null){
            right=right.next;
            cur=cur.next.next;
        }

        Stack<Node> stack=new Stack<>();
        while (right!=null){
            stack.push(right);
            right=right.next;
        }
        while (!stack.isEmpty()){
            if(head.value!=stack.pop().value){
                return false;
            }
            head=head.next;
        }
        return true;
    }

    /**
     * 改变右半区链表结构，再把左右半区链表进行比较
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome3(Node head){
        if(head==null||head.next==null){
            return true;
        }
        Node n1=head;
        Node n2=head;
        //找到中间节点
        while (n2.next!=null&&n2.next.next!=null){
            n1=n1.next;//中间节点
            n2=n2.next.next;//结尾
        }
        n2=n1.next;//右半部分节点
        n1.next=null;//把中间节点指向null
        Node n3=null;
        while (n2 != null) { // 右半部分反转
            n3 = n2.next; // n3 -> 保存下一个节点
            n2.next = n1; // 下一个反转节点
            n1 = n2; // n1 移动
            n2 = n3; // n2 移动
        }

        n3 = n1; // n3 -> 保存最后一个节点
        n2 = head;// n2 -> 左边第一个界定啊
        boolean res = true;
        while (n1 != null && n2 != null) { // 检查回文
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next; // 从左到中部
            n2 = n2.next; // 从右到中部
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) { // 恢复链表
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }



    public static void printLinkedList(Node node){
        System.out.println("Linked List: ");
        while (node!=null){
            System.out.println(node.value+" ");
            node=node.next;
        }
        System.out.println();
    }
}
