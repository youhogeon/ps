package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q12850{
	int N;
	int[][] arr = {{0,1,0,0,0,1,0,0},{1,0,1,0,0,1,0,0},{0,1,0,1,0,1,1,0},{0,0,1,0,1,0,1,0},{0,0,0,1,0,0,0,1},{1,1,1,0,0,0,1,0},{0,0,1,1,0,1,0,1},{0,0,0,0,1,0,1,0}};

	public Q12850() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
	}

	public int solve(){
		int[][] power = power(N);

		return power[0][0];
	}

	public int[][] power(int N){
		if (N == 1) return arr;

		int[][] a = power(N / 2);
		a = mul(a, a);

		if (N % 2 == 1) a = mul(a, arr);

		return a;
	}

	public int[][] mul(int[][] a, int[][] b){
		int[][] c = new int[8][8];

		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				for (int k = 0; k < 8; k++){
					c[i][j] += (int)(((long)a[i][k] * (long)b[k][j]) % 1000000007);
					c[i][j] = c[i][j] % 1000000007;
				}
			}
		}

		return c;
	}
}