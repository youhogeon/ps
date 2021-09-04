package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q17404{
	int N;
	int[][] data;

	public Q17404() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		data = new int[N][3];

		for (int i = 0; i < N; i++){
			String[] s = bf.readLine().split(" ");

			for (int j = 0; j < 3; j++) data[i][j] = Integer.parseInt(s[j]);
		}
	}

	public int solve(){
		return Math.min(Math.min(dp(0), dp(1)), dp(2));
	}

	public int dp(int start){
		int[][] dp;
		dp = new int[N][3];

		for (int i = 0; i < 3; i++) dp[1][i] = data[1][i] + data[0][start];
		dp[1][start] = 100000000;

		for (int i = 2; i < N; i++){
			for (int j = 0; j < 3; j++){
				dp[i][j] = data[i][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
			}
		}

		return Math.min(dp[N - 1][(start + 1) % 3], dp[N - 1][(start + 2) % 3]);
	}
}