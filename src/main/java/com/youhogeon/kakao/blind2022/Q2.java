package com.youhogeon.kakao.blind2022;

import java.util.*;
public class Q2 {
    class Solution {
        public int solution(int n, int k) {
            String N = Integer.toString(n, k);
            String[] strs = N.split("0");
    
            int answer = 0;
    
            for(String str : strs) {
                if (isPrime(str)) answer++;
            }
    
            return answer;
        }
    
        boolean isPrime(String str)  {
            if (str.length() == 0) return false;
    
            long k = Long.parseLong(str);
    
            if (k <= 1) return false;
    
            double b = Math.sqrt(k);
            for (long i = 2; i <= b ; i++) {
                if (k % i == 0) return false;
            }
    
            return true;
        }
    }

    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(2391484, 3));
        solution.add(s.solution(437674, 3));
        solution.add(s.solution(110011, 10));


        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
