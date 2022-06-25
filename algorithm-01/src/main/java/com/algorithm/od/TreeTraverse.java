package com.algorithm.od;

import java.util.Objects;

/**
 * TreeTraverse.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class TreeTraverse {
    
    public static void main(String[] args) {
        String str = "a{b{d,e{g,h{I{x,y},I{x,y}}}},c{f}}";
        System.out.println(str);
        final BinTreeNode node = buildBinTreeNode(str);
        show(new InorderTraverse(node));
        show(new PreorderTraverse(node));
        show(new PostOrderTraverse(node));
    }
    
    private static void show(Traverse traverse) {
        traverse.traverse();
        System.out.println(traverse.result());
    }
    
    public static BinTreeNode buildBinTreeNode(String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        if (string.length() <= 1) {
            return new BinTreeNode(string.charAt(0));
        }
        BinTreeNode node = new BinTreeNode(string.charAt(0));
        string = string.substring(2, string.length() - 1);
        final String left = findLeft(string);
        node.left = buildBinTreeNode(left);
        node.right = buildBinTreeNode(findRight(left, string));
        return node;
    }
    
    public static String findLeft(String str) {
        if (str.startsWith(",")) {
            return null;
        }
        if (str.length() == 1 || str.charAt(1) == ',') {
            return str.substring(0, 1);
        }
        int count = 0;
        int end = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '{') {
                count++;
            }
            if (str.charAt(i) == '}') {
                count--;
            }
            if (count == 0) {
                end = i;
                break;
            }
        }
        return str.substring(0, end + 1);
    }
    
    public static String findRight(String left, String str) {
        if (left == null) {
            return str.substring(1);
        }
        if (Objects.equals(left, str)) {
            return null;
        }
        return str.substring(left.length() + 1);
    }
    
    
    interface Traverse {
        
        void traverse();
        
        String result();
    }
    
    abstract static class BaseTraverse implements Traverse {
        
        private final BinTreeNode node;
        
        private StringBuilder builder;
        
        public BaseTraverse(BinTreeNode node) {
            this.node = node;
        }
        
        @Override
        public void traverse() {
            builder = new StringBuilder();
            traverse(node);
        }
        
        public abstract void traverse(BinTreeNode node);
        
        
        public void consume(BinTreeNode node) {
            builder.append(node.value);
        }
        
        @Override
        public String result() {
            return builder.toString();
        }
    }
    
    static class InorderTraverse extends BaseTraverse implements Traverse {
        
        public InorderTraverse(BinTreeNode node) {
            super(node);
        }
        
        @Override
        public void traverse(BinTreeNode node) {
            if (node == null) {
                return;
            }
            traverse(node.left);
            consume(node);
            traverse(node.right);
        }
        
    }
    
    static class PreorderTraverse extends BaseTraverse implements Traverse {
        
        public PreorderTraverse(BinTreeNode node) {
            super(node);
        }
        
        @Override
        public void traverse(BinTreeNode node) {
            if (node == null) {
                return;
            }
            consume(node);
            traverse(node.left);
            traverse(node.right);
        }
    }
    
    static class PostOrderTraverse extends BaseTraverse implements Traverse {
        
        public PostOrderTraverse(BinTreeNode node) {
            super(node);
        }
        
        @Override
        public void traverse(BinTreeNode node) {
            if (node == null) {
                return;
            }
            traverse(node.left);
            traverse(node.right);
            consume(node);
        }
    }
    
    
    static class BinTreeNode {
        char value;
        BinTreeNode left;
        BinTreeNode right;
        
        public BinTreeNode(char value) {
            this.value = value;
        }
    }
}
