package com.youhogeon.kakao.blind2018.test1;

import java.util.*;

public class Q5 {

    class Solution {
        public int solution(String str1, String str2) {
            Map<Integer, Integer> map1 = new HashMap<>();
            Map<Integer, Integer> map2 = new HashMap<>();
    
            makeHashMap(map1, str1);
            makeHashMap(map2, str2);
    
            int minSum = 0, maxSum = 0;
    
            Set<Integer> keys = new HashSet<>(map1.keySet());
            keys.addAll(map2.keySet());
    
            for (int key : keys) {
                int v1 = map1.getOrDefault(key, 0);
                int v2 = map2.getOrDefault(key, 0);
    
                int min = Math.min(v1, v2), max = Math.max(v1, v2);
    
                if (min > 0) minSum += min;
                maxSum += max;
            }
    
            if (maxSum == 0) return 65536;
            return minSum * 65536 / maxSum;
        }
    
        public void makeHashMap(Map<Integer, Integer> map, String str) {
            char[] c = str.toLowerCase().toCharArray();
    
            for (int i = 0; i < c.length - 1; i++) {
                if (c[i] < 'a' || c[i] > 'z' || c[i + 1] < 'a' || c[i + 1] > 'z') continue;
    
                int key = (c[i] << 10) + c[i + 1];
    
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution("FRANCE", "french"));
        solution.add(s.solution("handshake", "shake hands"));
        solution.add(s.solution("aa1+aa2", "AAAA12"));
        solution.add(s.solution("E=M*C^2", "e=m*c^2"));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}