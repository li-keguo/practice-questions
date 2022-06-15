package com.algorithm.od;

import java.util.Arrays;

/**
 * ViralInfection.<br>
 * .病毒扩散
 * 一个输入N个数满足n*n，1表示中病毒，0表示健康。每天有病毒的会感染它的上下左右的格子。求共需多少天所有格子全部感染。当输入全0或者全一的话输出-1，否则输出所需天数。
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class ViralInfection {
    
    public static void main(String[] args) {
        int[][] cells = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        if (allViralInfection(cells) || allNotViralInfection(cells)) {
            System.out.println(-1);
        }
        final int[][] temp = copy(cells);
        for (int a = 0; a < temp.length; a++) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (cells[i][j] == 1) {
                        // 开始扩散
                        diffusion(temp, cells, i, j);
                    }
                }
            }
            copy(temp, cells);
            System.out.println("第" + (a + 1) + "天");
            show(cells);
            if (allViralInfection(cells)) {
                System.out.println("第" + (a + 1) + "感染完成");
                return;
            }
        }
    }
    
    private static void diffusion(int[][] temp, int[][] cells, int i, int j) {
        if (i + 1 < cells.length) {
            temp[i + 1][j] = 1;
        }
        if (j + 1 < cells[i].length) {
            temp[i][j + 1] = 1;
        }
        if (i - 1 >= 0) {
            temp[i - 1][j] = 1;
        }
        if (j - 1 >= 0) {
            temp[i][j - 1] = 1;
        }
    }
    
    public static boolean allViralInfection(int[][] cells) {
        return isAllEqual(cells, 1);
    }
    
    public static boolean allNotViralInfection(int[][] cells) {
        return isAllEqual(cells, 0);
    }
    
    public static boolean isAllEqual(int[][] cells, int target) {
        for (int[] cell : cells) {
            for (int col : cell) {
                if (col != target) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void show(int[][] cells) {
        for (int[] cell : cells) {
            System.out.println(Arrays.toString(cell));
        }
        
    }
    
    public static int[][] copy(int[][] cells) {
        return copy(cells, new int[cells.length][cells[0].length]);
    }
    
    public static int[][] copy(int[][] cells, int[][] cellT) {
        
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cellT[i][j] = cells[i][j];
            }
        }
        return cellT;
    }
}
