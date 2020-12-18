package tasks.cache;

import java.util.*;

public class MyCache<T, V> implements Database<T, V> {
    Deque<String> strings = new ArrayDeque<>();
    Database<T, V> slowDatabase;
    Map<T, V> cache;
    private final LinkedHashSet<T> lastUsed;
    private int currentSize;
    private final int maxSize;

    public MyCache(SlowDatabase<T, V> slowDatabase, int maxSize) {
        this.slowDatabase = slowDatabase;
        cache = new HashMap<>();
        lastUsed = new LinkedHashSet<>();
        currentSize = 0;
        this.maxSize = maxSize;
    }

    public V get(T key) {
        V value;
        if (cache.containsKey(key)) {
            lastUsed.remove(key);
            value = cache.get(key);
        } else {
            value = getFromDB(key);
        }
        lastUsed.add(key);
        return value;
    }

    private V getFromDB(T key) {
        V value = slowDatabase.get(key);
        adjustSize();
        cache.put(key, value);
        currentSize++;
        return value;
    }

    private void adjustSize() {
        if (currentSize >= maxSize) {
            T toDelete = lastUsed.iterator().next();
            cache.remove(toDelete);
            lastUsed.remove(toDelete);
            currentSize--;
        }
    }


}
