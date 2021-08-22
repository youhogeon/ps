package com.youhogeon.acmicpc;

import java.math.BigInteger;
import java.util.Scanner;

class Q2407{
	int N, R;
	long[] fac;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		R = sc.nextInt();

		fac = new long[N + 1];
		fac[0] = 1;

		sc.close();
	}

	public void solve(){
		BigInteger f = BigInteger.ONE;

		for (int i = N; i >= N - R + 1; i--){
			f = f.multiply(BigInteger.valueOf(i));
		}

		for (int i = 2; i <= R; i++){
			f = f.divide(BigInteger.valueOf(i));
		}

		System.out.println(f.toString());
	}
}