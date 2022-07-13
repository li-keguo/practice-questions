package com.algorithm.test;

/**
 * ReverseList.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class ReverseList {
    public static void main(String[] args) {
        
        final ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        
        System.out.println("res: ");
        show(head);

//        final ListNode reverse1 = reverseList(head);
//        System.out.println("\nreverse1: ");
//        show(reverse1);
        
        final ListNode reverse2 = reverseBetween(head, 2, 4);
        System.out.println("\nreverse2: ");
        show(reverse2);
    }
    
    public static void show(ListNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + "\t");
        show(head.next);
    }
    
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
    
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        return reverseBetween(head, left, right, 1);
    }
    
    public static ListNode reverseBetween(ListNode head, int left, int right, int deep) {
        if (head == null || head.next == null || deep == right) {
            return head;
        }
        ListNode last = reverseBetween(head.next, left, right, deep+1);
        if (deep >= left && deep <= right){
            ListNode t = head.next.next;
            head.next.next = head;
            head.next = t;
            return last;
        }
        if (deep == left - 1) {
            head.next = last;
        }
        
        return head;
    }
    
    public static class ListNode {
        int val;
        ListNode next;
        
        ListNode() {
        }
        
        ListNode(int val) {
            this.val = val;
        }
        
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
