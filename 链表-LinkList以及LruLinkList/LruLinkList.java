package yang.study.com.appcompletetextviewdemo;

public class LruLinkList<T> extends LinkList<T> {

    private static int DEFAULT_SIZE = 5;
    private int listSize = DEFAULT_SIZE;
    private LinkList<T> linkList = new LinkList<>();

    public LruLinkList() {
        this(DEFAULT_SIZE);
    }

    public LruLinkList(int listSize) {
        this.listSize = listSize;
    }

    /**
     * 当存储的数据尺寸大于给定的尺寸时将会移除最后一位元素
     *
     * @param data
     */
    @Override
    public void put(T data) {
        super.put(data);
        if (size > listSize) {
            removeLast();
        }
    }

    @Override
    public void put(int index, T data) {
        super.put(index, data);
        if (size > listSize) {
            removeLast();
        }
    }

    /**
     * 将访问过的数据放到链表的头位置
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("数组越界异常");
        }

        Node preNode = head;
        Node tempNode = head;
        if (head != null) {
            //找到index-1位置的节点和index的节点
            for (int i = 0; i < index; i++) {
                preNode = tempNode;
                tempNode = tempNode.next;
            }
            //将index节点的next赋值给index-1的next
            preNode.next = tempNode.next;
            head.toString();
            //将头部节点赋值给index节点的next
            tempNode.next = head;
            //将index节点赋值给head
            head = tempNode;
        }

        return tempNode.data;
    }

    public static void main(String[] args) {
        LruLinkList lruLinkList = new LruLinkList<Integer>();
        lruLinkList.put(3);
        lruLinkList.put(2);
        lruLinkList.put(1);
        lruLinkList.toString();
        System.out.println(lruLinkList.get(2));
        lruLinkList.toString();
    }
}
