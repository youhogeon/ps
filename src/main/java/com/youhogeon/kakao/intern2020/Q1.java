package com.youhogeon.kakao.intern2020;

import java.util.*;

public class Q1 {

    class Solution {
        int[][] coor = {
            { 3, 1 },
            { 0, 0 },
            { 0, 1 },
            { 0, 2 },
            { 1, 0 },
            { 1, 1 },
            { 1, 2 },
            { 2, 0 },
            { 2, 1 },
            { 2, 2 },
            { 3, 0 },
            { 3, 2 },
        };
    
        public String solution(int[] numbers, String hand) {
            StringBuilder sb = new StringBuilder();
    
            int left = 10, right = 11;
    
            boolean leftHand = hand.equals("left");
    
            for (int i = 0; i < numbers.length; i++) {
                boolean isLeft = (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7);
    
                if (numbers[i] == 0 || (numbers[i] + 1) % 3 == 0) {
                    int calcLeft = calc(numbers[i], left), calcRight = calc(numbers[i], right);
                    isLeft = calcLeft < calcRight || leftHand && calcLeft == calcRight;
                }
    
                sb.append(isLeft ? 'L' : 'R');
                if (isLeft) left = numbers[i];
                else right = numbers[i];
            }
    
            return sb.toString();
        }
    
        private int calc(int a, int b) {
            return Math.abs(coor[a][0] - coor[b][0]) + Math.abs(coor[a][1] - coor[b][1]);
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}	,	"right"));
        solution.add(s.solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}	,	"left"));
        solution.add(s.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}	,	"right"));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
