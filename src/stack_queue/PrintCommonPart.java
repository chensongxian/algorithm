package stack_queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * 打印链表公共部分
 * @author: csx
 * @Date: 2018-01-20
 */
public class PrintCommonPart {
    public static class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void printCommon(Node head1,Node head2){
        System.out.println("Common part:");

        while (head1!=null&&head2!=null){
            if(head1.value<head2.value){
                head1=head1.next;
            }else if(head1.value>head2.value){
                head2=head2.next;
            }else{
                System.out.println(head1.value+" ");
                head1=head1.next;
                head2=head2.next;
            }
        }
        System.out.println();
    }

    public static void printLinkedList(Node head){
        System.out.println("Linked List: ");
        while (head!=null){
            System.out.println(head.value+" ");
            head=head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1=new Node(2);
        head1.next=new Node(3);
        head1.next.next=new Node(5);
        head1.next.next.next=new Node(6);

        Node head2=new Node(1);
        head2.next=new Node(2);
        head2.next.next=new Node(5);
        head2.next.next.next=new Node(7);
        head2.next.next.next.next=new Node(8);

        printLinkedList(head1);
        printLinkedList(head2);
        printCommon(head1,head2);
    }
}

