package com.youhogeon.kakao.blind2021.test1;

import java.util.*;

public class Q3 {
    class Solution {
        final int inf = 0x7FFFFFFF;
    
        public int solution(int n, int s, int a, int b, int[][] fares) {
            int[][] cost = new int[n][n];
    
            for (int i = 0; i < cost.length; i++) {
                for (int j = 0; j < cost.length; j++) {
                    cost[i][j] = i == j ? 0 : inf;
                }
            }
    
            for (int i = 0; i < fares.length; i++) {
                cost[fares[i][0] - 1][fares[i][1] - 1] = fares[i][2];
                cost[fares[i][1] - 1][fares[i][0] - 1] = fares[i][2];
            }
    
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (cost[j][i] != inf && cost[i][k] != inf) cost[j][k] = Math.min(cost[j][k], cost[j][i] + cost[i][k]);
                    }
                }
            }
    
            int min = inf;
            for (int i = 0; i < n; i++) {
                min = Math.min(min, cost[s - 1][i] + cost[i][a - 1] + cost[i][b - 1]);
            }
    
            return min;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(6,4,6,2,new int[][]{new int[]{4, 1, 10}, new int[]{3, 5, 24}, new int[]{5, 6, 2}, new int[]{3, 1, 41}, new int[]{5, 1, 24}, new int[]{4, 6, 50}, new int[]{2, 4, 66}, new int[]{2, 3, 22}, new int[]{1, 6, 25}}));
        solution.add(s.solution(7,3,4,1,new int[][]{new int[]{5, 7, 9}, new int[]{4, 6, 4}, new int[]{3, 6, 1}, new int[]{3, 2, 3}, new int[]{2, 1, 6}}));
        solution.add(s.solution(6,4,5,6,new int[][]{new int[]{2,6,6}, new int[]{6,3,7}, new int[]{4,6,7}, new int[]{6,5,11}, new int[]{2,5,12}, new int[]{5,3,20}, new int[]{2,4,8}, new int[]{4,3,9}}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}