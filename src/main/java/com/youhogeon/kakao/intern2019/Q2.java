package com.youhogeon.kakao.intern2019;

import java.util.*;

public class Q2 {
    class Solution {
        Map<Integer, Tuple> map = new HashMap<>();
        Queue<Tuple> queue = new PriorityQueue<>();
    
        int getMax() {
            while (!queue.isEmpty()) {
                if (queue.peek().count == 0) queue.poll();
                else return queue.peek().id;
            }
    
            return -1;
        }
    
        void add(int i) {
            Tuple t = map.get(i);
    
            if (t == null) {
                t = new Tuple(i, 1);
                map.put(i, t);
            } else t.count++;
    
            if (t.count == 1) {
                queue.add(t);
            }
        }
    
        void remove(int i) {
            map.get(i).count--;
        }
    
        public int solution(int[] stones, int k) {
            for (int i = 0; i < k; i++) add(stones[i]);
    
            int min = getMax();
            for (int i = k; i < stones.length; i++) {
                add(stones[i]);
                remove(stones[i - k]);
    
                min = Math.min(getMax(), min);
            }
    
            return min;
        }
    
        class Tuple implements Comparable<Tuple> {
            int id, count;
    
            public Tuple(int id, int count) {
                this.id = id;
                this.count = count;
            }
    
            @Override
            public int compareTo(Tuple o) {
                if (o.id > this.id) return 1;
                if (o.id < this.id) return -1;
    
                return 0;
            }
            
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[]{2, 4, 5}	,	3));
        solution.add(s.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}	,	3));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
