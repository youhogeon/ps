package com.youhogeon.kakao.blind2020;

import java.util.*;

public class Q3 {

    class Solution {
        int[][] lock, key;
    
        public boolean solution(int[][] key, int[][] lock) {
            int keyLen = key.length, lockLen = lock.length;
            this.lock = lock;
            this.key = key;
    
            for (int k = 0; k < 4; k++) {
                for (int i = -keyLen + 1; i < lockLen ; i++) {
                    for (int j = -keyLen + 1; j < lockLen ; j++) {
                        if (check(i, j)) return true;
                    }
                }
    
                rotateKey();
            }
    
            return false;
        }
    
        private boolean check(int x, int y) {
            int[][] clone = getLock();
    
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    int xx = x + i, yy = y + j;
    
                    if (xx < 0 || yy < 0 || xx >= clone.length || yy >= clone.length) continue;
    
                    clone[xx][yy] = clone[xx][yy] ^ key[i][j];
                }
            }
    
            for (int i = 0; i < clone.length; i++) {
                for (int j = 0; j < clone.length; j++) {
                    if (clone[i][j] == 0) return false;
                }
            }
    
            return true;
        }
    
        private void rotateKey() {
            int[][] clone = new int[key.length][key.length];
    
            for (int i = 0; i < clone.length; i++) {
                for (int j = 0; j < clone.length; j++) {
                    clone[i][j] = key[clone.length - j - 1][i];
                }
            }
    
            this.key = clone;
        }
    
        public int[][] getLock() {
            int[][] clone = new int[lock.length][lock.length];
    
            for (int i = 0; i < clone.length; i++) {
                for (int j = 0; j < clone.length; j++) {
                    clone[i][j] = lock[i][j];
                }
            }
    
            return clone;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new int[][]{new int[]{0, 0, 0}, new int[]{1, 0, 0}, new int[]{0, 1, 1}}, new int[][]{new int[]{1, 1, 1}, new int[]{1, 1, 0}, new int[]{1, 0, 1}}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}