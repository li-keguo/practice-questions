package com.algorithm.od;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * StudentSort.<br>
 * 某学校举行运动会,学生们按编号（1、2、3.....n)进行标识,<br>
 *    现需要按照身高由低到高排列，
 *    对身高相同的人，按体重由轻到重排列，
 *    对于身高体重都相同的人，维持原有的编号顺序关系。
 *    请输出排列后的学生编号
 *    输入描述：<br>
 *       两个序列，每个序列由N个正整数组成，(0<n<=100)。
 *       第一个序列中的数值代表身高，第二个序列中的数值代表体重，
 *    输出描述：<br>
 *       排列结果，每个数据都是原始序列中的学生编号，编号从1开始，
 *    实例一：<br>
 *       输入:<br>
 *        4<br>
 *        100 100 120 130<br>
 *        40 30 60 50<br>
 *       输出:<br>
 *        2134
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class StudentSort {
    
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            // id
            int n = Integer.parseInt(in.nextLine());
            // 身高
            final List<Integer> heights = readLine(in);
            // 体重
            final List<Integer> weights = readLine(in);
            System.out.println(IntStream.range(0, n)
                    .mapToObj(id -> new Stu(id + 1, heights.get(id), weights.get(id)))
                    .sorted((o1, o2) -> o1.h == o2.h ? (o1.w - o2.w) : o1.h - o2.h)
                    .map(s -> s.id + "")
                    .collect(Collectors.joining()));
        }
    }
    
    private static List<Integer> readLine(Scanner in) {
        return Arrays.stream(in.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
    
    static class Stu {
        int id;
        int h;
        int w;
        
        public Stu(int id, int h, int w) {
            this.id = id;
            this.h = h;
            this.w = w;
        }
    }
}

