package com.youhogeon.kakao.blind2022;

import java.util.*;

public class Q7 {
    class Solution {
        int N, M;
        int[] dr = new int[]{1, -1, 0, 0}, dc = new int[]{0, 0, 1, -1};

        public int solution(int[][] board, int[] aloc, int[] bloc) {
            N = board.length;
            M = board[0].length;

            int initBoard = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 0) initBoard = disableBoard(initBoard, i, j);
                }
            }

            State initState = new State(initBoard, aloc, bloc);
            int result = run(initState);

            return result;
        }

        int run(State state) {
            int r = state.aloc[0], c = state.aloc[1];
            int result = 0;

            if (!getBoard(state.board, r, c)) return 0;

            for (int i = 0; i < 4; i++) {
                int newR = r + dr[i], newC = c + dc[i];

                if (!getBoard(state.board, newR, newC)) continue;

                int newBoard = disableBoard(state.board, r, c);

                State newState = new State(newBoard, new int[]{ state.bloc[0], state.bloc[1] }, new int[]{ newR, newC });
                int newResult = run(newState) + 1;

                boolean isCurrentWin = result % 2 == 1;
                boolean isNewWin = newResult % 2 == 1;
                if (!isCurrentWin && isNewWin) result = newResult;
                else if (isCurrentWin && isNewWin) result = Math.min(result, newResult);
                else if (!isCurrentWin && !isNewWin) result = Math.max(result, newResult);
            }

            return result;
        }

        boolean getBoard(int board, int r, int c) {
            if (r < 0 || c < 0 || r >= N || c >= M) return false;

            int idx = r * M + c;

            return (board & (1 << idx)) == 0;
        }

        int disableBoard(int board, int r, int c) {
            int idx = r * M + c;

            return (board | (1 << idx));
        }
    
        class State{
            int board;
            int[] aloc, bloc;
            
            public State(int board, int[] aloc, int[] bloc) {
                this.board = board;
                this.aloc = aloc;
                this.bloc = bloc;
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
