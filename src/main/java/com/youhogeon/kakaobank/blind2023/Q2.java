package com.youhogeon.kakaobank.blind2023;

import java.util.*;

public class Q2 {

    class Solution {
        public int solution(int money, int minratio, int maxratio, int ranksize, int threshold, int months) {
    
            while (months-- > 0) {
                int money00 = (money / 100) * 100;
    
                double rate = calcRate(money00, minratio, maxratio, ranksize, threshold);
                money -= (double)money00 * rate;
            }
    
            return money;
        }
    
        public double calcRate(int money, int minratio, int maxratio, int ranksize, int threshold) {
            money -= threshold;
            
            if (money < 0) return 0;
    
            return (double)Math.min(minratio + (money / ranksize), maxratio) / 100;
        }
    }
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(12345678,10,20,250000,10000000,4)); //9000014
        solution.add(s.solution(1000000000,50,99,100000,0,6)); // 6150
        solution.add(s.solution(123456789,0,0,1,0,360)); //123456789

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
