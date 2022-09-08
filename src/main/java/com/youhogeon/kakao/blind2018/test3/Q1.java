package com.youhogeon.kakao.blind2018.test3;

import java.util.*;

public class Q1 {
    class Solution {
        public String solution(int n, int t, int m, int p) {
            Queue<Character> queue = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            int cnt = 0, turn = 0;
    
            while (true) {
                if (sb.length() == t) break;
    
                if (queue.isEmpty()) {
                    char[] chars = Integer.toString(cnt++, n).toCharArray();
    
                    for (int i = 0; i < chars.length; i++) queue.add(chars[i]);
                }
    
                char c = queue.poll();
    
                if (turn++ % m == p - 1) sb.append(c);
            }
    
            return sb.toString().toUpperCase();
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(2, 4, 2, 1));
        solution.add(s.solution(16, 16, 2, 1));
        solution.add(s.solution(16, 16, 2, 2));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}