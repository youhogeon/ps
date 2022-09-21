package com.youhogeon.kakao.blind2021.test1;

import java.util.*;

public class Q5 {
    
    class Solution {
        public String solution(String play_time, String adv_time, String[] logs) {
            int maxTime = str2int(play_time), advTime = str2int(adv_time);
            int[] windows = new int[maxTime + 1];
            int[] viewer = new int[maxTime + 1];
    
            for (int i = 0; i < logs.length; i++) {
                int[] time = str2ints(logs[i]);
                windows[time[0]]++;
                windows[time[1]]--;
            }
    
            int answerInt = 0;
            long sum = 0;
            for (int i = 0; i < advTime; i++) {
                viewer[i] = ((i > 0) ? viewer[i - 1] : 0) + windows[i];
                sum += viewer[i];
            }
            long maxSum = sum;
    
            for (int i = advTime; i <= maxTime; i++) {
                viewer[i] = viewer[i - 1] + windows[i];
    
                sum += viewer[i] - viewer[i - advTime];
                if (sum > maxSum) {
                    maxSum = sum;
                    answerInt = i - advTime + 1;
                }
            }
    
            return int2str(answerInt);
        }
    
        public String int2str(int i) {
            StringBuilder sb = new StringBuilder();
    
            sb.append(String.format("%02d", i / 3600));
            sb.append(":");
            sb.append(String.format("%02d", (i / 60) % 60));
            sb.append(":");
            sb.append(String.format("%02d", i % 60));
    
            return sb.toString();
        }
    
        public int str2int(String s) {
            String[] strs = s.split(":");
    
            return Integer.parseInt(strs[0]) * 3600 + Integer.parseInt(strs[1]) * 60 + Integer.parseInt(strs[2]);
        }
    
        public int[] str2ints(String s) {
            String[] strs = s.split("-");
    
            return new int[]{ str2int(strs[0]), str2int(strs[1]) };
        }
    }
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution("02:03:55","02:03:54",new String[]{"01:20:15-02:03:55"}));
        //solution.add(s.solution("02:03:55","00:14:15",new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"}));
        //solution.add(s.solution("99:59:59","25:00:00",new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}));
        //solution.add(s.solution("50:00:00","50:00:00",new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}