package com.algorithm.od;

import java.util.Scanner;

/**
 * RobotWalksTheMaze.
 *
 * @author keguoli
 */
public class RobotWalksTheMaze {
    
    /**
     * 墙
     */
    private static final int WALL = 1;
    
    /**
     * 初始状态
     */
    private static final int NONE = 0;
    
    /**
     * 标记过的/走过的
     */
    private static final int MARKED = 2;
    
    /**
     * 陷阱
     */
    private static final int TRAP = 9;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int[][] room = new int[x][y];
        int wallNum = in.nextInt();
        for (int i = 0; i < wallNum; i++) {
            int wallX = in.nextInt();
            int wallY = in.nextInt();
            room[wallX][wallY] = WALL;
        }
        // 标记
        mark(room, 0, 0);
        int trapCount = 0;
        int noMarkCount = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (room[i][j] == TRAP) {
                    trapCount += 1;
                }
                if (room[i][j] == NONE) {
                    noMarkCount += 1;
                }
            }
        }
        System.out.println(trapCount + " " + noMarkCount);
    }
    
    
    private static void mark(int[][] room, int x, int y) {
        //判断是墙直接跳过
        if (room[x][y] == WALL) {
            return;
        }
        // 已经被标记过了 ，跳过
        if (room[x][y] != 0) {
            return;
        }
        int X = room.length - 1;
        int Y = room[0].length - 1;
        // 到达终点 ，标记为走过
        if (x == X - 1 && y == Y - 1) {
            room[x][y] = MARKED;
            return;
        }
        // 向前
        if (x < X - 1) {
            mark(room, x + 1, y);
        }
        // 向下
        if (y < Y - 1) {
            mark(room, x, y + 1);
        }
        
        //该点向上/向前均为不可达/陷阱方格则为陷阱方格
        if (x == X || y == Y) {
            if (x == X && y < Y && room[x][y + 1] != MARKED) {
                room[x][y] = TRAP;
            } else if (y == Y && x < X && room[x + 1][y] != MARKED) {
                room[x][y] = TRAP;
            } else {
                room[x][y] = MARKED;
            }
            return;
        }
        // 当前位置向前一步被标记为路过，则次处标记为
        if (room[x + 1][y] == MARKED && room[x][y + 1] == MARKED) {
            room[x][y] = MARKED;
        } else {
            room[x][y] = TRAP;
        }
    }
    
}
