package com.youhogeon.kakao.blind2018.test1;

import java.util.*;

public class Q1 {
    class Solution {
        public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] answer = new String[n];
    
            for (int i = 0; i < n; i++) {
                int map = arr1[i] | arr2[i];
    
                StringBuilder sb = new StringBuilder();
                
                for (int j = n - 1; j >= 0; j--) {
                    sb.append( (map & (1 << j)) == 0 ? ' ' : '#' );
                }
    
                answer[i] = sb.toString();
            }
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(5, new int[]{9,20,28,18,11}, new int[]{30,1,21,17,28}));
        solution.add(s.solution(6, new int[]{46, 33, 33 ,22, 31, 50}, new int[]{27 ,56, 19, 14, 14, 10}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}