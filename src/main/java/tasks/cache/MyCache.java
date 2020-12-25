package tasks.cache;

import java.util.*;

public class MyCache<T, V> implements Database<T, V> {
    Database<T, V> slowDatabase;
    LinkedHashMap<T, V> cache;


    public MyCache(SlowDatabase<T, V> slowDatabase, int maxSize) {
        this.slowDatabase = slowDatabase;
        cache = new LinkedHashMap<>(maxSize);
    }

    public V get(T key) {
        V value;
        if (cache.containsKey(key)) {
            value = cache.get(key);
            cache.remove(key);
        } else {
            value = getFromDB(key);
        }
        cache.put(key, value);
        return value;
    }

    private V getFromDB(T key) {
        V value = slowDatabase.get(key);
        cache.put(key, value);
        return value;
    }


}
