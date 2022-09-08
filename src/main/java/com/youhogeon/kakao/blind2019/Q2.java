package com.youhogeon.kakao.blind2019;

import java.util.*;

public class Q2 {
    class Solution {
        public int[] solution(int N, int[] stages) {
            Stage[] objs = new Stage[N];
            for (int i = 0; i < N; i++) objs[i] = new Stage(i);
    
            for (int i = 0; i < stages.length; i++) {
                for (int j = 0; j < stages[i]; j++) {
                    if (j >= N) break;
     
                    objs[j].m++;
                }
    
                if (stages[i] <= N) objs[stages[i] - 1].n++;
            }
    
            Arrays.sort(objs);
    
            int[] answer = new int[N];
    
            for (int i = 0; i < N; i++) {
                answer[i] = objs[i].i + 1;
            }
    
            return answer;
        }
    
        class Stage implements Comparable<Stage> {
            int n = 0, m = 0, i; //n / m
    
            public Stage(int i) {
                this.i = i;
            }
    
            public double failRate() {
                if (m == 0) return 0;
    
                return (double)n / (double)m;
            }
    
            @Override
            public int compareTo(Stage o) {
                System.out.println(this.failRate());
    
                if (this.failRate() < o.failRate()) return 1;
                if (this.failRate() > o.failRate()) return -1;
    
                if (this.i > o.i) return 1;
                if (this.i < o.i) return -1;
                
                return 0;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3}));
        solution.add(s.solution(4, new int[]{4, 4, 4, 4, 4}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}