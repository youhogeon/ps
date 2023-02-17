package com.youhogeon.kakaobank.blind2023;

import java.util.*;

public class Q1 {

    class Solution {
        public int[] solution(String logs) {
            String[] strs = logs.split("\n");
            int[] answer = new int[24];
    
            for (String log : strs) {
                int hour = Integer.parseInt(log.split(" ")[1].split(":")[0]);
    
                hour = (hour + 9) % 24;
    
                answer[hour]++;
            }
    
            return answer;
        }
    }
    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution("2019/05/01 00:59:19\n2019/06/01 01:35:20\n2019/08/01 02:01:22\n2019/08/01 02:01:23\n2019/08/02 03:02:35\n2019/10/03 04:05:40\n2019/10/04 06:23:10\n2019/10/10 08:23:20\n2019/10/12 08:42:24\n2019/10/23 08:43:26\n2019/11/14 08:43:29\n2019/11/01 10:19:02\n2019/12/01 11:23:10"));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
