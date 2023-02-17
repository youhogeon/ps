package com.youhogeon.naverfinancial.intern2023;

import java.util.*;

class Solution {
    public int[] solution(int[][] gates, int[] airlines) {
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < gates.length; i++) {
            Queue<Integer> gatesQ = new PriorityQueue<>(Collections.reverseOrder());
            Queue<Integer> airsQ = new PriorityQueue<>(Collections.reverseOrder());

            for (int air : airlines) airsQ.add(air);
            for (int gate : gates[i]) gatesQ.add(gate);

            while (!gatesQ.isEmpty()) {
                if (airsQ.isEmpty()) break;

                if (airsQ.peek() < gatesQ.peek()) break;

                airsQ.add(airsQ.poll() - gatesQ.poll());
            }

            if (gatesQ.isEmpty()) answer.add(i + 1);
        }

        if (answer.isEmpty()) return new int[] { -1 };

        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) result[i] = answer.get(i);

        return result;
    }
}


public class Q2 {
    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(new int[][]{new int[]{5,4,4,2}}, new int[]{8,5,2}));



        // System.out.println(s.solution(new int[][]{new int[]{1, 1, 5, 3}, new int[]{2, 2, 3, 3}, new int[]{1, 1, 4, 4}, new int[]{1, 0, 3, 6}, new int[]{0, 2, 5, 3}}, new int[]{3, 2, 5}));
        // System.out.println(s.solution(new int[][]{new int[]{1, 1, 1, 1, 2}, new int[]{0, 0, 0, 0, 6}, new int[]{0, 2, 1, 1, 2}, new int[]{2, 0, 2, 0, 2}},new int[]{0, 2, 4}));
        // System.out.println(s.solution(new int[][]{new int[]{3}, new int[]{3}, new int[]{3}},new int[]{1, 1, 1}));
        // System.out.println(s.solution(new int[][]{new int[]{4, 1}, new int[]{5, 0}, new int[]{2, 3}, new int[]{3, 2}},new int[]{3, 0, 2}));

    }
}
