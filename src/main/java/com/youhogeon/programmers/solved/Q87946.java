package com.youhogeon.programmers.solved;

public class Q87946 {

    class Solution {
        int[][] dungeons;
    
        public int solution(int k, int[][] dungeons) {
            this.dungeons = dungeons;
    
            int answer = find(k, 0);
            return answer;
        }
    
        int find(int k, int mask) {
            int max = 0;
    
            for (int i = 0; i < dungeons.length; i++) {
                if ((mask & (1 << i)) > 0 || k < dungeons[i][0]) continue;
    
                max = Math.max(max, 1 + find(k - dungeons[i][1], mask | (1 << i)));
            }
    
            return max;
        }
    }

    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(80, new int[][]{new int[]{90,20},new int[]{90,40},new int[]{90,10}})); //0
        System.out.println(s.solution(80, new int[][]{new int[]{90,20},new int[]{90,40},new int[]{20,10}})); //1
        System.out.println(s.solution(80, new int[][]{new int[]{80,20},new int[]{50,40},new int[]{30,10}})); //3
    }
}
