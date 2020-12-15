package tasks.cache;

import java.util.HashMap;
import java.util.Map;

public class MyCache implements Database {
    Database slowDatabase;
    Map<String, String> cache;

    public MyCache(SlowDatabase slowDatabase) {
        this.slowDatabase = slowDatabase;
        cache = new HashMap<>();
    }

    public String get(String key) {
        String value;
        if (cache.containsKey(key)) {
            value = cache.get(key);
        } else {
            value = slowDatabase.get(key);
            cache.put(key, value);
        }
        return value;
    }
}
