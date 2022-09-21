package com.youhogeon.kakao.intern2022;

import java.util.*;

public class Q1 {

    class Solution {
        public String solution(String[] survey, int[] choices) {
            int RT = 0, CF = 0, JM = 0, AN = 0;
    
            for (int i = 0; i < choices.length; i++) {
                int score = choices[i] - 4;
                char c = survey[i].charAt(0);
    
                switch (c) {
                    case 'R': RT += score; break;
                    case 'T': RT -= score; break;
                    case 'C': CF += score; break;
                    case 'F': CF -= score; break;
                    case 'J': JM += score; break;
                    case 'M': JM -= score; break;
                    case 'A': AN += score; break;
                    case 'N': AN -= score; break;
                }
            }
    
            String answer = (RT <= 0 ? "R" : "T") + (CF <= 0 ? "C" : "F") + (JM <= 0 ? "J" : "M") + (AN <= 0 ? "A" : "N");
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new String[]{"AN", "CF", "MJ", "RT", "NA"},new int[]{5, 3, 2, 7, 5}));
        solution.add(s.solution(new String[]{"TR", "RT", "TR"},new int[]{7, 1, 3}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
