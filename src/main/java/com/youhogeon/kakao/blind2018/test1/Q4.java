package com.youhogeon.kakao.blind2018.test1;

import java.util.*;

public class Q4 {
    class Solution {
        class Bus {
            int time;
            int leftSeat;
            int lastPassenger = -1;
    
            public Bus() {
    
            }
    
            public Bus(int time, int leftSeat) {
                this.time = time;
                this.leftSeat = leftSeat;
            }
        }
    
        public String solution(int n, int t, int m, String[] timetable) {
            Queue<Integer> queue = new PriorityQueue<>();
    
            for (int i = 0; i < timetable.length; i++) queue.add(str2int(timetable[i]));
    
            int time = 540;
            Bus bus = new Bus();
    
            for (int i = 0; i < n; i++) {
                bus = new Bus(time, m);
    
                while (!queue.isEmpty()) {
                    if (queue.peek() > time || bus.leftSeat == 0) break;
    
                    bus.leftSeat--;
                    bus.lastPassenger = queue.poll();
                }
    
                time += t;
            }
    
            if (bus.leftSeat > 0) return int2str(bus.time);
    
            return int2str(bus.lastPassenger - 1);
        }
    
        public int str2int(String str) {
            char[] c = str.toCharArray();
            int result = 0;
    
            result += (c[4] - '0');
            result += (c[3] - '0') * 10;
            result += (c[1] - '0') * 60;
            result += (c[0] - '0') * 600;
    
            return result;
        }
    
        public String int2str(int num) {
            StringBuilder sb = new StringBuilder();
    
            sb.append(num / 600);
            num %= 600;
    
            sb.append(num / 60);
            num %= 60;
    
            sb.append(':');
            sb.append(num / 10);
            num %= 10;
    
            sb.append(num);
    
            return sb.toString();
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        solution.add(s.solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
        solution.add(s.solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        solution.add(s.solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));
        solution.add(s.solution(1, 1, 1, new String[]{"23:59"}));
        solution.add(s.solution(10, 60, 45, new String[]{"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}