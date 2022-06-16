package com.algorithm.self.lru;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LruCache.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class LinkListLruCache<T> implements Lru<T> {
    
    @Override
    public void print() {
        StringBuilder builder = new StringBuilder("[\n");
        linkedList.forEach(value -> builder.append("\t{key: ")
                .append(value.key)
                .append(" , value: ")
                .append(value.value)
                .append("}\n"));
        builder.append("]");
        System.out.println(builder);
        
    }
    
    private final Map<String, Node<T>> cache;
    
    private final LinkedList<Node<T>> linkedList;
    
    private final int capacity;
    
    private int size;
    
    public LinkListLruCache(final int capacity) {
        cache = new ConcurrentHashMap<>();
        this.size = 0;
        this.capacity = capacity;
        linkedList = new LinkedList<>();
    }
    
    @Override
    public T get(final String key) {
        final Node<T> value = cache.get(key);
        if (Objects.nonNull(value)) {
            // move to head
            moveToHead(value);
            return value.value;
        }
        return null;
    }
    
    @Override
    public void put(final String key, final T value) {
        final Node<T> node = cache.containsKey(key) ? cache.get(key) : new Node<>(key, value);
        node.value = value;
        cache.put(key, node);
        moveToHead(node);
        size++;
        if (size > capacity) {
            final Node<T> removed = linkedList.removeLast();
            cache.remove(removed.key);
            size--;
        }
    }
    
    private void moveToHead(final Node<T> value) {
        linkedList.remove(value);
        linkedList.addFirst(value);
    }
    
    
}
