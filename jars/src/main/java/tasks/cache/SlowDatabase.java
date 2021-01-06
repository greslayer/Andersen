package tasks.cache;

import java.util.HashMap;

public class SlowDatabase<T, V> implements Database<T, V> {
    private final HashMap<T, V> map;

    public SlowDatabase() {
        map = new HashMap<>();
    }

    public V get(T key) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return map.get(key);
    }
}
