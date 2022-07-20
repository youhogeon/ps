package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q1562{
	int N;
	final int X = 1000000000;
	int[][][] cache;

	public Q1562() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		cache = new int[N][10][1024];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 1024; k++) cache[i][j][k] = -1;
			}
		}
	}

	public int solve() {
		int sum = 0;

		for (int i = 1; i <= 9; i++) sum = (sum + calc(i, 1, (1 << i))) % X;

		return sum;
	}

	public int calc(int last, int n, int mask) {
		if (n == N) return mask == 1023 ? 1 : 0;

		int result;

		if (cache[n][last][mask] >= 0) return cache[n][last][mask];

		if (last == 0) result = calc(1, n + 1, mask | 2);
		else if (last == 9) result = calc(8, n + 1, mask | 256);
		else result = (calc(last + 1, n + 1, mask | (1 << (last + 1))) % X) + (calc(last - 1, n + 1, mask | (1 << last - 1)) % X);

		result %= X;

		cache[n][last][mask] = result;

		return result;
	}
}