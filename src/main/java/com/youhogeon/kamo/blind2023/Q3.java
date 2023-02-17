package com.youhogeon.kamo.blind2023;

import java.util.*;

class Solution {
    public int[] solution(String s, String[] times) {
        Set<Integer> sets = new HashSet<>();
        long beginTS = str2TS(s);
        
        long endTS = beginTS;
        for (String time : times) {
            endTS += str2TS(time);
            sets.add(TS2day(endTS));
        }

        int duration = TS2day(endTS) - TS2day(beginTS);

        int[] answer = {sets.size() == duration ? 1 : 0, duration + 1};
        return answer;
    }

    public int TS2day(long TS) {
        return (int) (TS / 86400L);
    }

    public long str2TS(String str) {
        String[] strs = str.split(":");
        long[] v = new long[6];

        int p = strs.length == 6 ? 0 : 2;

        for (int i = 0; i < 6 - p; i++) v[i + p] = Long.parseLong(strs[i]);
        
        return (v[0] * 360L + v[1] * 30L + v[2]) * 86400L + v[3] * 3600L + v[4] * 60L + v[5];
    }
}

public class Q3 {

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution("2021:12:12:16:08:35",new String[]{}));
        solution.add(s.solution("2021:04:12:16:08:35",new String[]{"00:07:51:25", "01:00:00:00", "01:00:00:00", "00:01:00:00", "00:22:59:59"}));
        solution.add(s.solution("2021:04:12:16:08:35",new String[]{"01:06:30:00", "01:04:12:00"}));
        solution.add(s.solution("2021:04:12:16:08:35",new String[]{"01:06:30:00", "00:01:12:00"}));
        solution.add(s.solution("2021:04:12:16:10:42",new String[]{"01:06:30:00"}));
        solution.add(s.solution("2021:04:12:16:08:35",new String[]{"01:06:30:00", "01:01:12:00", "00:00:09:25"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
