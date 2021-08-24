package com.youhogeon.acmicpc;

import java.util.Scanner;

class Q1865{
	int N;
	int[][] matrix;
	final int INF = 1000000000;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		while (T-- > 0){
			N = sc.nextInt();
			int M = sc.nextInt();
			int W = sc.nextInt();
			matrix = new int[N][N];
	
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++) matrix[i][j] = i==j?0:INF;
			}
	
			for (int i = 0; i < M + W; i++){
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				int c = sc.nextInt();

				if (i >= M) c *= -1;
				else matrix[b][a] = Math.min(matrix[b][a], c);
	
				matrix[a][b] = Math.min(matrix[a][b], c);
			}

			System.out.println(solve());
		}

		sc.close();
	}

	public String solve(){
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				for (int k = 0; k < N; k++){
					matrix[j][k] = Math.min(matrix[j][k], matrix[j][i] + matrix[i][k]);
				}
			}
		}

		for (int i = 0; i < N; i++){
			if (matrix[i][i] < 0) return "YES";
		}

		return "NO";
	}
}