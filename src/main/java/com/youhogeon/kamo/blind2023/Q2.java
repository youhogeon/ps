package com.youhogeon.kamo.blind2023;

import java.util.*;

public class Q2 {

    class Solution {
        public int solution(String[] id_list, int k) {
            Map<String, Integer> map = new HashMap<>();
    
            for (String idStr : id_list) {
                String[] ids = idStr.split(" ");
                Set<String> idSet = new HashSet<>(Arrays.asList(ids));
    
                for (String id : idSet) {
                    map.put(id, map.getOrDefault(id, 0) + 1);
                }
            }
    
            int answer = 0;
    
            for (String key : map.keySet()) {
                answer += Math.min(k, map.get(key));
            }
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new String[]{"A B C D", "A D", "A B D", "B D"}, 2));
        solution.add(s.solution(new String[]{"JAY", "JAY ELLE JAY MAY", "MAY ELLE MAY", "ELLE MAY", "ELLE ELLE ELLE", "MAY"}, 3));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
