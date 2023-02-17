package com.youhogeon.programmers.solved;

import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int[] numbers = new int[arr.length / 2];
        Queue<Integer> minusOps = new LinkedList<>();


        for (int i = 1; i < arr.length; i++) {
            if (i % 2 == 0) numbers[i / 2 - 1] = Integer.parseInt(arr[i]);
            else if (arr[i].charAt(0) == '-') minusOps.add(i / 2);
        }

        Map<Integer, int[]> prevMap = new HashMap<>();
        prevMap.put(0, numbers);

        for (int idx : minusOps) {
            Map<Integer, int[]> currentMap = new HashMap<>();

            for (int[] prev : prevMap.values()) {
                for (int i = idx; i < numbers.length; i++) {
                    prev[i] *= -1;

                    currentMap.put(Arrays.hashCode(prev), Arrays.copyOf(prev, prev.length));
                }
            }

            prevMap = currentMap;
        }

        int answer = Integer.MIN_VALUE;

        for (int[] result : prevMap.values()) {
            int sum = 0;

            for (int n : result) sum += n;

            answer = Math.max(answer, sum);
        }

        return Integer.parseInt(arr[0]) + answer;
    }
}

public class Q1843 {
    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(new String[]{"1", "-", "2", "-", "3", "-", "4", "-", "5"}));
        System.out.println(s.solution(new String[]{"1", "-", "3", "+", "5", "-", "8"}));
        System.out.println(s.solution(new String[]{"5", "-", "3", "+", "1", "+", "2", "-", "4"}));

    }
}
