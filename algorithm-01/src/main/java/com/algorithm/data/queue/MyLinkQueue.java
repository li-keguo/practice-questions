package com.algorithm.data.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * MyQueue.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class MyLinkQueue<T> implements Queue<T> {
    private final int capacity;
    
    private int size;
    
    private Node<T> head;
    
    private Node<T> tail;
    
    
    public MyLinkQueue(final int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("my queue capacity must be greater than 0");
        }
        this.capacity = capacity;
        clear();
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean contains(Object o) {
        return false;
    }
    
    @Override
    public Iterator<T> iterator() {
        return null;
    }
    
    @Override
    public Object[] toArray() {
        return null;
    }
    
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
    
    @Override
    public boolean add(T t) {
        return offer(t);
    }
    
    @Override
    public boolean remove(Object o) {
        if (size <= capacity) {
            return false;
        }
        throw new RuntimeException("my queue not support ");
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }
    
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }
    
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }
    
    @Override
    public void clear() {
        size = 0;
        head = new Node<>();
        tail = new Node<>();
        head.post = tail;
        tail.pre = head;
    }
    
    @Override
    public boolean offer(T t) {
        if (size >= capacity) {
            throw new RuntimeException("my queue is full");
        }
        final Node<T> node = new Node<>(t);
        
        node.pre = head;
        node.post = head.post;
        head.post = node;
        node.post.pre = node;
        size++;
        return true;
    }
    
    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("not such element by my queue");
        }
        return poll();
    }
    
    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        final Node<T> node = tail.pre;
        
        tail.pre = node.pre;
        tail.pre.post = tail;
        node.pre = null;
        node.post = null;
        size--;
        return node.value;
    }
    
    @Override
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException("not such element by my queue");
        }
        return peek();
    }
    
    @Override
    public T peek() {
        return tail.pre.value;
    }
    
    static class Node<T> {
        
        private T value;
        
        protected Node<T> pre;
        
        protected Node<T> post;
        
        public Node(T value) {
            this.value = value;
        }
        
        public Node() {
        }
    }
    
    public static void main(String[] args) {
        final Queue<Object> myQueue = new MyLinkQueue<>(3);
        myQueue.offer("1");
        myQueue.offer("2");
        myQueue.offer("3");
        myQueue.poll();
        myQueue.offer("3");
        
    }
    
    
}
