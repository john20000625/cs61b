public class ArrayDeque<T> {
    private T[] Items, newItems;
    private int capacity = 8;
    private int first, last;

    public ArrayDeque() {
        Items = (T[]) new Object[capacity];
        this.first = this.last = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        capacity = other.size();
        Items = (T[]) new Object[capacity];
        for (int i = other.first; i <= other.last; i++){
            this.Items[i] = (T) other.get(i);
        }
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(Items);
        }
        for (int i = last; i <= first; i--) {
            Items[i + 1] = Items[i];
        }
        Items[first] = item;
        last ++;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(Items);
        }
        Items[last + 1] = item;
        last ++;
    }

    public void removeFirst(T item) {
        for (int i = first + 1; i <= last; i++) {
            Items[i - 1] = Items[i];
        }
        if(isTooBig()) {
            downsize(Items);
        }
    }

    public T get(int i) {
        if (i > last) {
            return null;
        } else {
            return Items[i];
        }
    }

    public int size() {
        return last + 1;
    }

    private boolean isFull() {
        return last == capacity - 1;
    }

    private void resize(T[] Items) {
        newItems = (T[]) new Object[capacity * 4];
        System.arraycopy(Items, 0, newItems, 0, last - first + 1);
        this.Items = newItems;
        this.capacity *= 4;
    }

    private boolean isTooBig() {
        return last / capacity < 0.25;
    }

    private void downsize(T[] Items) {
        newItems = (T[]) new Object[capacity / 4];
        System.arraycopy(Items, 0, newItems, 0, last - first + 1);
        this.Items = newItems;
        this.capacity /= 4;
    }

}