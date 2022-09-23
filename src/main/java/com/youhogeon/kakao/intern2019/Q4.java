package com.youhogeon.kakao.intern2019;

import java.util.*;

public class Q4 {
    class Solution {
        String[] user_id, banned_id;
        Set<Integer> result;
    
        void calc(int banIdx, int mask) {
            if (banIdx == banned_id.length) {
                result.add(mask);
                return;
            }
    
            for (int i = 0; i < user_id.length; i++) {
                if (((mask >> i) & 1) == 1) continue;
                if (!user_id[i].matches(banned_id[banIdx])) continue;
    
                calc(banIdx + 1, mask | (1 << i));
            }
        }
    
        public int solution(String[] user_id, String[] banned_id) {
            this.user_id = user_id;
            this.banned_id = banned_id;
            result = new HashSet<>();
    
            for (int i = 0; i < banned_id.length; i++) banned_id[i] = "^" + banned_id[i].replaceAll("\\*", "(.{1})") + "$";
    
            calc(0, 0);
    
            return result.size();
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}	,	new String[]{"fr*d*", "abc1**"}));
        solution.add(s.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}	,	new String[]{"*rodo", "*rodo", "******"}));
        solution.add(s.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}	,	new String[]{"fr*d*", "*rodo", "******", "******"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
