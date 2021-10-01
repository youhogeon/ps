package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q2292{
	int N;

	public Q2292() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
	}

	public int solve(){
		if (N == 1) return 1;

		int i = 1;

		while (3 * i * (i++ +1) + 1 < N);

		return i;
	}
}