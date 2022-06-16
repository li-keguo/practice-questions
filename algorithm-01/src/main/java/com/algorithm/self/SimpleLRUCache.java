package com.algorithm.self;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleLRUCache<T> {
    
    public static void main(String[] args) {
        final SimpleLRUCache<String> lruCache = new SimpleLRUCache<>(3);
        lruCache.put("1", "1");
        lruCache.put("2", "2");
        lruCache.put("3", "3");
        lruCache.put("4", "4");
        lruCache.print();
        lruCache.put("5", "5");
        lruCache.put("6", "6");
        lruCache.get("4");
        lruCache.put("7", "7");
        lruCache.print();
    }
    
    private void print() {
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
    
    public SimpleLRUCache(final int capacity) {
        cache = new LinkedHashMap<String, T>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }
    
    public T get(final String key) {
        return cache.getOrDefault(key, null);
    }
    
    public void put(final String key, T value) {
        cache.put(key, value);
    }
    
}
