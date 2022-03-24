public class ArrayDeque<T> {
    private T[] Items, newItems;
    private int capacity = 8;
    private int first, last;

    public ArrayDeque() {
        Items = (T[]) new Object[capacity];
        first = 0;
        last = 0;
    }

    public void addFirst(T item) {
        if(isfull()) {
            resize(capacity * 2);
        } else {
            first = (first - 1 + capacity) % capacity;
            Items[first] = item;
        }
    }

    public void addLast(T item) {
        if(isfull()) {
            resize(capacity * 2);
        }
        Items[last] = item;
        last = (last + 1 + capacity) % capacity;
    }

    public T removeFirst() {
        if(isEmpty()) {
            return null;
        } else {
            first = (first + 1 + capacity) % capacity;
            if (size() == 0) {
                return null;
            } else if(isTooBig()){
                resize(capacity / 4);
            }
            return Items[first];
        }

    }

    public T removeLast() {
        if(isEmpty()) {
            return null;
        } else {
            last = (last - 1 + capacity) % capacity;
            if (size() == 0) {
                return null;
            } else if(isTooBig()){
                resize(capacity / 4);
            }
                return Items[last];
            }
    }

    public T get(int index) {
        return Items[(first + index +capacity) % capacity];
    }
    public void printDeque() {
        if (first < last) {
            for (int i = first; i < last; i++){
                System.out.println(Items[i] + " ");
            }
        } else {
            for (int i = first; i < capacity; i++){
                System.out.println(Items[i] + " ");
            }

            for (int i = 0; i < last; i++) {
                System.out.println(Items[i] + " ");
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
        if (first < last) {
            int j = 0;
            for(int i = first; i < last; i++, j++) {
                newItems[j] = Items[i];
            }
        } else {
            int j = 0;
            for(int i = first; i < capacity; i++, j++) {
                newItems[j] = Items[i];
            }
            for(int i = 0; i < last; i++, j++) {
                newItems[j] = Items[i];
            }
        }

        Items = newItems;
        capacity = newCapacity;
        first = 0;
        last = size;
    }

}