package com.youhogeon.kakao.blind2019;

import java.util.*;

public class Q1 {
    class Solution {
        public String[] solution(String[] record) {
            Map<String, String> nickname = new HashMap<>();
            List<String> result = new ArrayList<>();
    
            for (int i = 0; i < record.length; i++) {
                String[] strs = record[i].split(" ");
                
                if (strs[0].charAt(0) != 'L') nickname.put(strs[1], strs[2]);
            }
    
            for (int i = 0; i < record.length; i++) {
                String[] strs = record[i].split(" ");
    
    
                if (strs[0].charAt(0) == 'E') result.add(nickname.get(strs[1]) + "님이 들어왔습니다.");
                else if (strs[0].charAt(0) == 'L') result.add(nickname.get(strs[1]) + "님이 나갔습니다.");
            }
    
            String[] answer = new String[result.size()];
    
            for (int i = 0; i < result.size(); i++) answer[i] = result.get(i);
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}