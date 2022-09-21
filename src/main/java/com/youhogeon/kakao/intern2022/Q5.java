package com.youhogeon.kakao.intern2022;

import java.util.*;

public class Q5 {
    class Solution {
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();
        LinkedList<LinkedList<Integer>> rows = new LinkedList<>();
    
        public int[][] solution(int[][] rc, String[] operations) {
            int sizeC = rc[0].length, sizeR = rc.length;
    
            for (int i = 0; i < sizeR; i++) {
                LinkedList<Integer> row = new LinkedList<>();
                for (int j = 1; j < sizeC - 1; j++)  row.add(rc[i][j]);
    
                left.add(rc[i][0]);
                right.add(rc[i][sizeC - 1]);
                rows.add(row);
            }
    
            for (int i = 0; i < operations.length; i++) {
                if (operations[i].charAt(0) == 'R') rotate();
                else shiftRow();
            }
    
            int[][] answer = new int[sizeR][sizeC];
    
            for (int i = 0; i < sizeR; i++) {
                LinkedList<Integer> row = rows.removeFirst();
                for (int j = 1; j < sizeC - 1; j++) answer[i][j] = row.removeFirst();
    
                answer[i][0] = left.removeFirst();
                answer[i][sizeC - 1] = right.removeFirst();
            }
    
            return answer;
        }
    
        void shiftRow() {
            left.addFirst(left.removeLast());
            right.addFirst(right.removeLast());
            rows.addFirst(rows.removeLast());
        }
    
        void rotate() {
            rows.getFirst().addFirst(left.removeFirst());
            right.addFirst(rows.getFirst().removeLast());
            rows.getLast().addLast(right.removeLast());
            left.addLast(rows.getLast().removeFirst());
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}},new String[]{"Rotate", "ShiftRow"}));
        solution.add(s.solution(new int[][]{new int[]{8, 6, 3}, new int[]{3, 3, 7}, new int[]{8, 4, 9}},new String[]{"Rotate", "ShiftRow", "ShiftRow"}));
        solution.add(s.solution(new int[][]{new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8}, new int[]{9, 10, 11, 12}},new String[]{"ShiftRow", "Rotate", "ShiftRow", "Rotate"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
