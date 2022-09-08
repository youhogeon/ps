package com.youhogeon.kakao.blind2019;

import java.util.*;

public class Q7 {
    class Solution {
        boolean[] broken;
        int[][] board;
    
        public int solution(int[][] board) {
            broken  = new boolean[201];
            broken[0] = true;
            this.board = board;
    
            int N = board.length;
            Block[] blocks = new Block[201];
            for (int i = 1; i < 201; i++) blocks[i] = new Block();
    
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] > 0) blocks[board[i][j]].addDot(i, j);
                }
            }
    
            int answer = 0;
    
            for (int i = 1; i < 201; i++) {
                if (broken[i] || blocks[i].dotId != 4) continue;
    
                if (isTop(blocks[i].blank[0].x, blocks[i].blank[0].y) && isTop(blocks[i].blank[1].x, blocks[i].blank[1].y)) {
                    broken[i] = true;
                    answer++;
                    i = 0;
                }
            }
    
    
            return answer;
        }
    
        public boolean isTop(int x, int y) {
            for (int i = 0; i <= x; i++) {
                if (!broken[board[i][y]]) return false;
            }
    
            return true;
        }
    
        class Block {
            int dotId = 0, blankId = 0;
            int minX = 99999, minY = 99999, maxX = -1, maxY = -1;
            Dot[] dots = new Dot[4];
            Dot[] blank = new Dot[2];
    
            public void addDot(int x, int y) {
                dots[dotId++] = new Dot(x, y);
    
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
    
                if (dotId == 4) {
                    for (int i = minX; i <= maxX; i++) {
                        for (int j = minY; j <= maxY; j++) {
                            if (!check(i, j)) blank[blankId++] = new Dot(i, j);
                        }
                    }
                }
            }
    
            public boolean check(int x, int y) {
                for (int i = 0; i < 4; i++) {
                    if (dots[i] != null && dots[i].x == x && dots[i].y == y) return true;
                }
    
                return false;
            }
        }
    
        class Dot {
            int x, y;
    
            public Dot(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new int[][]{new int[]{0, 2, 0, 0}, new int[]{1, 2, 0, 4}, new int[]{1, 2, 2, 4}, new int[]{1, 1, 4, 4}}));
        solution.add(s.solution(new int[][]{new int[]{0,0,0,0,0,0,0,0,0,0},new int[]{0,0,0,0,0,0,0,0,0,0},new int[]{0,0,0,0,0,0,0,0,0,0},new int[]{0,0,0,0,0,0,0,0,0,0},new int[]{0,0,0,0,0,0,4,0,0,0},new int[]{0,0,0,0,0,4,4,0,0,0},new int[]{0,0,0,0,3,0,4,0,0,0},new int[]{0,0,0,2,3,0,0,0,5,5},new int[]{1,2,2,2,3,3,0,0,0,5},new int[]{1,1,1,0,0,0,0,0,0,5}}	));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}