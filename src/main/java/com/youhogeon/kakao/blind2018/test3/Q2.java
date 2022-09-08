package com.youhogeon.kakao.blind2018.test3;

import java.util.*;

public class Q2 {
    class Solution {
        public int[] solution(String msg) {
            Map<String, Integer> map = new HashMap<>();
            List<Integer> list = new ArrayList<>();
            int idx = 1, len = msg.length();
    
    
            for (char i = 'A'; i <= 'Z'; i++) {
                map.put(Character.toString(i), idx++);
            }
    
            for (int i = 0; i < len; i++) {
                for (int j = len; j > i; j--) {
                    String subStr = msg.substring(i, j);
                    
                    if (map.containsKey(subStr)) {
                        list.add(map.get(subStr));
                        if (j + 1 <= len) map.put(msg.substring(i, j + 1), idx++);
                        i += j - i - 1;
                        break;
                    }
                }
            }
    
            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);
    
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution("KAKAO"));
        solution.add(s.solution("ABABABABABABABAB"));
        solution.add(s.solution("TOBEORNOTTOBEORTOBEORNOT"));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}