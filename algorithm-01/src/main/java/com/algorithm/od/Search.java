package com.algorithm.od;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main.
 * 给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。请你设计一个推荐系统，
 * 在依次输入单词 searchWord 的每一个字母后，推荐 products 数组中前缀与 searchWord 相同的最多三个产品。
 * 如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。请你以二维列表的形式，
 * 返回在输入 searchWord 每个字母后相应的推荐产品的列表。不考虑产品有重复的；
 * <p>
 * 示例 1：输入：products ["mobile","mouse","moneypot","monitor","mousepad"],
 * <p>
 * searchWord = "mouse"
 * <p>
 * 输出：[["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]]
 * <p>
 * 解释：按字典序排序后的产品列表是 ["mobile","moneypot","monitor","mouse","mousepad"]输入 m 和 mo，
 * <p>
 * 由于所有产品的前缀都相同，所以系统返回字典序最小的三个产品 ["mobile","moneypot","monitor"]
 * <p>
 * 输入 mou， mous 和 mouse 后系统都返回 ["mouse","mousepad"]
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class Search {
    
    public static void main(String[] args) {
        String[] products = new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        final List<String> productsT = Arrays.stream(products)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        List<List<String>> res = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : searchWord.toCharArray()) {
            List<String> temp = new ArrayList<>();
            stringBuilder.append(c);
            for (String s : productsT) {
                if (temp.size() >= 3) {
                    break;
                }
                if (s.startsWith(stringBuilder.toString())) {
                    temp.add(s);
                }
            }
            
            if (!temp.isEmpty()) {
                res.add(temp);
            }
        }
        System.out.println(res);
    }
}
