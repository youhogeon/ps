package com.youhogeon.kakaobank.blind2023;

import java.util.*;

public class Q3 {

    class Solution {
        public int solution(int[] rooms) {
            int[] parents = new int[rooms.length];
            for (int i = 0; i < parents.length; i++) parents[i] = i;
            
            for (int i = 0; i < rooms.length; i++) {
                parents[i] = findParent(parents, --rooms[i]);
            }
    
            Set<Integer> sets = new HashSet<>();
            for (int i = 0; i < parents.length; i++) {
                sets.add(findParent(parents, i));
            }
    
            int answer = Math.max(1, sets.size() - 1);
            return answer;
        }
    
        int findParent(int[] parents, int id) {
            Queue<Integer> queue = new LinkedList<>();
    
            while (parents[id] != id) {
                queue.add(id);
                id = parents[id];
            }
    
            while (!queue.isEmpty()) {
                parents[queue.poll()] = id;
            }
    
            return id;
        }
    }
    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[]{3, 1, 2, 4}));
        solution.add(s.solution(new int[]{2, 3, 4, 5, 1}));
        solution.add(s.solution(new int[]{1, 2, 3, 4, 5, 6}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
