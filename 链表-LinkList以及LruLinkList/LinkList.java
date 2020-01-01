package yang.study.com.appcompletetextviewdemo;

public class LinkList<T> {
    Node head;
    /**
     * 用于记录节点个数
     */
    int size;

    /**
     * 头部添加数据
     *
     * @param data
     */
    public void put(T data) {
        if (head == null) {
            head = new Node(data);
        } else {//不为空，则新创建节点
            Node newHead = new Node(data);
            //将以前链表赋值给新建节点的next
            newHead.next = head;
            //将新建链表重新赋值给head
            head = newHead;
        }
        size++;
    }

    /**
     * 向链表最后一个位置插入数据
     *
     * @param data
     */
    public void putLast(T data) {
        Node node = new Node(data);
        head.next = node;
    }

    /**
     * 在链表的index 位置插入一个新的数据data
     *
     * @return
     */
    public void put(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("数组越界异常");
        }
        if (index == 0) {
            put(data);
        }

        if (index == size) {
            putLast(data);
        }

        Node indexNode = head;
        Node tempNode = head;
        //先找到链表中的这个节点的前一个节点
        for (int i = 1; i < index; i++) {
            indexNode = tempNode;
            tempNode = tempNode.next;
        }
        Node node = new Node(data);
        //将前一个节点的next给与node的next
        node.next = indexNode.next;
        //将node给与前一个节点的next
        indexNode.next = node;
        size++;
    }

    /**
     * 删除头部节点
     *
     * @return
     */
    public T remove() {
        if (head != null) {
            Node deleteNode;
            deleteNode = head;
            head = head.next;
            size--;
            return deleteNode.data;
        }
        return null;
    }

    /**
     * 删除头部节点
     *
     * @return
     */
    public T removeLast() {
        if (head != null) {
            Node tempHead = head;
            Node deleteNode = head;
            while (deleteNode.next != null) {
                tempHead = deleteNode;
                deleteNode = deleteNode.next;
            }
            //将倒数第二节点的next置为null,表示删除
            tempHead.next = null;
            //    head=tempHead;

            size--;
            return deleteNode.data;
        }
        return null;
    }

    /**
     * 删除指定位置节点
     *
     * @return
     */
    public T remove(int index) {
        if (head != null) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("数组越界异常");
            }

            if (index == 0) {
                remove();
            }

            if (index == size) {
                removeLast();
            }
            Node indexNode = head;
            Node tempNode = head;
            //先找到链表中的这个节点的前一个节点
            for (int i = 0; i < index; i++) {
                indexNode = tempNode;
                tempNode = tempNode.next;
            }
            Node deleteNode = indexNode.next;
            indexNode.next = deleteNode.next;
            deleteNode.next = null;
            size--;
            return deleteNode.data;
        }
        return null;
    }

    /**
     * 修改指定位置节点的数据
     *
     * @return
     */
    public void modify(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("数组越界异常");
        }
        Node modifyNode = head;
        for (int i = 0; i < index; i++) {
            modifyNode = modifyNode.next;
        }
        modifyNode.data = data;
    }

    /**
     * 查询节点，头部节点
     *
     * @return
     */
    public T get() {
        if (head != null) {
            return head.data;
        }
        return null;
    }

    /**
     * 查询指定位置的节点，头部节点
     *
     * @return
     */
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("数组越界异常");
        }

        if (head != null) {
            Node tempNode = head;
            for (int i = 0; i < index; i++) {
                tempNode = head.next;
            }
            return tempNode.data;
        }
        return null;
    }

    @Override
    public String toString() {
        Node tempNode = head;
        while (tempNode != null) {
            System.out.print(tempNode.data + " ");
            tempNode = tempNode.next;
        }
        System.out.println();
        return super.toString();
    }

    /**
     * 节点
     */
    class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        LinkList<Integer> linkList = new LinkList<>();
        linkList.put(2);
        linkList.toString();
        linkList.put(3);
        linkList.toString();
        linkList.put(4);
        linkList.toString();
        linkList.put(1, 1);
        linkList.toString();
        linkList.put(2, 5);
        linkList.toString();
        linkList.put(4, 6);
        linkList.toString();
        Integer remove = linkList.remove();
        System.out.println(remove);
        linkList.toString();
        Integer removeLast = linkList.removeLast();
        System.out.println(removeLast);
        linkList.toString();
        Integer removeIndex = linkList.remove(1);
        System.out.println(removeIndex);
        linkList.toString();
        linkList.modify(1, 1);
        linkList.toString();
        System.out.println(linkList.get());
        System.out.println(linkList.get(1));

    }
}
