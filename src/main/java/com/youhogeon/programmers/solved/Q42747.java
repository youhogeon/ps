package com.youhogeon.programmers.solved;

import java.util.*;

public class Q42747 {

    class Solution {
        public int solution(int[] citations) {
            Arrays.sort(citations);
    
            int max = -1;
            for (int i = 1; i <= citations.length; i++) {
                max = Math.max(max, Math.min(i, citations[citations.length - i]));
            }
    
            return max;
        }
    }

    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(new int[]{3, 3, 2}));
        System.out.println(s.solution(new int[]{4,0,6,1,5}));
    }
}
