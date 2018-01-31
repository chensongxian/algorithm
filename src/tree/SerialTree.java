package tree;

import com.sun.java.swing.plaf.windows.WindowsTableHeaderUI;
import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 序列化二叉树
 * @Author: csx
 * @Date: 2018/01/31
 */
public class SerialTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }


    public static Node reconByPreString(String preStr){
        String[] values=preStr.split("!");
        Queue<String> queue=new LinkedList<>();
        for(int i=0;i<values.length;i++){
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    public static Node reconPreOrder(Queue<String> queue){
        String value=queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head=new Node(Integer.valueOf(value));
        head.left=reconPreOrder(queue);
        head.right=reconPreOrder(queue);
        return head;
    }

    public static String serialByLevel(Node head){
        if(head==null){
            return "#!";
        }
        String res=head.value+"!";
        Queue<Node> queue=new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()){
            head=queue.poll();
            if(head.left!=null){
                res+=head.left.value+"!";
                queue.offer(head.left);
            }else{
                res+="#!";
            }

            if(head.right!=null){
                res+=head.right.value+"!";
                queue.offer(head.right);
            }else{
                res+="#!";
            }
        }
        return res;
    }

    public static Node reconByLevel(String res){
        String[] values=res.split("!");
        int index=0;
        Node head=generateByString(values[index++]);

        Queue<Node> queue=new LinkedList<>();

        if(head!=null){
            queue.offer(head);
        }

        Node node=null;
        while (!queue.isEmpty()){
            node=queue.poll();
            node.left=generateByString(values[index++]);
            node.right=generateByString(values[index++]);
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return head;
    }

    public static Node generateByString(String val){
        if(val.equals("#")){
            return null;
        }
        return new Node(Integer.valueOf(val));
    }
    public static void main(String[] args) {
        Node head = new Node(12);
        head.left = new Node(3);
//        String res = serialByPre(head);
//        System.out.println(res);
//        Node reconHead=reconByPreString(res);
//        System.out.println(reconHead);


        String res=serialByLevel(head);
        System.out.println(res);
        Node reconHead=reconByLevel(res);
        System.out.println(reconHead);
    }
}
