package com.youhogeon.kakao.blind2021;

import java.util.*;

public class Q2 {
    class Solution {
        public String[] solution(String[] orders, int[] course) {
            Map<String, Integer> count = new HashMap<>();
    
            for (int i = 0; i < orders.length; i++) {
                int max = 1 << orders[i].length();
                char[] chars = orders[i].toCharArray();
                Arrays.sort(chars);
    
                for (int j = 1; j < max; j++) {
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < orders[i].length(); k++) {
                        if ((j & (1 << k)) > 0) sb.append(chars[k]);
                    }
    
                    String s = sb.toString();
                    count.put(s, count.getOrDefault(s, 0) + 1);
                }
            }
    
            Queue<String> result = new PriorityQueue<>();
            for (int i = 0; i < course.length; i++) {
                Queue<Tuple> tmp = new PriorityQueue<>();
                
                for (String k : count.keySet()) {
                    if (k.length() == course[i] && count.get(k) >= 2) tmp.add(new Tuple(k, count.get(k)));
                }
    
                if (tmp.isEmpty()) continue;
                int cnt = -1;
    
                while (!tmp.isEmpty() && (cnt == -1 || cnt == tmp.peek().count)) {
                    cnt = tmp.peek().count;
                    result.add(tmp.poll().str);
                }
            }
    
            String[] answer = new String[result.size()];
            int i = 0;
    
            while (!result.isEmpty()) answer[i++] = result.poll();
    
            return answer;
        }
    
        class Tuple implements Comparable<Tuple> {
            String str;
            int count;
    
            public Tuple(String str, int count) {
                this.str = str;
                this.count = count;
            }
    
            @Override
            public int compareTo(Tuple o) {
                if (o.count > this.count) return 1;
                if (o.count < this.count) return -1;
    
                if (this.str.compareTo(o.str) < 0) return 1;
                if (this.str.compareTo(o.str) > 0) return -1;
    
                return 0;
            }
    
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},new int[]{2,3,4}));
        solution.add(s.solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"},new int[]{2,3,5}));
        solution.add(s.solution(new String[]{"XYZ", "XWY", "WXA"},new int[]{2,3,4}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}