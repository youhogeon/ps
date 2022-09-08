package com.youhogeon.kakao.blind2020;

import java.util.*;

public class Q5 {
    class Solution {
        Dot[][] dots;
        int N;
    
        public int[][] solution(int n, int[][] build_frame) {
            N = n + 1;
            dots = new Dot[N][N];
    
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dots[i][j] = new Dot();
                }
            }
    
            int cnt = 0;
            for (int i = 0; i < build_frame.length; i++) {
                int x = build_frame[i][0], y = build_frame[i][1], a = build_frame[i][2], b = build_frame[i][3];
    
                if (a == 0 && b == 1) { //기둥 설치
                    if (!check기둥(x, y)) continue;
    
                    dots[x][y].기둥 = true;
                    cnt++;
                } else if (a == 1 && b == 1) { //보 설치
                    if (!check보(x, y)) continue;
    
                    dots[x][y].보 = true;
                    cnt++;
                } else if (a == 0) { //기둥 제거
                    dots[x][y].기둥 = false;
                    cnt--;
    
    
                    if (exist기둥(x, y + 1) && !check기둥(x, y + 1) || exist보(x, y + 1) && !check보(x, y + 1) || exist보(x - 1, y + 1) && !check보(x - 1, y + 1)) {
                        dots[x][y].기둥 = true;
                        cnt++;
                    }
                } else { //보 제거
                    dots[x][y].보 = false;
                    cnt--;
    
                    if (exist기둥(x, y) && !check기둥(x, y) || exist기둥(x + 1, y) && !check기둥(x + 1, y) || exist보(x - 1, y) && !check보(x - 1, y) || exist보(x + 1, y) && !check보(x + 1, y)) {
                        dots[x][y].보 = true;
                        cnt++;
                    }
                }
            }
    
            int[][] result = new int[cnt][3];
            int idx = 0;
    
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dots[i][j].기둥) result[idx++] = new int[]{i, j, 0};
                    if (dots[i][j].보) result[idx++] = new int[]{i, j, 1};
                }
            }
    
            return result;
        }
    
        boolean exist기둥(int x, int y) {
            if (x < 0 || y < 0 || x >= N || y >= N) return false;
    
            return dots[x][y].기둥;
        }
    
        boolean exist보(int x, int y) {
            if (x < 0 || y < 0 || x >= N || y >= N) return false;
    
            return dots[x][y].보;
        }
    
        boolean check기둥(int x, int y) {
            if (y == 0 || dots[x][y].보 || (x > 0 && dots[x - 1][y].보) || (y > 0 && dots[x][y - 1].기둥)) return true;
    
            return false;
        }
    
        boolean check보(int x, int y) {
            if (y > 0 && dots[x][y - 1].기둥 || (y > 0 && x < N - 1 && dots[x + 1][y - 1].기둥) || (x > 0 && x < N - 1 && dots[x - 1][y].보 && dots[x + 1][y].보)) return true;
    
            return false;
        }
    
        class Dot {
            boolean 기둥, 보;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(5, new int[][]{new int[]{1,0,0,1},new int[]{1,1,1,1},new int[]{2,1,0,1},new int[]{2,2,1,1},new int[]{5,0,0,1},new int[]{5,1,0,1},new int[]{4,2,1,1},new int[]{3,2,1,1}}	));
        solution.add(s.solution(5, new int[][]{new int[]{0,0,0,1},new int[]{2,0,0,1},new int[]{4,0,0,1},new int[]{0,1,1,1},new int[]{1,1,1,1},new int[]{2,1,1,1},new int[]{3,1,1,1},new int[]{2,0,0,0},new int[]{1,1,1,0},new int[]{2,2,0,1}}	));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}