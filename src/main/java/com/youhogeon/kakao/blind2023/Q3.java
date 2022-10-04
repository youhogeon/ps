package com.youhogeon.kakao.blind2023;

import java.util.*;

public class Q3 {
    class Solution {
        public int[] solution(int[][] users, int[] emoticons) {
            int N = users.length, M = emoticons.length;
    
            emoticons = Arrays.copyOf(emoticons, 7);
    
            int maxCountPlus = -1, maxSumEmo = -1;
            for (int mask = 0; mask < 0x4000; mask++) {
    
                int countPlus = 0, sumEmo = 0;
    
                for (int i = 0; i < N; i++) {
                    int sum = 0;
    
                    for (int j = 0; j < M; j++) {
                        int rate = getRate(mask, j);
                        int price = calcRate(emoticons[j], getRate(mask, j));
    
                        if (rate >= users[i][0]) sum += price;
                    }
    
                    if (sum >= users[i][1]) countPlus++;
                    else sumEmo += sum;
                }
    
                if (countPlus > maxCountPlus) {
                    maxCountPlus = countPlus;
                    maxSumEmo = sumEmo;
                } else if (countPlus == maxCountPlus) maxSumEmo = Math.max(maxSumEmo, sumEmo);
            }
    
            int[] answer = { maxCountPlus, maxSumEmo };
            return answer;
        }
    
        int calcRate(int price, int rate) {
            return price - (int)((double)price / 100.0 * (double)rate);
        }
    
        int getRate(int mask, int digit) {
            digit *= 2;
            int bit = (mask & (3 << digit)) >> digit;
    
            return 10 * (bit + 1);
        }
    }
    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[][]{new int[]{40, 10000}, new int[]{25, 10000}}	,	new int[]{7000, 9000}));
        solution.add(s.solution(new int[][]{new int[]{40, 2900}, new int[]{23, 10000}, new int[]{11, 5200}, new int[]{5, 5900}, new int[]{40, 3100}, new int[]{27, 9200}, new int[]{32, 6900}}	,	new int[]{1300, 1500, 1600, 4900}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
