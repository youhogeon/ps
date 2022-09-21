package com.youhogeon.kakao.intern2021;

import java.util.*;

public class Q1 {
    class Solution {
        public int solution(String s) {
            int answer = Integer.parseInt(s
                .replaceAll("zero", "0")
                .replaceAll("one", "1")
                .replaceAll("two", "2")
                .replaceAll("three", "3")
                .replaceAll("four", "4")
                .replaceAll("five", "5")
                .replaceAll("six", "6")
                .replaceAll("seven", "7")
                .replaceAll("eight", "8")
                .replaceAll("nine", "9"));
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution("one4seveneight"));
        solution.add(s.solution("23four5six7"));
        solution.add(s.solution("2three45sixseven"));
        solution.add(s.solution("123"));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
