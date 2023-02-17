package com.youhogeon.programmers.solved;


public class Q43238 {

    class Solution {
        public long solution(int n, int[] times) {
            long min = 1, max = 1000000000L * 1000000000L;
    
            while (min < max) {
                long mid = (min + max) / 2;
    
                if (isAvailable(n, mid, times)) max = mid;
                else min = mid + 1;
            }
    
            return min;
        }
    
        boolean isAvailable(int n, long max, int[] times) {
            long sum = 0;
    
            for (int time : times) {
                sum += max / (long)time;
    
                if (sum > 1000000000) return true;
            }
    
            return sum >= n;
        }
    }


    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(6,new int[]{7, 10}));
    }
}
