package com.algorithm.self.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LruCache.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class LinkHashMapLruCache<T> implements Lru<T> {
    
    @Override
    public void print() {
        StringBuilder builder = new StringBuilder("[\n");
        cache.forEach((k, value) -> builder.append("\t{key: ")
                .append(k)
                .append(" , value: ")
                .append(value)
                .append("}\n"));
        builder.append("]");
        System.out.println(builder);
    }
    
    private final LinkedHashMap<String, T> cache;
    
    public LinkHashMapLruCache(final int capacity) {
        cache = new LinkedHashMap<String, T>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }
    
    @Override
    public T get(final String key) {
        return cache.getOrDefault(key, null);
    }
    
    @Override
    public void put(final String key, T value) {
        cache.put(key, value);
    }
    
}
