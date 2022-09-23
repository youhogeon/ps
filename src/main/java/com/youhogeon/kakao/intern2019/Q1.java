package com.youhogeon.kakao.intern2019;

import java.util.*;

public class Q1 {
    class Solution {
        public int solution(int[][] board, int[] moves) {
            List<Queue<Integer>> queueList = new ArrayList<>();
    
            for (int i = 0; i < board[0].length; i++) queueList.add(new LinkedList<>());
    
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] > 0) queueList.get(j).add(board[i][j]);
                }
            }
            
            int answer = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < moves.length; i++) {
                Queue<Integer> queue = queueList.get(moves[i] - 1);
    
                if (queue.isEmpty()) continue;
    
                if (!stack.isEmpty() && queue.peek() == stack.peek()) {
                    stack.pop();
                    answer += 2;
                } else stack.push(queue.peek());
    
                queue.poll();
            }
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[][]{new int[]{0,0,0,0,0},new int[]{0,0,1,0,3},new int[]{0,2,5,0,1},new int[]{4,2,4,4,2},new int[]{3,5,1,3,1}}	,	new int[]{1,5,3,5,1,2,1,4}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
