package com.youhogeon.kakao;

import com.youhogeon.kakao.intern2021.*;

public class Main {

    public static void main(String[] args) {
        //(new Q4()).solve();
        System.out.println((new Solution()).solution(new int[][]{new int[]{60, 50}, new int[]{30, 70}, new int[]{60, 30}, new int[]{80, 40}}));
        System.out.println((new Solution()).solution(new int[][]{new int[]{10, 7}, new int[]{12, 3}, new int[]{8, 15}, new int[]{14, 7}, new int[]{5, 15}}));
        System.out.println((new Solution()).solution(new int[][]{new int[]{14, 4}, new int[]{19, 6}, new int[]{6, 16}, new int[]{18, 7}, new int[]{7, 11}}));
    }
}

class Solution {
    public int solution(int[][] sizes) {
        int max = -1;
        for (int i = 0; i < sizes.length; i++) {
            max = Math.max(max, sizes[i][0]);
            max = Math.max(max, sizes[i][1]);
        }

        int subMax = -1;
        for (int i = 0; i < sizes.length; i++) {
            subMax = Math.max(subMax, Math.min(sizes[i][0], sizes[i][1]));
        }

        return max * subMax;
    }
}