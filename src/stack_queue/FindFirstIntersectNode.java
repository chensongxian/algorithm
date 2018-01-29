package stack_queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 求两个单链表相交的第一个节点
 * @Author: csx
 * @Date: 2018-01-27
 */
public class FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 单链表的相交，只有两种情况
     * 1：无环链表和无环链表的相交
     * 2：两个有环链表的相交
     *
     * 无环链表和有环链表不可能相交
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        /*
         * 处理两个无环链表的相交
         */
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        /*
         * 处理两个有环链表的相交
         */
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }


    /**
     * 得到有环链表的相交节点
     *
     * 快指针走两步，慢指针走一步，他们一定会相遇
     * 此时再让快指针从头节点开始走，他们一定会在入环节点相遇
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        //慢指针
        Node n1 = head.next;
        //快指针
        Node n2 = head.next.next;
        /*
         * 快指针走两步，慢指针走一步，两者一定在入环节点相遇
         */
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        /*
         * 快指针从头部开始走,此时每次走一步，两者一定会在入环节点相遇
         */
        n2 = head;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    /**
     * 两个无环链表相交
     *
     * 两个无环链表的相交也分为两种情况
     * 1：一个单链表在另一个单链表里面
     * 2：两个单链表成树杈形状
     *
     * 值得注意的是不管哪种情况，两者相遇节点到尾节点一定是重合的
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        /*
         * 算出链表一的步长n
         */
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        /*
         * 遍历链表二同时n--，得到的n便是两者的步长差
         */
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        //取长的链表
        cur1 = n > 0 ? head1 : head2;
        //取短的链表
        cur2 = cur1 == head1 ? head2 : head1;
        //取差值
        n = Math.abs(n);
        /*
         * 先让步长长的链表，走两者步长差的步数
         */
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        /*
         * 此时两者同时走，直到第一个相等的节点，这个节点一定是第一个相交节点
         */
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个有环链表的相交，有一定是可以肯定的就是两个有环链表的环一定是重合的
     * 他们相交也有两种情况
     * 1：相交节点不在环上，环外部形成一个树杈
     * 2：相交节点在环上，形状类似两个6，环部分重合
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        /*
         * 入环节点相同，这是上述第一种情况，两者在环外相遇
         */
        if (loop1 == loop2) {
            /**
             * 和无环链表相似
             */
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            /*
             * 第二种情况，与环相交的有两个节点，任何一个节点都是相交节点
             */
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

}
