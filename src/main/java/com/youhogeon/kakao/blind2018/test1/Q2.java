package com.youhogeon.kakao.blind2018.test1;

import java.util.*;

public class Q2 {
    class Solution {
        public int solution(String dartResult) {
            int answer = 0, prev = 0;
    
            char[] chars = dartResult.toCharArray();
            for (int i = 0; i < chars.length; ) {
                int current = chars[i++] - '0';
                if (current == 1 && chars[i] == '0') {
                    current = 10;
                    i++;
                }
    
                if (chars[i] == 'D') current *= current;
                else if (chars[i] == 'T') current *= current * current;
    
                if (++i < chars.length) {
                    if (chars[i] == '*') {
                        i++;
                        current *= 2;
                        prev *= 2;
                    }else if (chars[i] == '#') {
                        i++;
                        current *= -1;
                    }
                }
    
                answer += prev;
                prev = current;
            }
    
            return answer + prev;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution("1S2D*3T"));
        solution.add(s.solution("1D2S#10S"));
        solution.add(s.solution("1D2S0T"));
        solution.add(s.solution("1S*2T*3S"));
        solution.add(s.solution("1D#2S*3S"));
        solution.add(s.solution("1T2D3D#"));
        solution.add(s.solution("1D2S3T*"));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}