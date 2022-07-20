package com.algorithm.od;

/**
 * Main.
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class MaxSuffix {
    public static void main(String[] args) {
//        String[] strs = new String[]{"flower", "flow", "flight"};
        String[] strs = new String[]{"dog", "racecar", "car"};
        String res = strs[0];
        for (String str : strs) {
            int i = 0;
            for (; i < str.length() && i < res.length(); i++) {
                if (str.charAt(i) != res.charAt(i)) {
                    break;
                }
            }
            res = res.substring(0, i);
            if ("".equals(res)) {
                break;
            }
            
        }
        
        System.out.println(res);
    }
}
