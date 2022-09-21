package com.youhogeon.kakao.intern2022;

import java.util.*;

public class Q3 {
    class Solution {
        public int solution(int alp, int cop, int[][] problems) {
            int maxAlp = -1, maxCop = -1;
            int[][] ps = new int[problems.length + 2][5];
            boolean[][] visited = new boolean[2000][2000];
    
    
            for (int i = 0; i < problems.length; i++) {
                maxAlp = Math.max(maxAlp, problems[i][0]);
                maxCop = Math.max(maxCop, problems[i][1]);
    
                ps[i] = problems[i];
            }
    
            ps[ps.length - 2] = new int[]{0, 0, 1, 0, 1};
            ps[ps.length - 1] = new int[]{0, 0, 0, 1, 1};
    
            Queue<Q> queue = new PriorityQueue<>();
            queue.add(new Q(alp, cop, 0));
    
            while (!queue.isEmpty()) {
                Q q = queue.poll();
                
                if (visited[q.alp][q.cop]) continue;
                if (q.alp >= maxAlp && q.cop >= maxCop) return q.cost;
    
                for (int i = 0; i < ps.length; i++) {
                    if (ps[i][0] > q.alp || ps[i][1] > q.cop) continue;
    
                    queue.add(new Q(q.alp + ps[i][2], q.cop + ps[i][3], q.cost + ps[i][4]));
                }
    
                visited[q.alp][q.cop] = true;
            }
    
            return -1;
        }
    
        class Q implements Comparable<Q>{
            int cost, alp, cop;
    
            public Q(int alp, int cop, int cost) {
                this.alp = alp;
                this.cop = cop;
                this.cost = cost;
            }
    
            @Override
            public int compareTo(Q o) {
                if (o.cost < this.cost) return 1;
                if (o.cost > this.cost) return -1;
                return 0;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(10, 10, new int[][]{new int[]{10,15,2,1,2},new int[]{20,20,3,3,4}}));
        solution.add(s.solution(0, 0, new int[][]{new int[]{0,0,2,1,2},new int[]{4,5,3,1,2},new int[]{4,11,4,0,2},new int[]{10,4,0,4,2}}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
