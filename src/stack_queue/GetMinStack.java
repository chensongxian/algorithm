package stack_queue;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 实现一个特殊的栈，返回栈中最小的元素
 * @Author: csx
 * @Date: 2018-01-13
 */
public class GetMinStack {
     public static class MyStack1{
         private Stack<Integer> stackData;
         private Stack<Integer> stackMin;

         public MyStack1() {
             this.stackData = new Stack<>();
             this.stackMin = new Stack<>();
         }

         public void push(int newNum){
             if(stackMin.isEmpty()){
                 stackMin.push(newNum);
             }else if(newNum<=this.getMin()){
                 stackMin.push(newNum);
             }
             stackData.push(newNum);
         }

         public int pop(){
             if(this.stackData.isEmpty()){
                 throw new RuntimeException("Your stack is empty.");
             }
             int value=this.stackData.pop();
             if(value==this.getMin()){
                 this.stackMin.pop();
             }
             return value;
         }
         public int getMin(){
             if (this.stackMin.isEmpty()) {
                 throw new RuntimeException("Your stack is empty.");
             }
             return this.stackMin.peek();
         }
     }

    public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }
}
