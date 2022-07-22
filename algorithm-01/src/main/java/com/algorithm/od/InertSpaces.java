package com.algorithm.od;

import java.util.Arrays;

/**
 * Main.
 * 数组 spaces 描述原字符串中需要添加空格的下标。每个空格都应该插入到给定索引处的字符值 之前 。
 * <p>
 * 例如，s = "EnjoyYourCoffee" 且 spaces = [5, 9] ，那么我们需要在 'Y' 和 'C' 之前添加空格，
 * 这两个字符分别位于下标 5 和下标 9 。因此，最终得到 "Enjoy Your Coffee" 。
 * 请你添加空格，并返回修改后的字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "LeetcodeHelpsMeLearn", spaces = [8,13,15]
 * 输出："Leetcode Helps Me Learn"
 * 解释：
 * 下标 8、13 和 15 对应 "LeetcodeHelpsMeLearn" 中加粗斜体字符。
 * 接着在这些字符前添加空格。
 * 示例 2：
 * <p>
 * 输入：s = "icodeinpython", spaces = [1,5,7,9]
 * 输出："i code in py thon"
 * 解释：
 * 下标 1、5、7 和 9 对应 "icodeinpython" 中加粗斜体字符。
 * 接着在这些字符前添加空格。
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class InertSpaces {
    public static void main(String[] args) {
        
        System.out.println(Arrays.toString("   233   23 323 23  3  ".split("\\s+")));
        
        String s = "icodeinpython";
//        int[] spaces = new int[]{8,13,15};
        int[] spaces = new int[]{1, 5, 7, 9};
        
        final StringBuilder res = new StringBuilder();
        final char[] chars = s.toCharArray();
        int si = 0;
        for (int i = 0; i < chars.length; i++) {
            if (si < spaces.length && spaces[si] == i) {
                res.append(' ');
                si++;
            }
            res.append(chars[i]);
        }
        System.out.println(res);
    }
}
