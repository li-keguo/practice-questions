package com.algorithm.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/sliding-window-maximum/">滑动窗口</a>
 */
public class SlidingWindow {
    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();
        System.out.println(Arrays.toString(slidingWindow.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(slidingWindow.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(slidingWindow.maxSlidingWindow(new int[]{1}, 1)));
        System.out.println(Arrays.toString(slidingWindow.maxSlidingWindow(new int[]{1, -1}, 1)));
        System.out.println(Arrays.toString(slidingWindow.maxSlidingWindow(new int[]{7, 2, 4}, 2)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int[] res = new int[nums.length + 1 - k];
        int maxIndex = maxIndex(nums, 0, k);
        res[0] = nums[maxIndex];
        if (res.length == 1) {
            return res;
        }
        for (int i = 1; i < nums.length + 1 - k; i++) {
            maxIndex = maxIndex(nums, i, i + k, maxIndex);
            res[i] = nums[maxIndex];
        }
        return res;
    }

    private int maxIndex(int[] nums, int start, int end, int lastMaxIndex) {
        if (start - 1 != lastMaxIndex) {
            int max = Math.max(nums[lastMaxIndex], nums[end - 1]);
            return max == nums[end - 1] ? end - 1 : lastMaxIndex;
        }
        return maxIndex(nums, start, end);
    }

    private int maxIndex(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        int index = start;
        for (int i = start; i < end; i++) {
            max = Math.max(max, nums[i]);
            if (max == nums[i]) {
                index = i;
            }
        }
        return index;
    }
}
