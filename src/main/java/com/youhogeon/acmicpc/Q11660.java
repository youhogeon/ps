package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q11660{
	int N, M;
	int[][] input;
	long[][] matrix;

	public void scan() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");

		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		input = new int[N][N];
		matrix = new long[N + 1][N + 1];

		for (int i = 0; i < N; i++){
			s = bf.readLine().split(" ");
			for (int j = 0; j < N; j++) input[i][j] = Integer.parseInt(s[j]);
		}

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				matrix[i + 1][j + 1] = matrix[i][j + 1] + matrix[i + 1][j] - matrix[i][j] + input[i][j];
			}
		}

		while (M-- > 0){
			s = bf.readLine().split(" ");
			System.out.println(solve(s));
		}
	}

	public long solve(String[] s){
		int a = Integer.parseInt(s[0]);
		int b = Integer.parseInt(s[1]);
		int c = Integer.parseInt(s[2]);
		int d = Integer.parseInt(s[3]);

		return matrix[c][d] - matrix[a - 1][d] - matrix[c][b - 1] + matrix[a - 1][b - 1];
	}
}