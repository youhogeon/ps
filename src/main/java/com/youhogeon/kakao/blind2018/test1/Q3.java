package com.youhogeon.kakao.blind2018.test1;

import java.util.*;

public class Q3 {
    class Solution {
        public int solution(int cacheSize, String[] cities) {
            Set<String> cache = new LinkedHashSet<>();
            int answer = 0;
    
            for (int i = 0; i < cities.length; i++) {
                String city = cities[i].toLowerCase();
    
                if (cache.contains(city)) {
                    answer += 1;
                    cache.remove(city);
                }else answer += 5;
    
                cache.add(city);
    
                if (cache.size() > cacheSize) {
                    cache.remove(cache.iterator().next());
                }
            }
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        solution.add(s.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
        solution.add(s.solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        solution.add(s.solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        solution.add(s.solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
        solution.add(s.solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}