package com.youhogeon.kakao.blind2018.test1;

import java.util.*;

public class Q7 {
    class Solution {
        public int solution(String[] lines) {
            List<Log> logs = new ArrayList<>();
    
            for (int i = 0; i < lines.length; i++) {
                logs.add(new Log(lines[i]));
            }
    
            int max = -1;
            for (Log l : logs) {
                int cnt = 0;
    
                for (Log t : logs) {
                    if (l.begin <= t.begin && t.begin < l.begin + 1000 || l.begin <= t.end && t.end < l.begin + 1000 || l.begin > t.begin && l.begin + 1000 < t.end) cnt++;
                }
    
                max = Math.max(cnt, max);
            }
    
            for (Log l : logs) {
                int cnt = 0;
    
                for (Log t : logs) {
                    if (l.end <= t.begin && t.begin < l.end + 1000 || l.end <= t.end && t.end < l.end + 1000 || l.end > t.begin && l.end + 1000 < t.end) cnt++;
                }
    
                max = Math.max(cnt, max);
            }
    
            return max;
        }
    
        class Log {
            int begin, end;
    
            public Log(String str) {
                String[] split = str.split(" ");
                char[] ts = split[1].toCharArray();
    
                end = c2i(ts[0]) * 36000000 + c2i(ts[1]) * 3600000 + c2i(ts[3]) * 600000 + c2i(ts[4]) * 60000 + c2i(ts[6]) * 10000 + c2i(ts[7]) * 1000 + c2i(ts[9]) * 100 + c2i(ts[10]) * 10 + c2i(ts[11]);
                begin = end - Math.round(Float.parseFloat(split[2].substring(0, split[2].length() - 1)) * 1000) + 1;
            }
    
            public int c2i(char c) {
                return c - '0';
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new String[]{"2016-09-15 00:00:00.000 2.0s","2016-09-15 00:00:01.000 2s"}));
        solution.add(s.solution(new String[]{"2016-09-15 01:00:04.001 2.0s","2016-09-15 01:00:07.000 2s"}));
        solution.add(s.solution(new String[]{"2016-09-15 01:00:04.002 2.0s","2016-09-15 01:00:07.000 2s"}));
        solution.add(s.solution(new String[]{"2016-09-15 20:59:57.421 0.351s","2016-09-15 20:59:58.233 1.181s","2016-09-15 20:59:58.299 0.8s","2016-09-15 20:59:58.688 1.041s","2016-09-15 20:59:59.591 1.412s","2016-09-15 21:00:00.464 1.466s","2016-09-15 21:00:00.741 1.581s","2016-09-15 21:00:00.748 2.31s","2016-09-15 21:00:00.966 0.381s","2016-09-15 21:00:02.066 2.62s"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}