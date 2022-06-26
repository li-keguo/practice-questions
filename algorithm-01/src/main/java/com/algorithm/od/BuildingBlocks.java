package com.algorithm.od;

import java.util.*;

/**
 * 分积木
 * BuildingBlocks.
 * @author keguoli
 */
public class BuildingBlocks {
    public static void main(String[] arg) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int n = Integer.parseInt(in.nextLine().trim());
            int[] arr = new int[n];
            String[] ss = in.nextLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(ss[i].trim());
                
            }
            String result = getResult(arr);
            System.out.println(result);
        }
    }
    
    private static String getResult(int[] arr) {
        if (arr.length == 0 || arr.length == 1 || (arr.length == 2 && arr[0] != arr[1])) {
            return "NO";
        }
        int min = arr[0];
        int sum = min;
        int temp = min;
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
            temp ^= arr[i];
        }
        if (temp != 0) {
            return "NO";
        } else {
            return String.valueOf(sum - min);
        }
    }
}
