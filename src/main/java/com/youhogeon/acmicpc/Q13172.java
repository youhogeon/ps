package com.youhogeon.acmicpc;

import java.io.IOException;
import java.util.Scanner;

class Q13172{
	int N;
	int[][] data;

	public Q13172() throws IOException{
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		data = new int[N][2];

		for (int i = 0; i < N; i++){
			data[i][0] = sc.nextInt();
			data[i][1] = sc.nextInt();
		}

		sc.close();
	}

	public int solve(){
		long result = 0;

		for (int i = 0; i < N; i++){
			long b1 = power(data[i][0], 1000000005) % 1000000007;
			long mul = ((data[i][1] * b1)) % 1000000007;

			result = (result + mul) % 1000000007;
		}

		return (int)result;
	}

	public int power(int a, int n){
		if (n == 1) return a;

		long p = power(a, n / 2) % 1000000007;
		p = (p * p) % 1000000007;

		if (n % 2 == 1) p = (p * a) % 1000000007;

		return (int)p;
	}
}