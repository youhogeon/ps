package com.youhogeon.kakao.blind2020;

import java.util.*;

public class Q6 {

    class Solution {
        int n;
        Map<String, Integer> cache = new HashMap<>();
    
        public int solution(int n, int[] weak, int[] dist) {
            this.n = n;
    
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < weak.length; i++) l.add(weak[i]);
    
            int answer = calc(l, dist);
    
            return answer > 8 ? -1 : answer;
        }
    
        int calc(List<Integer> l, int[] dist) {
            if (l.size() == 0) return 0;
            if (dist.length == 0) return 100000;
    
            String key = l.toString() + Arrays.toString(dist);
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
    
            int min = 0x7FFFFFFF;
            int[] newDist = Arrays.copyOfRange(dist, 0, dist.length - 1);
            int currentDist = dist[dist.length - 1];
    
            for (int begin : l) {
                List<Integer> l1 = new ArrayList<>();
                List<Integer> l2 = new ArrayList<>();
    
                int end1 = (currentDist + begin) % n;
                int end2 = (begin - currentDist + n * 1000) % n;
    
                for (int i = 0; i < l.size(); i++) {
                    int v = l.get(i);
    
                    if ( !(begin < end1 && (v >= begin && v <= end1) || begin > end1 && (v >= begin || v <= end1)) ) l1.add(v);
                    if ( !(begin > end2 && (v >= end2 && v <= begin) || begin < end2 && (v >= end2 || v <= begin)) ) l2.add(v);
                }
    
                min = Math.min(calc(l1, newDist) + 1, min);
                min = Math.min(calc(l2, newDist) + 1, min);
            }
    
            cache.put(key, min);
            return min;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(12, new int[]{1,5,6,10}, new int[]{1,2,3,4}));
        solution.add(s.solution(12, new int[]{1,3,4,9,10}, new int[]{3,5,7}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}