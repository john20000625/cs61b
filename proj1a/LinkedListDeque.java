public class LinkedListDeque<T> {
    public class Node {
        private Node prev;
        private T value;
        private Node next;
        private Node(T value, Node prev, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node((T) new Object(), null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

//    public LinkedListDeque(LinkedListDeque other) {
//        Node p = other.sentinel.next;
//        while (p != other.sentinel) {
//            addLast(p.value);
//            p = p.next;
//        }
//    }

    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.next == sentinel) {
                System.out.println(p.value);
                break;
            } else {
                System.out.println(p.value + " ");
                p = p.next;
            }
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T res = sentinel.next.value;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return res;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T res = sentinel.prev.value;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return res;
        }
    }

    public T get(int index) {
        if (index > size) {
            return null;
        } else {
            Node p = sentinel.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.value;
        }
    }

    public T getRecursive(int index) {
        if (index > size) {
            return null;
        } else {
            return getRecursive(index, sentinel.next);
        }
    }

    private T getRecursive(int index, LinkedListDeque<T>.Node p) {
        if (index == 0) {
            return p.value;
        } else {
            return getRecursive(index - 1, p.next);
        }
    }
}