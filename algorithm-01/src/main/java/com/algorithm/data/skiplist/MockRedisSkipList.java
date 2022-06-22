package com.algorithm.data.skiplist;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

import static com.algorithm.data.skiplist.MockRedisSkipList.SkipNode.MAX_LEVEL;

/**
 * MockRedisSkipList.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class MockRedisSkipList<T> {
    
    
    private final int capacity;
    
    private int size;
    
    private SkipNode<T> head;
    
    private SkipNode<T> tail;
    
    private Comparator<T> comparator;
    
    public MockRedisSkipList(int capacity) {
        // 默认比较
        this(capacity, (o1, o2) -> {
            if (o1 instanceof String && o2 instanceof String) {
                return (((String) o1).compareTo((String) o2));
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            return Objects.equals(o1, o2) ? 0 : Objects.hashCode(o1) - Objects.hashCode(o2);
        });
        
    }
    
    public MockRedisSkipList(int capacity, Comparator<T> comparator) {
        this.capacity = capacity;
        this.size = 0;
        // 初始化为对高层级
        head = new SkipNode<>(MAX_LEVEL);
        tail = new SkipNode<>(MAX_LEVEL);
        // 初始化头每一层指向尾部
        Arrays.fill(head.post, tail);
        // 初始化尾部每一层指向头
        Arrays.fill(tail.pre, head);
        
        
        this.comparator = comparator;
    }
    
    /**
     * 添加：插入
     *
     * @param v value
     */
    public void add(T v) {
        if (size >= capacity) {
            throw new RuntimeException("my list is full，Please add after delete");
        }
        final SkipNode<T> node = new SkipNode<>(v);
        log("插入值：" + node.value + "随机层：" + node.level);
        SkipNode<T> startNode = head;
        for (int l = 0; l < node.level; l++) {
            // 对每一层寻找插入位置：前项和后项
            SkipNode<T> preNode = findNodePoint(startNode, node, l);
            SkipNode<T> postNode = preNode.post[l];
            
            // 插入到两个节点之间
            node.post[l] = postNode;
            node.pre[l] = preNode;
            
            postNode.pre[l] = node;
            preNode.post[l] = node;
        }
        size++;
    }
    
    /**
     * 删除
     *
     * @param v value
     */
    public void remove(T v) {
        if (size <= 0) {
            return;
        }
        final SkipNode<T> node = findFirstNodeByValue(v);
        if (node == null) {
            // 没找到，不删了吧
            log("删除 " + v + " 没找到，不删了吧");
            return;
        }
        for (int l = 0; l < node.level; l++) {
            node.pre[l].post[l] = node.post[l];
            node.post[l].pre[l] = node.pre[l];
        }
        log("删除节点: " + node.value + " level : " + node.level);
        size--;
    }
    
    private SkipNode<T> findFirstNodeByValue(T v) {
        int l = MAX_LEVEL - 1;
        SkipNode<T> node = head;
        // 下一个不是最后一个
        while (node != tail) {
            if (l < 0) {
                return null;
            }
            if (Objects.equals(node.value, v)) {
                return node;
            }
            // 查找到值在当前节点和下一个节点之间，降低层级查找
            if (between(node, node.post[l], v)) {
                l--;
                continue;
            }
            node = node.post[l];
        }
        return null;
    }
    
    private boolean between(SkipNode<T> pre, SkipNode<T> post, T v) {
        if (post == tail) {
            return true;
        }
        return comparator.compare(pre.value, v) > 0 && comparator.compare(post.value, v) < 0;
    }
    
    public void show() {
        for (int l = 0; l < MAX_LEVEL; l++) {
            showOneLevel(l);
        }
    }
    
    private void showOneLevel(int l) {
        SkipNode<T> node = head.post[l];
        StringBuilder builder = new StringBuilder("head -> ");
        while (node != tail) {
            builder.append(node.value).append(" -> ");
            node = node.post[l];
        }
        builder.append("tail [level:").append(l).append("]");
        System.out.println(builder);
    }
    
    private void log(String v) {
        System.out.println(v);
    }
    
    /**
     * 寻找插入点位
     *
     * @param node 插入对元素
     * @param l    查找对层级
     * @return 插入点位
     */
    private SkipNode<T> findNodePoint(SkipNode<T> startNode, SkipNode<T> node, int l) {
        SkipNode<T> preNode = startNode;
        while (preNode.post[l] != tail) {
            preNode = preNode.post[l];
            if (comparator.compare(preNode.value, node.value) <= 0) {
                // 返回上一个节点
                return preNode.pre[l];
            }
        }
        return preNode;
    }
    
    
    static class SkipNode<T> {
        
        public static final Random RANDOM = new Random();
        
        public static final int MAX_LEVEL = 64;
        
        T value;
        
        
        /**
         * 层树
         */
        int level;
        
        
        /**
         * 前一个：数量随机
         */
        SkipNode<T>[] pre;
        
        /**
         * 后一个：数量随机
         */
        SkipNode<T>[] post;
        
        public SkipNode(T value) {
            this.value = value;
            this.level = RANDOM.nextInt(MAX_LEVEL);
            this.pre = new SkipNode[level];
            this.post = new SkipNode[level];
        }
        
        
        public SkipNode(int level) {
            this.level = level;
            this.pre = new SkipNode[level];
            this.post = new SkipNode[level];
        }
    }
    
    public static void main(String[] args) {
        
        final MockRedisSkipList<Object> skipList = new MockRedisSkipList<>(10);
        skipList.add("13");
        skipList.showOneLevel(0);
        skipList.add("73");
        skipList.showOneLevel(0);
        skipList.add("23");
        skipList.showOneLevel(0);
        skipList.add("4");
        skipList.showOneLevel(0);
        skipList.add("34");
        skipList.showOneLevel(0);
        skipList.add("734");
        skipList.showOneLevel(0);
        skipList.add("7464");
        skipList.showOneLevel(0);
        skipList.add("864");
        skipList.showOneLevel(0);
        skipList.remove("34");
        skipList.showOneLevel(0);
        skipList.add("364");
        skipList.showOneLevel(0);
        skipList.add("264");
        skipList.showOneLevel(0);
        System.out.println("========detail==========");
        skipList.show();
    }
}


