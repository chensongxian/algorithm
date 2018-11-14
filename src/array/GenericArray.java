package array;

/**
 * @author csx
 * @Package array
 * @Description: TODO
 * @date 2018/11/14 0014
 */
public class GenericArray<T> {
    private T[] data;
    private int size;

    public GenericArray(int capacity) {
        this.data = (T[]) new Object[capacity];
        size = 0;
    }

    public GenericArray() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public int count() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 修改index位置元素
     * @param index
     * @param value
     */
    public void set(int index, T value) {
        checkIndex(index);
        data[index] = value;
    }

    /**
     * 获取index位置元素
     * @param index
     * @return
     */
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * 查看是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找元素返回对应索引
     * @param e
     * @return
     */
    public int find(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 添加元素
     * @param index
     * @param e
     */
    public void add(int index, T e) {
        checkIndex(index);
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size; i > index; --i) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    /**
     * 向数组头添加元素
     * @param e
     */
    public void addFirst(T e) {
        add(0, e);
    }

    /**
     * 向数组尾添加元素
     * @param e
     */
    public void addLast(T e) {
        add(size,e);
    }

    /**
     * 根据索引删除元素
     * @param index
     * @return
     */
    public T remove(int index) {
        checkIndexForRemove(index);
        T ret = data[index];
        for (int i = index; i < size; ++i) {
            data[index] = data[index + 1];
        }
        size--;
        data[size] = null;
        if (size == data.length / 4 && data.length / 2 == 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 从数组头删除元素
     * @return
     */
    public T removeFirst() {
        return remove(0);
    }

    /**
     * 从数组尾删除元素
     * @return
     */
    public T removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除指定元素
     * @param e
     */
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }
    /**
     * 扩容
     * @param capacity
     */
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
        }
    }

    public void checkIndexForRemove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed! Require index >=0 and index < size.");
        }
    }
}
