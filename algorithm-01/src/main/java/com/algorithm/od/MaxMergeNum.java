package com.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.out;


/**
 * Main.
 * 小组中每位都有一张卡片
 * 卡片是6位以内的正整数
 * 将卡片连起来可以组成多种数字
 * 计算组成的最大数字
 * <p>
 * 输入描述：
 * ","分割的多个正整数字符串
 * 不需要考虑非数字异常情况
 * 小组种最多25个人
 * <p>
 * 输出描述：
 * 最大数字字符串
 * <p>
 * 示例一
 * 输入
 * 22,221
 * 输出
 * 22221
 * <p>
 * 示例二
 * 输入
 * 4589,101,41425,9999
 * 输出
 * 9999458941425101
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class MaxMergeNum {
    
    public static void main(String[] args) {
    
        try(final Scanner scanner = new Scanner(System.in)){
            out.println(Arrays.stream(scanner.nextLine().split(","))
                    .sorted((t1, t2) -> -1 * t1.compareTo(t2))
                    .collect(Collectors.joining()));
        }
    }
    
}
