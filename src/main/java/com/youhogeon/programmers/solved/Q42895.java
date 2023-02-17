package com.youhogeon.programmers.solved;

import java.util.*;

public class Q42895 {

    class Solution {
        public int solution(int N, int number) {
            Map<Long, Integer> result = new HashMap<>();
    
            int NN = N;
            for (int i = 0; i < 8; i++) {
                result.put((long)NN, i + 1);
                NN = NN * 10 + N;
            }
    
            while (true) {
                int hashCode = result.hashCode();
    
                List<Long> keySet = new ArrayList<>(result.keySet());
                for (Long a : keySet) {
                    for (Long b : keySet) {
                        int calcCnt = result.get(a) + result.get(b);
                        if (calcCnt > 8) continue;
    
                        if (calcCnt < result.getOrDefault(a + b, 0x7FFFFFFF)) result.put(a + b, calcCnt);
                        if (calcCnt < result.getOrDefault(a - b, 0x7FFFFFFF)) result.put(a - b, calcCnt);
                        if (calcCnt < result.getOrDefault(b - a, 0x7FFFFFFF)) result.put(b - a, calcCnt);
                        if (calcCnt < result.getOrDefault(a * b, 0x7FFFFFFF)) result.put(a * b, calcCnt);
                        if (b != 0 && calcCnt < result.getOrDefault(a / b, 0x7FFFFFFF)) result.put(a / b, calcCnt);
                    }
                }
    
                if (hashCode == result.hashCode()) break;
            }
    
            return result.getOrDefault((long)number, -1);
        }
    }

    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(5,12));
        System.out.println(s.solution(2,11));
    }
}
