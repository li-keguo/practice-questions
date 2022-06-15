package com.algorithm.data;

import java.util.Stack;

/**
 * StackDemo.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class StackDemo {
    
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        System.out.println(stack.search("3"));
        System.out.println(stack.search("5"));
        
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        
        
    }
}
