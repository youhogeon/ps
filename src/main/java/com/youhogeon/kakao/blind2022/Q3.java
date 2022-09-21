package com.youhogeon.kakao.blind2022;

import java.util.*;

public class Q3 {
    class Solution {
        public int[] solution(int[] fees, String[] records) {
            Map<Integer, Integer> in = new HashMap<>();
            Map<Integer, Integer> sum = new HashMap<>();
    
            for (int i = 0; i < records.length; i++) {
                String[] str = records[i].split(" ");
    
                int time = str2int(str[0]);
                int car = Integer.parseInt(str[1]);
                boolean isIN = str[2].charAt(0) == 'I';
    
                if (isIN) {
                    in.put(car, time);
                } else {
                    sum.put(car, sum.getOrDefault(car, 0) + (time - in.get(car)));
                    in.remove(car);
                }
            }
    
            for (int car : in.keySet()) {
                sum.put(car, sum.getOrDefault(car, 0) + (1439 - in.get(car)));
            }
    
            Queue<Car> result = new PriorityQueue<>();
    
            for (int car : sum.keySet()) {
                result.add(new Car(car, sum.get(car)));
            }
    
            int[] answer = new int[result.size()];
    
            for (int i = 0; i < answer.length; i++) {
                int min = result.poll().min - fees[0];
                answer[i] = fees[1];
    
                if (min <= 0) continue;
    
                double additionalFees = Math.ceil((double)min / (double)fees[2]) * fees[3];
    
                answer[i] += additionalFees;
            }
    
            return answer;
        }
    
        int str2int(String str) {
            String[] strs = str.split(":");
    
            return Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
        }
    
        class Car implements Comparable<Car>{
            int id, min;
    
            public Car(int id, int min) {
                this.id = id;
                this.min = min;
            }
    
            @Override
            public int compareTo(Car o) {
                if (o.id < this.id) return 1;
                if (o.id > this.id) return -1;
    
                return 0;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[]{180, 5000, 10, 600},new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"}));
        solution.add(s.solution(new int[]{120, 0, 60, 591},new String[]{"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"}));
        solution.add(s.solution(new int[]{1, 461, 1, 10},new String[]{"00:00 1234 IN"}));


        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
