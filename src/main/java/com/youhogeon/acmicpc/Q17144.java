package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q17144{
	int R, C, T;
	int[][] arr;
	int[][] adder;
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, 1, -1};
	int position = 0;

	public Q17144() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = bf.readLine().split(" ");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		T = Integer.parseInt(s[2]);
		arr = new int[R][C];
		adder = new int[R][C];

		for (int i = 0; i < R; i++){
			s = bf.readLine().split(" ");

			for (int j = 0; j < C; j++){
				arr[i][j] = Integer.parseInt(s[j]);
				if (arr[i][j] == -1) position = i - 1;
			}
		}
	}

	public int solve(){
		while (T-- > 0){
			diffusion();
			cycle();
		}
		
		int sum = 0;
		for (int i = 0; i < R; i++){
			for (int j = 0; j < C; j++) sum += arr[i][j];
		}

		return sum + 2;
	}

	public void cycle(){
		for (int i = position - 2; i >= 0; i--) arr[i + 1][0] = arr[i][0];
		for (int j = 1; j < C; j++) arr[0][j - 1] = arr[0][j];
		for (int i = 0; i < position; i++) arr[i][C - 1] = arr[i + 1][C - 1];
		for (int j = C - 1; j > 1; j--) arr[position][j] = arr[position][j - 1];
		arr[position][1] = 0;
		//upper cycle
		
		for (int i = position + 2; i < R - 1; i++) arr[i][0] = arr[i + 1][0];
		for (int j = 1; j < C; j++) arr[R - 1][j - 1] = arr[R - 1][j];
		for (int i = R - 1; i > position; i--) arr[i][C - 1] = arr[i - 1][C - 1];
		for (int j = C - 1; j >= 2; j--) arr[position + 1][j] = arr[position + 1][j - 1];
		arr[position + 1][1] = 0;
	}

	public void diffusion(){
		for (int i = 0; i < R; i++){
			for (int j = 0; j < C; j++){
				int dust = arr[i][j] / 5;
				if (arr[i][j] <= 0) continue;

				for (int k = 0; k < 4; k++){
					if (i + dx[k] < 0 || i + dx[k] >= R || j + dy[k] < 0 || j + dy[k] >= C) continue;
					if (arr[i + dx[k]][j + dy[k]] < 0) continue;

					arr[i][j] -= dust;
					adder[i + dx[k]][j + dy[k]] += dust;
				}
			}
		}

		for (int i = 0; i < R; i++){
			for (int j = 0; j < C; j++){
				arr[i][j] += adder[i][j];
				adder[i][j] = 0;
			}
		}
	}
}