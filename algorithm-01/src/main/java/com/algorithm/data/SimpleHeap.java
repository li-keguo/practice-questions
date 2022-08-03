package com.algorithm.data;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * SimpleHeap.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class SimpleHeap {
    public static void main(String[] args) {
        PriorityQueue<String> head = new PriorityQueue<>();
        LinkedList<String> linkedList = new LinkedList<>();
    }
    
    static class Heap<T> {
        private Object[] elements;
        
        private Comparator<T> comparator;
        
        private int depth;
        
        private int size;
        
        Heap(int depth) {
            if (depth < 1) {
                throw new IllegalArgumentException("depth be must greater than 0");
            }
            this.depth = depth;
            this.size = 0;
            elements = new Object[(int) (Math.pow(2, depth) - 1)];
        }
        
        /**
         * 上浮操作代码。
         * 代码中是小的元素上浮，可用于构建大根堆
         *
         * @param index index
         */
        public void swim(int index) {
            while (index > 1 && comparator.compare(get(index / 2), get(index)) > 0) {
                swap(index / 2, index);//交换
                index = index / 2;
            }
        }
        
        private void swap(int i, int index) {
            Object t = elements[i];
            elements[i] = elements[index];
            elements[index] = t;
        }
        
        
        /**
         * 返回左节点
         *
         * @param i index
         * @return index
         */
        private int leftIndex(int i) {
            return (i + 1) * 2 - 1;
        }
        
        /**
         * 返回右节点
         *
         * @param i index
         * @return index
         */
        private int rightIndex(int i) {
            return (i + 1) * 2;
        }
        
        /**
         * 返回根节点
         *
         * @param i index
         * @return index
         */
        private int parentIndex(int i) {
            // i为根结点
            if (i == 0) {
                return -1;
            }
            return (i - 1) / 2;
        }
        
        
        @SuppressWarnings("all")
        private T get(int index) {
            return (T) elements[index];
        }
        
    }
}
