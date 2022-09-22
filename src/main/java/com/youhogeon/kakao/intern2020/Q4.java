package com.youhogeon.kakao.intern2020;

import java.util.*;

public class Q4 {
    class Solution {
        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };
        int[] dir = { 0, 0, 1, 1 };
    
        public int solution(int[][] board) {
            int N = board.length;
            boolean[][][] visited = new boolean[N][N][2];
    
            Queue<Coor> queue = new PriorityQueue<>();
            queue.add(new Coor(0, 0, 0, 1));
            queue.add(new Coor(0, 0, 0, 0));
    
            while (!queue.isEmpty()) {
                Coor coor = queue.poll();
    
                if (visited[coor.x][coor.y][coor.direction]) continue;
                if (coor.x == N - 1 && coor.y == N - 1) return coor.cost;
    
                for (int i = 0; i < 4; i++) {
                    int newX = coor.x + dx[i], newY = coor.y + dy[i];
                    int cost = coor.direction == dir[i] ? 100 : 600;
    
                    if (newX < 0 || newY < 0 || newX >= N || newY >= N || board[newX][newY] == 1) continue;
    
                    Coor newCoor = new Coor(newX, newY, cost + coor.cost, dir[i]);
                    queue.add(newCoor);
                }
    
                visited[coor.x][coor.y][coor.direction] = true;
            }
    
            return -1;
        }
    
        class Coor implements Comparable<Coor>{
            int x, y, cost, direction;
    
            public Coor(int x, int y, int cost, int direction) {
                this.x = x;
                this.y = y;
                this.cost = cost;
                this.direction = direction;
            }
    
            @Override
            public int compareTo(Coor o) {
                if (o.cost < this.cost) return 1;
                if (o.cost > this.cost) return -1;
    
                return 0;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[][]{new int[]{0,0,0},new int[]{0,0,0},new int[]{0,0,0}}));
        solution.add(s.solution(new int[][]{new int[]{0,0,0,0,0,0,0,1},new int[]{0,0,0,0,0,0,0,0},new int[]{0,0,0,0,0,1,0,0},new int[]{0,0,0,0,1,0,0,0},new int[]{0,0,0,1,0,0,0,1},new int[]{0,0,1,0,0,0,1,0},new int[]{0,1,0,0,0,1,0,0},new int[]{1,0,0,0,0,0,0,0}}));
        solution.add(s.solution(new int[][]{new int[]{0,0,1,0},new int[]{0,0,0,0},new int[]{0,1,0,1},new int[]{1,0,0,0}}));
        solution.add(s.solution(new int[][]{new int[]{0,0,0,0,0,0},new int[]{0,1,1,1,1,0},new int[]{0,0,1,0,0,0},new int[]{1,0,0,1,0,1},new int[]{0,1,0,0,0,1},new int[]{0,0,0,0,0,0}}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
