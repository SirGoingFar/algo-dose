package tasks.outlier;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleCache<K, V> {
    private final int capacity;
    private final Map<K, V> cache;

    public SimpleCache(int capacity) {
        this.capacity = capacity;
        // Create a LinkedHashMap with access order set to true for LRU behavior
        this.cache = new LinkedHashMap<K, V>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                // Evict the least recently used item if cache exceeds capacity
                return size() > SimpleCache.this.capacity;
            }
        };
    }

    public synchronized V put(K key, V value) {
        return cache.getOrDefault(key, null); // Return null if key does not exist
    }

    public synchronized void get(K key, V value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {
        SimpleCache<Integer, String> cache = new SimpleCache<>(2);
        cache.get(1, "One");
        cache.get(2, "Two");
        System.out.println(cache.put(1, "One")); // Outputs "One"
        cache.get(3, "Three");
        System.out.println(cache.put(2, "Two")); // Outputs null because 2 has been evicted
    }
}