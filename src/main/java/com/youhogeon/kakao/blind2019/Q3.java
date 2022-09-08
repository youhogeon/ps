package com.youhogeon.kakao.blind2019;

import java.util.*;

public class Q3 {
    class Solution {
        public int solution(String[][] relation) {
            int col = relation[0].length;
    
            List<Integer> tuples = new ArrayList<Integer>();
    
            for (int tupleKey = 1; tupleKey < Math.pow(2, col); tupleKey++) {
                if (checkTuple(tupleKey, relation)) tuples.add(tupleKey);
                //check uniqueness
            }
    
            int answer = 0;
            for (int i : tuples) {
                boolean available = true;
    
                for (int j : tuples) {
                    if ((i | j) == i) {
                        if (i != j) available = false;
                        break;
                    }
                }
    
                if (available) answer++;
            }
    
            return answer;
        }
    
        public boolean checkTuple(int tupleKey, String[][] relation) {
            Set<String> set = new HashSet<>();
    
            for (int i = 0; i < relation.length; i++) {
                String key = makeKey(tupleKey, relation[i]);
    
                if (set.contains(key)) return false;
    
                set.add(key);
            }
    
            return true;
        }
    
        public String makeKey(int tupleKey, String[] data) {
            String key = "";
            int idx = 0;
    
            while (tupleKey > 0) {
                if ((tupleKey & 1) == 1) {
                    key += data[idx] + "/";
                }
    
                tupleKey = tupleKey >> 1;
                idx++;
            }
    
            return key;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new String[][]{ new String[]{ "100","ryan","music","2" }, new String[]{ "200","apeach","math","2" }, new String[]{ "300","tube","computer","3" }, new String[]{ "400","con","computer","4" }, new String[]{ "500","muzi","music","3" }, new String[]{ "600","apeach","music","2" } }));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}