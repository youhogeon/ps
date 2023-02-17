package com.youhogeon.kamo.blind2023;

import java.util.*;

public class Q1 {

    class Solution {
        public int solution(int[][] flowers) {
            boolean[] days = new boolean[365];
    
            for (int[] flower : flowers) {
                for (int j = flower[0]; j < flower[1]; j++) days[j] = true;
            }
    
            int answer = 0;
            for (boolean day : days) {
                if (day) answer++;
            }
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{4, 5}}));
        solution.add(s.solution(new int[][]{new int[]{3, 4},new int[]{4, 5}, new int[]{6, 7}, new int[]{8, 10}}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
