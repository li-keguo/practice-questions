package com.algorithm.od;


/**
 * numberOfIslands.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class NumberOfIslands {
    
    public static void main(String[] args) {
        int[][] grids = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        int count = 0;
        for (int row = 0; row < grids.length; row++) {
            for (int col = 0; col < grids[row].length; col++) {
                if (grids[row][col] == 1) {
                    destroyTheIsland(grids, row, col);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    
    private static void destroyTheIsland(int[][] grids, int row, int col) {
        
        if (row >= grids.length || row < 0 || col >= grids[0].length || col < 0) {
            return;
        }
        if (grids[row][col] == 0) {
            return;
        }
        // annihilation
        grids[row][col] = 0;
        destroyTheIsland(grids, row + 1, col);
        destroyTheIsland(grids, row - 1, col);
        destroyTheIsland(grids, row, col + 1);
        destroyTheIsland(grids, row, col - 1);
    }
}
