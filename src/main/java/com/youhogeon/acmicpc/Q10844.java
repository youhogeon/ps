package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q10844{
	int N;
	final int X = 1000000000;
	int[][] cache;

	public Q10844() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		cache = new int[N][10];
	}

	public int solve() {
		int sum = 0;

		for (int i = 1; i <= 9; i++) sum = (sum + calc(i, 1)) % X;

		return sum;
	}

	public int calc(int last, int n) {
		if (n == N) return 1;

		int result;

		if (n == N - 1) {
			if (last == 0 || last == 9) return 1;
			else return 2;
		}

		if (cache[n][last] > 0) return cache[n][last];

		if (last == 0) result = calc(1, n + 1);
		else if (last == 9) result = calc(8, n + 1);
		else result = (calc(last + 1, n + 1) % X) + (calc(last - 1, n + 1) % X);

		result %= X;

		cache[n][last] = result;

		return result;
	}
}