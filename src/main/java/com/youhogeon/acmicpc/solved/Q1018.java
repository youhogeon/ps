package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1018{
	int N, M;
	boolean[][] map;
	boolean[][] chess = new boolean[8][8];

	public Q1018() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		map = new boolean[N][M];

		for (int i = 0 ; i < N; i++){
			char[] c = bf.readLine().toCharArray();

			for (int j = 0; j < M; j++) map[i][j] = c[j]=='W';
		}
	}

	public int solve(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++) chess[i][j] = (i + j) % 2 == 0;
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= N - 8; i++){
			for (int j = 0; j <= M - 8; j++){
				min = Math.min(min, diff(i, j));
			}
		}

		return min;
	}

	public int diff(int n, int m){
		int same = 0;

		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (map[i + n][j + m] == chess[i][j]) same++;
			}
		}

		return Math.min(same, 64 - same);
	}
}