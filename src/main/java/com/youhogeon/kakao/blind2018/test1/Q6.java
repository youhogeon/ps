package com.youhogeon.kakao.blind2018.test1;

import java.util.*;

public class Q6 {
    class Solution {
        public int solution(int m, int n, String[] board) {
            int[][] map = new int[n][m];
    
            for (int i = 0; i < m; i++) {
                char[] c = board[i].toCharArray();
    
                for (int j = 0; j < n; j++) map[j][m - i - 1] = c[j];
            }
    
            while (run(map, m, n));
    
            int result = m * n;
            for (int i = 0; i < n; i++) result -= map[i].length;
    
            return result;
        }
    
        public boolean run(int[][] map, int m, int n) {
            boolean result = false;
            boolean[][] removed = new boolean[n][m];
    
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < m - 1; j++) {
    
                    if (i + 1 >= n || j + 1 >= map[i].length || j + 1 >= map[i + 1].length || map[i][j] != map[i][j + 1] || map[i][j] != map[i + 1][j] || map[i][j] != map[i + 1][j + 1]) continue;
    
                    result = true;
                    removed[i][j] = true;
                    removed[i][j + 1] = true;
                    removed[i + 1][j] = true;
                    removed[i + 1][j + 1] = true;
    
                }
            }
    
            for (int i = 0; i < n; i++) {
                for (int j = m - 1; j >= 0; j--) {
                    if (removed[i][j]) map[i] = remove(map[i], j);
                }
            }
    
            return result;
        }
    
        public int[] remove(int[] arr, int idx) {
            return java.util.stream.IntStream.range(0, arr.length)
                            .filter(k -> k != idx)
                            .map(k -> arr[k])
                            .toArray();
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
        solution.add(s.solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}