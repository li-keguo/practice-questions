package com.algorithm.test;

import java.util.Arrays;

/**
 * MaxProduct.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class MaxProduct {
    public static void main(String[] args) {
        final String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println(maxProduct(words));
        System.out.println(maxProduct2(words));
    }
    
    public static int maxProduct(String[] words) {
        int[] hash = new int[words.length];
        int max = 0;
        for (int i = 0; i < words.length; ++i) {
            for (char c : words[i].toCharArray()) {
                hash[i] |= 1 << (c - 'a');
            }
        }
        System.out.println(Arrays.toString(hash));
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((hash[i] & hash[j]) == 0) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }
    
    public static int maxProduct2(String[] words) {
        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isRepeat(words[i], words[j])) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }
    
    private static boolean isRepeat(String s1, String s2) {
        final char[] chars1 = s1.toCharArray();
        final char[] chars2 = s2.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars2.length; j++) {
                if (chars1[i] == chars2[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
