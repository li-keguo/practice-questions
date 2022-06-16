package com.algorithm.self.lru;

/**
 * Main.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("LruCache -----");
        test(new LruCache<>(3));
        System.out.println("LinkHashMapLruCache -----");
        test(new LinkHashMapLruCache<>(3));
        System.out.println("LinkListLruCache -----");
        test(new LinkListLruCache<>(3));
    }
    
    private static void test(final Lru<Object> lruCache) {
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
}
