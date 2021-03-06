package tasks.collections;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyCollectionImpl<T> implements MyCollection<T> {

    Logger logger;
    private Entry<T> top;
    private int size;

    public MyCollectionImpl() {
        this.logger = LoggerFactory.getLogger(MyCollectionImpl.class);
    }

    public void add(T t) {
        if (t == null) {
            logger.info("Got null as argument at " + new Date());
        } else {
            Entry<T> entry = new Entry<>(t);
            bindEntries(entry, top);
            top = entry;
            size++;
        }
    }

    @Override
    public void add(int index, T t) {
        if (t == null) {
            logger.info("Got null as argument at " + new Date());
        } else {
            Entry<T> newEntry = new Entry<>(t);
            Entry<T> current = iterate(index);
            bindEntries(current.getPrevious(), newEntry);
            bindEntries(newEntry, current);
            size++;
        }
    }

    public boolean remove(T t) {
        var current = find(t);
        boolean result;
        if (current == null) {
            result = false;
        } else {
            result = true;
            var previous = current.getPrevious();
            var next = current.getNext();
            bindEntries(previous, next);
            size--;
        }
        return result;
    }

    public boolean contains(T t) {
        return find(t) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int i) {
        if (i < size && !isEmpty()) {
            return iterate(i).getData();
        }
        throw new IndexOutOfBoundsException(i + " not in bounds for size " + size);
    }

    public int size() {
        return size;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    private Entry<T> iterate(int i) {
        Entry<T> entry = top;
        for (int iter = 0; iter < i; iter++) {
            entry = entry.getNext();
        }
        return entry;

    }

    private Entry<T> find(T t) {

        Entry<T> current = top;
        while (current != null) {
            if (current.getData().equals(t)) {
                break;
            }
            current = current.getNext();
        }
        return current;
    }

    private void bindEntries(Entry<T> previous, Entry<T> next) {
        if (next != null) {
            next.setPrevious(previous);
        }
        if (previous != null) {
            previous.setNext(next);
        }

    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        if (size == 1) {
            return "[" + top.getData() + "]";
        }
        Entry<T> entry = top;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(entry.getData()).append(",");
        for (int i = 1; i < size - 1; i++) {
            entry = entry.getNext();
            stringBuilder.append(entry.getData()).append(",");
        }
        stringBuilder.append(entry.getNext().getData()).append("]");
        return stringBuilder.toString();
    }
}
