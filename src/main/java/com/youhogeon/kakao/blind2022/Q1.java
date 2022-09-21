package com.youhogeon.kakao.blind2022;

import java.util.*;

public class Q1 {
    class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            Map<String, Integer> ids = new HashMap<>();
            Set<String> logs = new HashSet<>();
            Map<Integer, Integer> count = new HashMap<>();
    
            for (int i = 0; i < id_list.length; i++) {
                ids.put(id_list[i], i);
            }
    
            for (int i = 0; i < report.length; i++) {
                if (logs.contains(report[i])) continue;
                logs.add(report[i]);
    
                String[] strs = report[i].split(" ");
                int a = ids.get(strs[0]), b = ids.get(strs[1]);
    
                count.put(b, count.getOrDefault(b, 0) + 1);
            }
    
            logs = new HashSet<>();
            int[] answer = new int[id_list.length];
    
            for (int i = 0; i < report.length; i++) {
                if (logs.contains(report[i])) continue;
                logs.add(report[i]);
    
                String[] strs = report[i].split(" ");
                int a = ids.get(strs[0]), b = ids.get(strs[1]);
    
                if (count.getOrDefault(b, 0) >= k) answer[a]++;
            }
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new String[]{"muzi", "frodo", "apeach", "neo"},new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},2));
        solution.add(s.solution(new String[]{"con", "ryan"},new String[]{"ryan con", "ryan con", "ryan con", "ryan con"},3));


        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
