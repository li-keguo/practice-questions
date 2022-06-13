package com.algorithm.od;

import java.util.Scanner;

/**
 * NoFour.
 * 程序员小明打了一辆出租车去上班。出于职业敏感，他注意到这辆出租车的计费表有点问题，总是偏大。
 * 出租车司机解释说他不喜欢数字4，所以改装了计费表，任何数字位置遇到数字4就直接跳过，其余功能都正常。<br>
 * 比如：<br>
 * 1. 23再多一块钱就变为25；<br>
 * 2. 39再多一块钱变为50；<br>
 * 3. 399再多一块钱变为500；<br>
 * 小明识破了司机的伎俩，准备利用自己的学识打败司机的阴谋。<br>
 * 给出计费表的表面读数，返回实际产生的费用。<br>
 * <br>
 * 输入描述:<br>
 * 只有一行，数字N，表示里程表的读数。
 * (1<=N<=888888888)。<br>
 * 输出描述:<br>
 * 一个数字，表示实际产生的费用。以回车结束。
 * 示例1：<br>
 * 输入<br>
 * 5
 * 输出<br>
 * 4
 * 说明<br>
 * 5表示计费表的表面读数。
 * 表示实际产生的费用其实只有4块钱。
 * <p>
 * 示例2：<br>
 * 输入<br>
 * 17
 * 输出<br>
 * 15
 * 说明<br>
 * 17表示计费表的表面读数。
 * 15表示实际产生的费用其实只有15块钱。
 * 示例3：<br>
 * 输入
 * 100<br>
 * 输出
 * 81<br>
 * 说明：100表示计费表的表面读数，81表示实际产生的费用其实只有81块钱<br>
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class NoFour {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        final int sourceN = N;
        int divValue = 0;
        // 位置
        int p = 1;
        int k = 0;
        while (N > 0) {
            int s = N % 10;
            divValue += s > 4 ? (s - 1) * k + p : s * k;
            k = k * 9 + p;
            p *= 10;
            N /= 10;
        }
        System.out.println(sourceN - divValue);
    }
    
}
