package com.algorithm.data.queue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * MyQueue.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class MyArrayQueue<T> implements Queue<T> {
    
    private Object[] queue;
    
    private final int capacity;
    
    private int size;
    
    private int startIndex;
    
    private int endIndex;
    
    public MyArrayQueue(final int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("my queue capacity must be greater than 0");
        }
        this.capacity = capacity;
        queue = new Object[capacity];
        size = 0;
        startIndex = 0;
        endIndex = 0;
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
        return Arrays.copyOf(queue, size);
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
        for (int i = 0; i < queue.length; i++) {
            queue[0] = null;
        }
    }
    
    @Override
    public boolean offer(T t) {
        if (size >= capacity) {
            throw new RuntimeException("my queue is full");
        }
        endIndex = endIndex >= capacity - 1 ? 0 : endIndex + 1;
        queue[endIndex] = t;
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
        final T t = get(startIndex);
        queue[startIndex] = null;
        startOffset();
        size--;
        return t;
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
        return get(startIndex);
    }
    
    private void startOffset() {
        startIndex++;
        if (startIndex > capacity) {
            startIndex = 0;
        }
    }
    
    @SuppressWarnings("all")
    private T get(int index) {
        return (T) queue[index];
    }
    
    public static void main(String[] args) {
        final Queue<Object> myQueue = new MyArrayQueue<>(3);
        myQueue.offer("1");
        myQueue.offer("2");
        myQueue.offer("3");
        myQueue.poll();
        myQueue.offer("3");
        
    }
    
}
