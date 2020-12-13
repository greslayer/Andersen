package tasks.collections;

public interface MyCollection<T> {
    void add(T t);

    void add(int index,T t);

    void remove(T t);

    boolean contains(T t);

    boolean isEmpty();

    T get(int i);

    int size();

    void clear();
}
