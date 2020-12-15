package tasks.cache;

import java.util.HashMap;

public class SlowDatabase implements Database {
    private final HashMap<String, String> map;

    public SlowDatabase() {
        map = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            map.put("Key " + i, "Value " + i);
        }
    }

    public String get(String key) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return map.get(key);
    }
}
