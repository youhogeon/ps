package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q2098{
	int N;
	int[][] arr, dp;
	int INF = 20000000;

	public Q2098() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		arr = new int[N][N];

		for (int i = 0; i < N; i++){
			String[] s = bf.readLine().split(" ");
			for (int j = 0; j < N; j++) arr[i][j] = Integer.parseInt(s[j]);
		}
	}

	public int solve(){
		dp = new int[N][(1 << N)];
		for (int i = 0; i < N; i++){
			for (int j = 0; j < (1 << N); j++) dp[i][j] = INF;
		}

		return dp(0, 1);
	}

	public int dp(int id, int visited){
		if (visited == (1 << N) - 1){
			if (arr[id][0] == 0) return INF;
			
			return arr[id][0];
		}

		if (dp[id][visited] != INF) return dp[id][visited];

		for (int i = 0; i < N; i++){
			int next = (1 << i) | visited;

			if (arr[id][i] == 0 || (visited & (1 << i)) > 0) continue;

			dp[id][visited] = Math.min(dp[id][visited], dp(i, next) + arr[id][i]);
		}

		return dp[id][visited];
	}
}