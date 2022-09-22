package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q2166{
	int N;
	long[] x;
	long[] y;

	public Q2166() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		x = new long[N];
		y = new long[N];

		for (int i = 0; i < N; i++){
			String[] s = bf.readLine().split(" ");
			x[i] = Long.parseLong(s[0]);
			y[i] = Long.parseLong(s[1]);
		}
	}

	public void solve(){
		long sum = 0;

		for (int i = 0; i < N; i++) sum += (x[i] * y[(i + 1) % N] - x[(i + 1) % N] * y[i]);
		
		sum = Math.abs(sum);

		System.out.print(sum / 2);
		System.out.print(".");
		System.out.print(sum % 2 == 0?0:5);
	}
}