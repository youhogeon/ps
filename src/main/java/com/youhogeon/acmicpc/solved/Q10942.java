package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q10942{
	/**
	 * 출력 라인수가 최대 100000라인이기 때문에 StringBuilder 필수적으로 사용해야 함.
	 */
	int N, M;
	int[] arr;
	int[][] dp;

	public Q10942() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		dp = new int[N][N];

		String[] s = bf.readLine().split(" ");
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(s[i]);

		makeDP();

		M = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++){
			s = bf.readLine().split(" ");
			sb.append(solve(Integer.parseInt(s[0]) - 1, Integer.parseInt(s[1]) - 1));
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	public int solve(int S, int E){
		if (S == E) return 1;
		return dp[E][S];
	}

	public void makeDP(){
		for (int i = 1; i < N; i++){
			for (int j = 0; j < i; j++){
				int topRight = (i <= j + 2)?-1:dp[i - 1][j + 1];

				if (arr[i] == arr[j] && topRight != 0) dp[i][j] = 1;
			}
		}
	}
}