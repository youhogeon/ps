package com.youhogeon.kakao.intern2020;

import java.util.*;

public class Q3 {
    class Solution {
        public int[] solution(String[] gems) {
            Set<String> kinds = new HashSet<>();
            Map<String, Integer> check = new HashMap<>();
            
            for (int i = 0; i < gems.length; i++) {
                kinds.add(gems[i]);
                check.put(gems[i], 0);
            }
            
            int N = kinds.size();
            
            int count = 0;
            int x = 0, y = 0, minLen = 0x7FFFFFFF, minX = 0, minY = 0; //[x, y)
    
            while (true) {
                if (gems.length == y) break;
    
                check.put(gems[y], check.get(gems[y]) + 1);
    
                if (check.get(gems[y]) == 1) count++;
    
                for (int i = x; i < y; i++) {
                    if (check.get(gems[i]) == 1) break;
    
                    x++;
                    check.put(gems[i], check.get(gems[i]) - 1);
                }
    
                if (count == N) {
                    int len = y - x;
    
                    if (minLen > len) {
                        minLen = len;
                        minX = x;
                        minY = y;
                    }
                }
    
                y++;
            }
    
            return new int[] { minX + 1, minY + 1 };
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new String[]{"A", "B", "A", "A", "A", "C", "A", "B"}));
        solution.add(s.solution(new String[]{"XYZ", "XYZ", "XYZ"}));
        solution.add(s.solution(new String[]{"XYZ", "A", "B"}));
        solution.add(s.solution(new String[]{"XYZ"}));
        solution.add(s.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}));
        solution.add(s.solution(new String[]{"AA", "AB", "AC", "AA", "AC"}));
        solution.add(s.solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
