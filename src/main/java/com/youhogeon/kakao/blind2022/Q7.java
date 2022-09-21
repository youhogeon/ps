package com.youhogeon.kakao.blind2022;

import java.util.*;

public class Q7 {
    class Solution {
        public int solution(int[][] board, int[] aloc, int[] bloc) {
            int answer = -1;
            return answer;
        }
    
        class State {
            int[][] board;
            int[] aloc, bloc;
            int state = 0; //0 : A차례 1 : B차례 2 : A승 3 : B승
            int count = 0;
    
            List<State> nextState() {
                return null;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[][]{new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}},new int[]{1, 0},new int[]{1, 2}));
        solution.add(s.solution(new int[][]{new int[]{1, 1, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 1}},new int[]{1, 0},new int[]{1, 2}));
        solution.add(s.solution(new int[][]{new int[]{1, 1, 1, 1, 1}},new int[]{0, 0},new int[]{0, 4}));
        solution.add(s.solution(new int[][]{new int[]{1}},new int[]{0, 0},new int[]{0, 0}));


        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
