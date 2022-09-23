package com.youhogeon.kakao.intern2019;

import java.util.*;

public class Q5 {
    class Solution {
        public int[] solution(String s) {
            String[] arr = s.replace("{{", "").replace("}}", "").split("\\},\\{");
    
            Queue<L> queue = new PriorityQueue<>();
            for (int i = 0; i < arr.length; i++) queue.add(new L(arr[i]));
    
            Set<Integer> set = new HashSet<>();
            int[] answer = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                L l = queue.poll();
    
                for (int j = 0; j <= i; j++) {
                    if (!set.contains(l.arr[j])) {
                        answer[i] = l.arr[j];
                        set.add(l.arr[j]);
    
                        break;
                    }
                }
    
            }
    
            return answer;
        }
    
        class L implements Comparable<L> {
            int[] arr;
            
            public L(String str) {
                String[] strs = str.split(",");
    
                arr = new int[strs.length];
                for (int i = 0; i < strs.length; i++) arr[i] = Integer.parseInt(strs[i]);
            }
    
            @Override
            public int compareTo(L o) {
                if (o.arr.length < this.arr.length) return 1;
                if (o.arr.length > this.arr.length) return -1;
    
                return 0;
            }
    
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"));
        solution.add(s.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"));
        solution.add(s.solution("{{20,111},{111}}"));
        solution.add(s.solution("{{123}}"));
        solution.add(s.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
