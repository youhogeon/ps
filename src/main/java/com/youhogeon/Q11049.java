package com.youhogeon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q11049{
	int[][] matrix;
	int N;

	public Q11049() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		matrix = new int[N][2];

		for (int i = 0; i < N; i++){
			String[] s = bf.readLine().split(" ");
			matrix[i][0] = Integer.parseInt(s[0]);
			matrix[i][1] = Integer.parseInt(s[1]);
		}
	}

	public int solve(){
		int[][] dp = new int[N][N];

		for (int i = 1; i < N; i++){
			for (int j = 0; j < N - i; j++){ 
				int v = Integer.MAX_VALUE;

				for (int k = 0; k < i; k++) v = Math.min(v, dp[j][j + k] + dp[j + k + 1][j + i] + matrix[j][0] * matrix[j + k][1] * matrix[j + i][1]);

				dp[j][j + i] = v;
			}
		}

		return dp[0][N - 1];
	}
}