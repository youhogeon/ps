package com.youhogeon.kakao.blind2022;

import java.util.*;

public class Q6 {
    class Solution {
        public int solution(int[][] board, int[][] skill) {
            int[][] sum = new int[board.length + 1][board[0].length + 1];
    
            for (int i = 0; i < skill.length; i++) {
                int degree = skill[i][5] * ((skill[i][0] == 1) ? -1 : 1);
                sum[skill[i][1]][skill[i][2]] += degree;
                sum[skill[i][1]][skill[i][4] + 1] -= degree;
                sum[skill[i][3] + 1][skill[i][2]] -= degree;
                sum[skill[i][3] + 1][skill[i][4] + 1] += degree;
            }
    
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    sum[i][j + 1] += sum[i][j];
                }
            }
    
            for (int i = 0; i < board[0].length; i++) {
                for (int j = 0; j < board.length; j++) {
                    sum[j + 1][i] += sum[j][i];
                }
            }
            
            int answer = 0;
    
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (sum[i][j] + board[i][j] > 0) answer++;
                }
            }
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[][]{new int[]{5,5,5,5,5},new int[]{5,5,5,5,5},new int[]{5,5,5,5,5},new int[]{5,5,5,5,5}},new int[][]{new int[]{1,0,0,3,4,4},new int[]{1,2,0,2,3,2},new int[]{2,1,0,3,1,2},new int[]{1,0,1,3,3,1}}	));
        solution.add(s.solution(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,9}},new int[][]{new int[]{1,1,1,2,2,4},new int[]{1,0,0,1,1,2},new int[]{2,2,0,2,0,100}}));


        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
