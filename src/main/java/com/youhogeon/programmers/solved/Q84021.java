package com.youhogeon.programmers.solved;

import java.util.*;

public class Q84021 {

    class Solution {
        int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
        int N;
    
        public int solution(int[][] game_board, int[][] table) {
            N = table.length;
    
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) table[i][j] = table[i][j] == 0 ? 1 : 0;
            }
    
            List<Block> blanks = new ArrayList<>();
            List<Block> blocks = new ArrayList<>();
    
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (game_board[i][j] == 0) {
                        Block b = new Block(blanks.size() + 2);
                        blanks.add(b);
                        spread(b, game_board, i, j);
                    }
    
                    if (table[i][j] == 0) {
                        Block b = new Block(blocks.size() + 2);
                        blocks.add(b);
                        spread(b, table, i, j);
                    }
                }
            }
    
            int answer = 0;
            for (Block block : blocks) {
                for (Block blank : blanks) {
                    if (blank.size != block.size) continue;
    
                    int cnt1 = 0, cnt2 = 0, cnt3 = 0, cnt4 = 0;
    
                    int width = blank.c2 - blank.c1, height = blank.r2 - blank.r1;
    
                    if (block.r2 - block.r1 == height && block.c2 - block.c1 == width) {
                        for (int i = 0; i <= height; i++) {
                            for (int j = 0; j <= width; j++) {
                                if (game_board[i + blank.r1][j+ blank.c1] > 1 && table[i + block.r1][j + block.c1] > 1) cnt1++;
                                if (game_board[i + blank.r1][j+ blank.c1] > 1 && table[block.r2 - i][block.c2 - j] > 1) cnt2++;
                            }
                        }
                    }
    
                    if (block.r2 - block.r1 == width && block.c2 - block.c1 == height) {
                        for (int i = 0; i <= height; i++) {
                            for (int j = 0; j <= width; j++) {
                                if (game_board[i + blank.r1][j+ blank.c1] > 1 && table[block.r2 - j][i + block.c1] > 1) cnt3++;
                                if (game_board[i + blank.r1][j+ blank.c1] > 1 && table[block.r1 + j][block.c2 - i] > 1) cnt4++;
                            }
                        }
                    }
    
                    if (cnt1 == block.size || cnt2 == block.size || cnt3 == block.size || cnt4 == block.size) {
                        answer += block.size;
                        blank.size = -1;
                        block.size = -2;
                    }
                }
            }
    
    
            return answer;
        }
    
        void spread(Block b, int[][] map, int r, int c) {
            map[r][c] = b.id;
            b.r1 = Math.min(b.r1, r);
            b.r2 = Math.max(b.r2, r);
            b.c1 = Math.min(b.c1, c);
            b.c2 = Math.max(b.c2, c);
            b.size++;
    
            for (int i = 0; i < 4; i++) {
                int newR = r + dr[i], newC = c + dc[i];
    
                if (newR < 0 || newC < 0 || newR >= N || newC >= N || map[newR][newC] > 0) continue;
    
                spread(b, map, newR, newC);
            }
        }
    
        class Block {
            int r1 = 0x7FFFFFFF, r2 = -1, c1 = 0x7FFFFFFF, c2 = -1, size = 0;
            int id;
    
            public Block(int id) {
                this.id = id;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(new int[][]{new int[]{1,1,0,0,1,0},new int[]{0,0,1,0,1,0},new int[]{0,1,1,0,0,1},new int[]{1,1,0,1,1,1},new int[]{1,0,0,0,1,0},new int[]{0,1,1,1,0,0}},new int[][]{new int[]{1,0,0,1,1,0},new int[]{1,0,1,0,1,0},new int[]{0,1,1,0,1,1},new int[]{0,0,1,0,0,0},new int[]{1,1,0,1,1,0},new int[]{0,1,0,0,0,0}})); 
        System.out.println(s.solution(new int[][]{new int[]{0,0,0},new int[]{1,1,0},new int[]{1,1,1}},new int[][]{new int[]{1,1,1},new int[]{1,0,0},new int[]{0,0,0}})); 
    }
}
