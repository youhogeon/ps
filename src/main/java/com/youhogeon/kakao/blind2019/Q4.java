package com.youhogeon.kakao.blind2019;

import java.util.*;

public class Q4 {
    class Solution {
        public int solution(int[] food_times, long k) {
            int minusConst = 0, leftFood = food_times.length;
            
            Queue<Integer> list = new PriorityQueue<>();
            for (int i : food_times) list.add(i);
    
            while (!list.isEmpty()) {
                int i = list.peek();
                long sum = (long)(i - minusConst) * (long)leftFood;
    
                if (k < sum) break;
    
                k -= sum;
                minusConst = i;
                leftFood--;
                list.poll();
            }
    
            int j = 0;
            for (int i = 0; i < food_times.length; i++) {
                if (food_times[i] > minusConst) {
                    if (k % leftFood == j) return i + 1;
                    j++;
                }
            }
    
            return -1;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new int[]{1, 2, 5, 4, 5}, 1));
        solution.add(s.solution(new int[]{4, 9, 2, 7, 8, 4, 3, 2}, 20));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}