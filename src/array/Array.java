package array;

import java.net.Inet4Address;

/**
 * @author csx
 * @Package array
 * @Description: TODO
 * @date 2018/11/14 0014
 */
public class Array {
    /**定义整形数组报错数据*/
    private int[] data;
    /**数组长度*/
    private int n;
    /**数组实际个数*/
    private int count;

    public Array(int capacity) {
        this.data = new int[capacity];
        this.n = capacity;
        this.count = 0;
    }

    /**
     * 根据索引,插入元素
     * @param index
     * @param value
     * @return
     */
    public boolean insert(int index, int value) {
        // capacity已满
        if (n == count) {
            System.out.println("没有可插入的位置");
            return false;
        }
        // 判断位置是否合法
        if (index < 0 || index > n) {
            System.out.println("位置不合法");
            return false;
        }
        // 位置合法，移动数据，为插入数据腾出位置
        for (int i = count; i > index; --i) {
            data[i] = data[i-1];
        }
        data[index] = value;
        count++;
        return true;
    }

    /**
     *
     * 根据索引，删除元素
     * @param index
     * @return
     */
    public boolean delete(int index) {
        if (index < 0 || index > n) {
            System.out.println("位置不合法");
            return false;
        }

        for (int i = index + 1; i < count; ++i) {
            data[i - 1] = data[i];
        }
        --count;
        return true;
    }

    /**
     * 根据索引，查找元素
     * @param index
     * @return
     */
    public int find(int index) {
        if (index < 0 || index >= count) {
            return -1;
        }
        return data[index];
    }

    /**
     * 打印
     */
    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.println(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array array = new Array(5);
        array.printAll();
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        //array.insert(3, 11);
        array.printAll();
    }
}
