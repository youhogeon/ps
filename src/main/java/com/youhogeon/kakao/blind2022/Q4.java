package com.youhogeon.kakao.blind2022;

import java.util.*;

public class Q4 {
    class Solution {
        int[] info;
        int n;
    
        public int[] solution(int n, int[] info) {
            this.info = info;
            this.n = n;
    
            Queue<Wrap> available = new PriorityQueue<>();
    
            for (int i = 1; i < 2048; i++) {
                int[] sets = makeSet(i);
                if (sets == null) continue;
    
                int score = calcScore(sets);
                if (score > 0) available.add(new Wrap(sets, score));
            }
    
            if (available.isEmpty()) return new int[]{ -1 };
    
            return available.poll().info;
        }
    
        int[] makeSet(int k) {
            int[] sets = new int[11];
            int sum = 0;
    
            for (int i = 0; i <= 10; i++) {
                if ((k & (1 << i)) > 0) {
                    sets[i] = info[i] + 1;
                    sum += sets[i];
                }
            }
    
            if (sum > n) return null;
    
            sets[10] += (n - sum);
    
            return sets;
        }
    
        int calcScore(int[] b) {
            int score = 0;
    
            for (int i = 0; i < 11; i++) {
                if (info[i] == 0 && b[i] == 0) continue;
    
                if (info[i] >= b[i]) score -= 10 - i;
                else score += 10 - i;
            }
    
            return score;
        }
    
        class Wrap implements Comparable<Wrap> {
            int[] info;
            int score;
    
            public Wrap(int[] info, int score) {
                this.info = info;
                this.score = score;
            }
    
            @Override
            public int compareTo(Wrap o) {
                if (o.score > this.score) return 1;
                if (o.score < this.score) return -1;
    
                for (int i = 10; i >= 0; i--) {
                    if (o.info[i] > this.info[i]) return 1;
                    if (o.info[i] < this.info[i]) return -1;
                }
    
                return 0;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(5,new int[]{2,1,1,1,0,0,0,0,0,0,0}));
        solution.add(s.solution(1,new int[]{1,0,0,0,0,0,0,0,0,0,0}));
        solution.add(s.solution(9,new int[]{0,0,1,2,0,1,1,1,1,1,1}));
        solution.add(s.solution(10,new int[]{0,0,0,0,0,0,0,0,3,4,3}));


        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
