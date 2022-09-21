package com.youhogeon.kakao.intern2021;

import java.util.*;

public class Q2 {
    class Solution {
        int[] dx = new int[]{ 0, 0, 1, -1 }, dy = new int[]{ 1, -1, 0, 0 };
    
        public int[] solution(String[][] places) {
            int[] answer = new int[5];
    
            for (int i = 0; i < 5; i++) {
                char[][] map = new char[5][5];
    
                for (int j = 0; j < 5; j++) map[j] = places[i][j].toCharArray();
                
                if (validate(map)) answer[i] = 1;
            }
    
            return answer;
        }
    
        boolean validate(char[][] map) {
            Queue<Coor> P = new LinkedList<>();
    
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (map[i][j] == 'P') P.add(new Coor(i, j, -1, -1));
                }
            }
    
            Queue<Coor> near = new LinkedList<>();
            while (!P.isEmpty()) {
                Coor c = P.poll();
    
                for (int i = 0; i < 4; i++) {
                    int x = dx[i] + c.x, y = dy[i] + c.y;
    
                    if (x < 0 || y < 0 || x >= 5 || y >= 5) continue;
    
                    if (map[x][y] == 'P') return false;
                    if (map[x][y] == 'O') near.add(new Coor(x, y, c.x, c.y));
                }
            }
    
            while (!near.isEmpty()) {
                Coor c = near.poll();
    
                for (int i = 0; i < 4; i++) {
                    int x = dx[i] + c.x, y = dy[i] + c.y;
    
                    if (x < 0 || y < 0 || x >= 5 || y >= 5) continue;
                    if (x == c.orgx && y == c.orgy) continue;
    
                    if (map[x][y] == 'P') return false;
                }
            }
    
            return true;
        }
    
        class Coor {
            int x, y, orgx, orgy;
    
            public Coor(int x, int y, int orgx, int orgy) {
                this.x = x;
                this.y = y;
                this.orgx = orgx;
                this.orgy = orgy;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new String[][]{new String[]{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, new String[]{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, new String[]{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, new String[]{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, new String[]{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
