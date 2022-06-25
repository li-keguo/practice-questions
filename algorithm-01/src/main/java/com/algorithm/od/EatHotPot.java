package com.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * EatHotPot.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class EatHotPot {
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            
            // 设置一个数组，存放每道菜可以吃到的时间。
            int[] arrTime = new int[n];
            for (int i = 0; i < n; i++) {
                arrTime[i] = sc.nextInt() + sc.nextInt();
            }
            Arrays.sort(arrTime);
   
            int next = 0;
            int count = 1;
            for (int i = 1; i < n; i++) {
                if (arrTime[i] >= (arrTime[next] + m)) {
                    count++;
                    next = i;
                }
            }
            System.out.println(count);
        
        }
        
    }
   
}
