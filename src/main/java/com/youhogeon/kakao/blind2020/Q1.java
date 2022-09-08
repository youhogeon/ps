package com.youhogeon.kakao.blind2020;

import java.util.*;

public class Q1 {
    class Solution {
        public int solution(String s) {
            int len = s.length();
    
            int min = len;
            for (int i = 1; i <= len / 2; i++) {
                min = Math.min(min, compress(s, i));
            }
    
            return min;
        }
    
        private int compress(String s, int len) {
            int compressed = 0;
            
            String compareStr = "";
            int compareCnt = 1;
            for (int i = 0; i < s.length(); i += len) {
                String subStr = s.substring(i, Math.min(s.length(), i + len));
    
                boolean isSame = subStr.equals(compareStr);
    
                if (isSame) {
                    compareCnt++;
                }
                
                if (compareCnt > 1 && (!isSame || i + len >= s.length())) {
                    compressed += compareStr.length() * (compareCnt - 1) - String.valueOf(compareCnt).length();
                }
    
                if (!isSame) {
                    compareStr = subStr;
                    compareCnt = 1;
                }
            }
    
            return s.length() - compressed;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution("aabbaccc"));
        solution.add(s.solution("ababcdcdababcdcd"));
        solution.add(s.solution("abcabcdede"));
        solution.add(s.solution("abcabcabcabcdededededede"));
        solution.add(s.solution("xababcdcdababcdcd"));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}