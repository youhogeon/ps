package com.youhogeon.acmicpc;

import java.util.Scanner;

class Q11404{
	int N, M;
	int[][] matrix;
	final int INF = 100000000;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		matrix = new int[N][N];

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++) matrix[i][j] = i==j?0:INF;
		}

		while (M-- > 0){
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int c = sc.nextInt();

			matrix[a][b] = Math.min(matrix[a][b], c);
		}

		sc.close();
	}

	public void solve(){
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				for (int k = 0; k < N; k++){
					matrix[j][k] = Math.min(matrix[j][k], matrix[j][i] + matrix[i][k]);
				}
			}
		}

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				if (matrix[i][j] == INF) matrix[i][j] = 0;

				System.out.print(String.valueOf(matrix[i][j]));
				if (j != N - 1) System.out.print(" ");
			}
			System.out.println();
		}
	}
}