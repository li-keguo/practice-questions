package com.algorithm.self.lru;

/**
 * Lru.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public interface Lru<T> {
    
    /**
     * get
     *
     * @param key key
     * @return
     */
    T get(final String key);
    
    /**
     * put
     *
     * @param key   key
     * @param value value
     */
    void put(String key, T value);
    
    /**
     * print
     */
    void print();
    
    
    class Node<T> {
        protected String key;
        
        protected T value;
        
        public Node(final String key, final T value) {
            this.key = key;
            this.value = value;
        }
        
        public Node() {
        }
    }
    
}
