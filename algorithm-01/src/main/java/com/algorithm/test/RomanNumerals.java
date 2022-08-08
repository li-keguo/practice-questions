package com.algorithm.test;

import java.util.HashMap;
import java.util.Map;

/**
 * RomanNumerals.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class RomanNumerals {
    public static void main(String[] args) {
    
    }
    
    public static Map<Character, Integer> mapper = new HashMap<Character, Integer>() {
        {
            /**
             * I             1
             * V             5
             * X             10
             * L             50
             * C             100
             * D             500
             * M             1000
             *
             */
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };
    
    public static int romanToInt(String s) {
        final char[] chars = s.toCharArray();
        int r = 0;
        for (int i = 0; i < chars.length; ++i) {
            int value = mapper.get(chars[i]);
            if (i < chars.length - 1 && value < mapper.get(chars[i + 1])) {
                r -= value;
            } else {
                r += value;
            }
        }
        return r;
    }
}
