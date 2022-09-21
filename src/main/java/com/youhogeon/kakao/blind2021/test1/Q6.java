package com.youhogeon.kakao.blind2021.test1;

import java.util.*;

public class Q6 {

    class Solution {
        class Board implements Comparable<Board>{
            int[][] board;
            int leftCards, moved, r, c, wantToFind = -1;
    
            public Board(int[][] board, int leftCards, int moved, int r, int c) {
                this.board = board;
                this.leftCards = leftCards;
                this.moved = moved;
                this.r = r;
                this.c = c;
            }
    
            public Board(int[][] board, int leftCards, int moved, int r, int c, int wantToFind) {
                this(board, leftCards, moved, r, c);
                this.wantToFind = wantToFind;
            }
    
            @Override
            public int compareTo(Board o) {
                if (o.moved < this.moved) return 1;
                if (o.moved > this.moved) return -1;
                
                return 0;
            }
    
            @Override
            public String toString() {
                return "Board [board=" + Arrays.deepToString(board) + ", c=" + c + ", leftCards=" + leftCards + ", moved="
                        + moved + ", r=" + r + ", wantToFind=" + wantToFind + "]";
            }  
        }
        
        public int solution(int[][] board, int r, int c) {
            int leftCards = 0;
            int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
    
            Set<String> visited = new HashSet<>();
    
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] > 0) leftCards++;
                }
            }
    
            Queue<Board> queue = new PriorityQueue<>();
            Board initBoard = new Board(board, leftCards, 0, r, c);
            queue.add(initBoard);
            visited.add(initBoard.toString());
    
            while (!queue.isEmpty()) {
                Board b = queue.poll();
    
                if (b.leftCards == 0) return b.moved;
    
                if (b.wantToFind == -1 && b.board[b.r][b.c] > 0) {
                    Board nb = new Board(clone(b.board), b.leftCards, b.moved + 1, b.r, b.c, b.board[b.r][b.c]);
                    if (!visited.contains(nb.toString())) {
                        visited.add(nb.toString());
                        queue.add(nb);
                        nb.board[b.r][b.c] = 0;
                    }
    
                } //엔터(찾기위해)
    
                if (b.wantToFind == b.board[b.r][b.c]) {
                    Board nb = new Board(remove(b.board, b.board[b.r][b.c]), b.leftCards - 2, b.moved + 1, b.r, b.c);
                    if (!visited.contains(nb.toString())) {
                        visited.add(nb.toString());
                        queue.add(nb);
                        // nb.prev = b;
                    }
                } //엔터(찾음)
    
    
                for (int i = 0; i < 4; i++) {
                    int[] coor = move(b.board, b.r, b.c, dx[i], dy[i]);
                    if (coor != null) {
                        Board nb = new Board(clone(b.board), b.leftCards, b.moved + 1, coor[0], coor[1], b.wantToFind);
                        if (!visited.contains(nb.toString())) {
                            visited.add(nb.toString());
                            queue.add(nb);
                        }
                    }
    
                    coor = ctrlMove(b.board, b.r, b.c, dx[i], dy[i]);
                    if (coor != null) {
                        Board nb = new Board(clone(b.board), b.leftCards, b.moved + 1, coor[0], coor[1], b.wantToFind);
                        if (!visited.contains(nb.toString())) {
                            visited.add(nb.toString());
                            queue.add(nb);
                        }
                    }
                }
            }
    
            return -1;
        }
    
        public int[] move(int[][] board, int r, int c, int dr, int dc) {
            int newR = r + dr, newC = c + dc;
    
            if (newR < 0 || newC < 0 || newR > 3 || newC > 3) return null;
            
            return new int[]{newR, newC};
        }
        
        public int[] ctrlMove(int[][] board, int r, int c, int dr, int dc) {
            int newR = r, newC = c;
    
            while (true) {
                newR += dr;
                newC += dc;
                
                if (newR < 0 || newC < 0 || newR > 3 || newC > 3) {
                    newR -= dr;
                    newC -= dc;
                    break;
                }
                
                if (board[newR][newC] > 0) break;
            }
    
    
            if (r == newR && c == newC) return null;
    
            return new int[]{newR, newC};
        }
    
        public int[][] remove(int[][] a, int target) {
            int[][] b = new int[4][4];
    
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    b[i][j] = a[i][j] == target ? 0 : a[i][j];
                }
            }
    
            return b;
        }
    
        public int[][] clone(int[][] a) {
            return remove(a, -999);
        }
    }
    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new int[][]{new int[]{1,0,0,3},new int[]{2,0,0,0},new int[]{0,0,0,2},new int[]{3,0,1,0}},1,0));
        solution.add(s.solution(new int[][]{new int[]{3,0,0,2},new int[]{0,0,1,0},new int[]{0,1,0,0},new int[]{2,0,0,3}},0,1));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}