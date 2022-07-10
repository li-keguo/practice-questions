package com.algorithm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ThreeSum.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class ThreeSum {
    public static void main(String[] args) {
//        final int[] nums = {-1, 0, 1, 2, -1, -4};
//        final int[] nums = {0, 0, 0, 0};
        final int[] nums = {-2, 0, 1, 1, 2};
        System.out.println(threeSum(nums));
    }
    
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        if (nums[nums.length - 1] < 0 || nums[0] > 0 || nums.length < 3) {
            return res;
        }
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                final int sum = nums[i] + nums[j];
                final int index = Arrays.binarySearch(nums, j + 1, nums.length, -sum);
                if (index > 1 && index != i && index != j) {
                    final ArrayList<Integer> v = of(nums[i], nums[j], nums[index]);
                    if (!res.contains(v)) {
                        res.add(v);
                    }
                    
                }
            }
            if (nums[i] >= 0) {
                break;
            }
        }
        return res;
    }
    
    private static ArrayList<Integer> of(Integer... t) {
        final ArrayList<Integer> v = new ArrayList<>(Arrays.asList(t));
        v.sort(Integer::compareTo);
        return v;
    }
    
}
