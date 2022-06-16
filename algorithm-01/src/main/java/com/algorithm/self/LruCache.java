package com.algorithm.self;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * LruCache.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class LruCache<T> {
    
    public static void main(String[] args) {
        final LruCache<Object> lruCache = new LruCache<>(3);
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
    
    private final ConcurrentMap<String, LruLinkedNode<T>> cache = new ConcurrentHashMap<>();
    
    private int count;
    private final int capacity;
    private final LruLinkedNode<T> head;
    private final LruLinkedNode<T> tail;
    
    public LruCache(final int capacity) {
        this.count = 0;
        this.capacity = capacity;
        
        head = new LruLinkedNode<>();
        head.pre = null;
        
        tail = new LruLinkedNode<>();
        tail.post = null;
        
        head.post = tail;
        tail.pre = head;
    }
    
    public T get(final String key) {
        LruLinkedNode<T> node = cache.get(key);
        if (node == null) {
            // should raise exception here.
            return null;
        }
        
        moveToHead(node);
        return node.value;
    }
    
    
    public void put(final String key, final T value) {
        LruLinkedNode<T> node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
            return;
        }
        
        LruLinkedNode<T> newNode = new LruLinkedNode<>(key, value);
        cache.put(key, newNode);
        addNode(newNode);
        
        ++count;
        
        if (count > capacity) {
            // 淘汰
            cache.remove(popTail().key);
            --count;
        }
    }
    
    public void print() {
        StringBuilder builder = new StringBuilder("[\n");
        LruLinkedNode<T> node = head.post;
        while (node != null && node.key != null) {
            builder.append("\t{key: ")
                    .append(node.key)
                    .append(" , value: ")
                    .append(node.value)
                    .append("}\n");
            node = node.post;
        }
        builder.append("]");
        System.out.println(builder);
    }
    
    private void addNode(final LruLinkedNode<T> node) {
        node.pre = head;
        node.post = head.post;
        
        head.post.pre = node;
        head.post = node;
    }
    
    private void removeNode(final LruLinkedNode<T> node) {
        LruLinkedNode<T> pre = node.pre;
        LruLinkedNode<T> post = node.post;
        
        pre.post = post;
        post.pre = pre;
    }
    
    private void moveToHead(final LruLinkedNode<T> node) {
        removeNode(node);
        addNode(node);
    }
    
    private LruLinkedNode<T> popTail() {
        LruLinkedNode<T> res = tail.pre;
        removeNode(res);
        return res;
    }
    
    static class LruLinkedNode<T> {
        String key;
        T value;
        LruLinkedNode<T> pre;
        LruLinkedNode<T> post;
        
        public LruLinkedNode(final String key, final T value) {
            this.key = key;
            this.value = value;
        }
        
        public LruLinkedNode() {
        }
    }
    
}
