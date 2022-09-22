package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q2342{
	int N;
	int[] arr;
	int[][] dp;
	int[][] cost;
	final int big = 999999;

	public Q2342() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		arr = new int[str.length];
		dp = new int[str.length][5];

		for (int i = 0; i < arr.length; i++) {
			if (i != 0) arr[i] = Integer.parseInt(str[i - 1]);

			for (int j = 0; j < 5; j++) dp[i][j] = big;
		}

		dp[0][0] = 0;
		arr[0] = 0;
		cost = new int[][] {{big, 2, 2, 2, 2}, {big, 1, 3, 4, 3}, {big, 3, 1, 3, 4}, {big, 4, 3, 1, 3}, {big, 3, 4, 3, 1}};
	}

	public int solve(){
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < 5; j++) {
				if (arr[i + 1] == j) dp[i + 1][j] = big;
				else if (arr[i] != j) dp[i + 1][j] = dp[i][j] + cost[arr[i]][arr[i + 1]];
				else {
					for (int k = 0; k < 5; k++) dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][k] + cost[k][arr[i + 1]]);
				}
			}

			for (int j = 0; j < 5; j++) if (dp[i + 1][j] > big) dp[i + 1][j] = big;
		}

		int min = 0x7FFFFFFF;
		for (int i = 0; i < 5; i++) min = Math.min(min, dp[arr.length - 1][i]);

		return min;
	}
}