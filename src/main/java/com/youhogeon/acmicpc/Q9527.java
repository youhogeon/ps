package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q9527{
	long N, M;
	long[] fixed;

	public Q9527() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		N = Long.parseLong(s[0]);
		M = Long.parseLong(s[1]);
	}

	public long solve(){
		fixed = new long[54];

		for (int i = 0; i < 54; i++){
			fixed[i] = (long)Math.pow(2, i);

			for (int j = 0; j < i; j++) fixed[i] += fixed[j];
		}

		long x = f(M) - f(N - 1);
		
		return x;
	}

	public long f(long x){
		if (x == 0) return 0;

		int log = log2(x);
		long y = (long)Math.pow(2, log) - 1;

		if ((y + 1) * 2 == x + 1){
			long sum = 0;
			for (int i = 0; i <= log; i++) sum += fixed[i];
			return sum;
		}

		return f(y) + (x - y) + f(x - y - 1);
	}

	public int log2(long x){
		for (int i = 1; i <= 54; i++){
			if (Math.pow(2, i) > x) return i - 1;
		}

		return -1;
	}
}