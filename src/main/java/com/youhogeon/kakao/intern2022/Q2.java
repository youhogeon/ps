package com.youhogeon.kakao.intern2022;

import java.util.*;

public class Q2 {

    class Solution {
        public int solution(int[] queue1, int[] queue2) {
            Queue<Integer> q1 = new LinkedList<>(), q2 = new LinkedList<>();
            long sum1 = 0, sum2 = 0;
            int cnt = 0;
    
            for (int i = 0; i < queue1.length; i++) {
                sum1 += (long)queue1[i];
                q1.add(queue1[i]);
            }
            for (int i = 0; i < queue2.length; i++) {
                sum2 += (long)queue2[i];
                q2.add(queue2[i]);
            }
    
            for (int i = 0; i <= 600000; i++) {
                if (sum1 == sum2) return cnt;
                
                if (sum1 > sum2) {
                    int k = q1.poll();
                    sum1 -= (long)k;
                    sum2 += (long)k;
                    q2.add(k);
                } else {
                    int k = q2.poll();
                    sum1 += (long)k;
                    sum2 -= (long)k;
                    q1.add(k);
                }
    
                cnt++;
            }
    
            return -1;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[]{3, 2, 7, 2},new int[]{4, 6, 5, 1}));
        solution.add(s.solution(new int[]{1, 2, 1, 2},new int[]{1, 10, 1, 2}));
        solution.add(s.solution(new int[]{1, 1},new int[]{1, 5}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
