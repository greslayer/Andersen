package tasks.cache;

public interface Database<T,V> {
    V get(T key);
}
