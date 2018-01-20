package stack_queue;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
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

    public static boolean isPalindrome3(Node head){
        if(head==null||head.next==null){
            return true;
        }
        Node right=head;
        Node cur=head;

        while (cur.next!=null&&cur.next.next!=null){
            right=right.next;
            cur=cur.next.next;
        }
        return true;
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
