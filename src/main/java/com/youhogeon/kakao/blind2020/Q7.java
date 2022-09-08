package com.youhogeon.kakao.blind2020;

import java.util.*;

public class Q7 {

    class Solution {
        int[][] board;
    
        public int solution(int[][] board) {
            this.board = board;
            Map<Integer, Integer> map = new HashMap<>();
    
            Queue<Integer> queue = new LinkedList<>();
            queue.add(makeKey(0, 0, 0, 1));
            map.put(makeKey(0, 0, 0, 1), 0);
    
            while (!queue.isEmpty()) {
                int key, currentKey = queue.poll(), nextCost = map.get(currentKey) + 1;
                int[] coor = resolveKey(currentKey);
    
                key = makeKey(coor[0] + 1, coor[1], coor[2] + 1, coor[3]);
                if (isAvailable(coor[0] + 1, coor[1], coor[2] + 1, coor[3]) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                    queue.add(key);
                    map.put(key, nextCost);
                }
    
                key = makeKey(coor[0], coor[1] + 1, coor[2], coor[3] + 1);
                if (isAvailable(coor[0], coor[1] + 1, coor[2], coor[3] + 1) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                    queue.add(key);
                    map.put(key, nextCost);
                }
    
                key = makeKey(coor[0] - 1, coor[1], coor[2] - 1, coor[3]);
                if (isAvailable(coor[0] - 1, coor[1], coor[2] - 1, coor[3]) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                    queue.add(key);
                    map.put(key, nextCost);
                }
    
                key = makeKey(coor[0], coor[1] - 1, coor[2], coor[3] - 1);
                if (isAvailable(coor[0], coor[1] - 1, coor[2], coor[3] - 1) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                    queue.add(key);
                    map.put(key, nextCost);
                }
    
                if (coor[0] == coor[2]) {
                    key = makeKey(coor[0] - 1, coor[1] + 1, coor[2], coor[3]);
                    if (getBoard(coor[0] - 1, coor[1]) && isAvailable(coor[0] - 1, coor[1] + 1, coor[2], coor[3]) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                        queue.add(key);
                        map.put(key, nextCost);
                    }
        
                    key = makeKey(coor[0] + 1, coor[1] + 1, coor[2], coor[3]);
                    if (getBoard(coor[0] + 1, coor[1]) && isAvailable(coor[0] + 1, coor[1] + 1, coor[2], coor[3]) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                        queue.add(key);
                        map.put(key, nextCost);
                    }
        
                    key = makeKey(coor[0], coor[1], coor[2] - 1, coor[3] - 1);
                    if (getBoard(coor[2] - 1, coor[3]) && isAvailable(coor[0], coor[1], coor[2] - 1, coor[3] - 1) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                        queue.add(key);
                        map.put(key, nextCost);
                    }
        
                    key = makeKey(coor[0], coor[1], coor[2] + 1, coor[3] - 1);
                    if (getBoard(coor[2] + 1, coor[3]) && isAvailable(coor[0], coor[1], coor[2] + 1, coor[3] - 1) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                        queue.add(key);
                        map.put(key, nextCost);
                    }
                } else {
                    key = makeKey(coor[0] + 1, coor[1] - 1, coor[2], coor[3]);
                    if (getBoard(coor[0], coor[1] - 1) && isAvailable(coor[0] + 1, coor[1] - 1, coor[2], coor[3]) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                        queue.add(key);
                        map.put(key, nextCost);
                    }
        
                    key = makeKey(coor[0] + 1, coor[1] + 1, coor[2], coor[3]);
                    if (getBoard(coor[0], coor[1] + 1) && isAvailable(coor[0] + 1, coor[1] + 1, coor[2], coor[3]) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                        queue.add(key);
                        map.put(key, nextCost);
                    }
        
                    key = makeKey(coor[0], coor[1], coor[2] - 1, coor[3] - 1);
                    if (getBoard(coor[2], coor[3] - 1) && isAvailable(coor[0], coor[1], coor[2] - 1, coor[3] - 1) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                        queue.add(key);
                        map.put(key, nextCost);
                    }
        
                    key = makeKey(coor[0], coor[1], coor[2] - 1, coor[3] + 1);
                    if (getBoard(coor[2], coor[3] + 1) && isAvailable(coor[0], coor[1], coor[2] - 1, coor[3] + 1) && map.getOrDefault(key, 0x7FFFFFFF) > nextCost) {
                        queue.add(key);
                        map.put(key, nextCost);
                    }
                }
            }
    
            return Math.min(map.getOrDefault(makeKey(board.length - 2, board[0].length - 1, board.length - 1, board[0].length - 1), 0x7FFFFFFF), map.getOrDefault(makeKey(board.length - 1, board[0].length - 2, board.length - 1, board[0].length - 1), 0x7FFFFFFF));
        }
    
        public boolean getBoard(int x, int y) {
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return false;
    
            return board[x][y] == 0;
        }
    
        public boolean isAvailable(int key) {
            int[] coor = resolveKey(key);
    
            return isAvailable(coor[0], coor[1], coor[2], coor[3]);
        }
    
        public boolean isAvailable(int x1, int y1, int x2, int y2) {
            return getBoard(x1, y1) & getBoard(x2, y2);
        }
    
        public int makeKey(int x1, int y1, int x2, int y2) {
            int k1 = x1 * 1000000 + y1 * 10000 + x2 * 100 + y2;
            int k2 = x2 * 1000000 + y2 * 10000 + x1 * 100 + y1;
    
            return Math.min(k1, k2);
        }
    
        public int[] resolveKey(int key) {
            return new int[]{ key / 1000000, (key / 10000) % 100, (key / 100) % 100, key % 100 };
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new int[][]{new int[]{0, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 0, 0, 1}, new int[]{0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 1, 1, 1, 1, 0}, new int[]{0, 1, 1, 1, 1, 1, 0}, new int[]{0, 0, 0, 0, 0, 1, 1}, new int[]{0, 0, 1, 0, 0, 0, 0}}        )); //21
        solution.add(s.solution(new int[][]{new int[]{0, 0, 0, 0, 1, 0}, new int[]{0, 0, 1, 1, 1, 0}, new int[]{0, 1, 1, 1, 1, 0}, new int[]{0, 1, 0, 0, 1, 0}, new int[]{0, 0, 1, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}}        )); // 11
        solution.add(s.solution(new int[][]{new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}}));
        solution.add(s.solution(new int[][]{new int[]{0, 0, 0, 1, 1},new int[]{0, 0, 0, 1, 0},new int[]{0, 1, 0, 1, 1},new int[]{1, 1, 0, 0, 1},new int[]{0, 0, 0, 0, 0}}	));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}