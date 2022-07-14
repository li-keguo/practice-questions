package com.algorithm.test;

import java.util.LinkedList;

/**
 * Test.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class Test {
    public static void main(String[] args) {
        final BinTreeNode root = new BinTreeNode(1,
                new BinTreeNode(2, null, null),
                new BinTreeNode(3,
                        new BinTreeNode(4, null, null),
                        null));
        System.out.println("inorderTraverse");
        inorderTraverse(root);
        System.out.println("postOrderTraverse");
        postOrderTraverse(root);
        System.out.println("preorderTraverse");
        preorderTraverse(root);
        System.out.println("levelTravel");
        levelTravel(root);
    }
    
    static void inorderTraverse(BinTreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraverse(node.left);
        System.out.println(node.val);
        inorderTraverse(node.right);
    }
    
    static void preorderTraverse(BinTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        inorderTraverse(node.left);
        inorderTraverse(node.right);
    }
    
    static void postOrderTraverse(BinTreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraverse(node.left);
        inorderTraverse(node.right);
        System.out.println(node.val);
    }
    
    static void levelTravel(BinTreeNode node) {
        if (node == null) {
            return;
        }
        LinkedList<BinTreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            final BinTreeNode pop = queue.pop();
            System.out.println(pop.val);
            if (pop.left != null) {
                queue.add(pop.left);
            }
            if (pop.right != null) {
                queue.add(pop.right);
            }
        }
    }
    
    Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        final Node last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
    
    class Node {
        int val;
        Node next;
    }
    
    static class BinTreeNode {
        int val;
        BinTreeNode left;
        BinTreeNode right;
        
        public BinTreeNode(int val, BinTreeNode left, BinTreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
