package com.youhogeon.acmicpc;

import java.util.Scanner;

class Q9663{
	int N;
	int[] matrix;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		matrix = new int[N];
		for (int i = 0; i < N; i++) matrix[i] = -999;

		sc.close();
	}

	public int solve(){
		return calc(matrix, 0);
	}

	private int calc(int[] matrix, int n){
		if (n == N) return 1;

		int count = 0;

		mainloop:
		for (int i = 0; i < N; i++){
			for (int j = n - 1, k = 1; j >= 0; j--){
				if (matrix[j] == i || matrix[j] == i - k || matrix[j] == i + k++) continue mainloop;
			}

			matrix[n] = i;
			count += calc(matrix, n + 1);
		}

		return count;
	}
}