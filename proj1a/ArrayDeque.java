public class ArrayDeque<T> {
    private T[] items, newItems;
    private int capacity = 8;
    private int first, last;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        first = 0;
        last = 0;
    }

    public void addFirst(T item) {
        if (isfull()) {
            resize(capacity * 2);
        }
        first = (first - 1 + capacity) % capacity;
        items[first] = item;

    }

    public void addLast(T item) {
        if (isfull()) {
            resize(capacity * 2);
        }
        items[last] = item;
        last = (last + 1 + capacity) % capacity;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T res = items[first];
            if (isTooBig()) {
                resize(capacity / 4);
            }
            first = (first + 1 + capacity) % capacity;
            return res;
        }

    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T res = items[(last - 1 + capacity) % capacity];
            if (isTooBig()) {
                resize(capacity / 4);
            }
            last = (last - 1 + capacity) % capacity;
            return res;
        }
    }

    public T get(int index) {
        return items[(first + index + capacity) % capacity];
    }
    public void printDeque() {
        if (first < last) {
            for (int i = first; i < last; i++) {
                System.out.println(items[i] + " ");
            }
        } else {
            for (int i = first; i < capacity; i++) {
                System.out.println(items[i] + " ");
            }
            for (int i = 0; i < last; i++) {
                if (i == last - 1) {
                    System.out.println(items[i]);
                    break;
                }
                System.out.println(items[i] + " ");
            }
        }
    }

    public boolean isEmpty() {
        return first == last;
    }

    private boolean isfull() {
        return size() == capacity - 1;
    }

    private boolean isTooBig() {
        return size() < capacity / 4;
    }

    public int size() {
        return (last - first + capacity) % capacity;
    }


    private void resize(int newCapacity) {
        newItems = (T[]) new Object[newCapacity];
        int size = size();
        int j = 0;
        if (first < last) {
            for (int i = first; i < last; i++, j++) {
                newItems[j] = items[i];
            }
        } else {
            for (int i = first; i < capacity; i++, j++) {
                newItems[j] = items[i];
            }
            for (int i = 0; i < last; i++, j++) {
                newItems[j] = items[i];
            }
        }

        items = newItems;
        capacity = newCapacity;
        first = 0;
        last = size;
    }
}