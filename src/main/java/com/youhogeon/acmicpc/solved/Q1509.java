package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q1509{
	//Q2079와 완전히 동일
	char[] str;
	int N;
	boolean[][] dp;

	public Q1509() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		str = bf.readLine().toCharArray();
		N = str.length;
		dp = new boolean[N][N];

		for (int i = 0; i < N; i++) dp[i][i] = true;
	}

	public int solve() {
		checkPalindrom();

		int[] result = new int[N];
		result[0] = 1;

		for (int i = 1; i < N; i++) {
			if (dp[0][i]) result[i] = 1;
			else {
				result[i] = 0x7FFFFFFF;

				for (int j = 1; j <= i; j++) {
					if (dp[j][i]) result[i] = Math.min(result[i], result[j - 1] + 1);
				}
			}
		}

		return result[N - 1];
	}

	public void checkPalindrom() {
		for (int i = N - 1; i >= 0; i--) {
			for (int j = N - 1; j > i; j--) {
				if (i == j || str[i] != str[j]) continue;

				if (j - i == 1 || dp[i + 1][j - 1]) dp[i][j] = true;
			}
		}
	}
}