package com.algorithm.test;

/**
 * LargestContainer.<br>
 * <p>
 * https://leetcode.cn/problems/container-with-most-water/submissions/
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class LargestContainer {
    public static void main(String[] args) {
        final int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
        System.out.println(maxArea2(height));
    }
    
    public static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return max;
    }
    
    public static int maxArea2(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            if (height[i] >= height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return max;
    }
}
