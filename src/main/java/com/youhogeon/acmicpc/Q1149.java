package com.youhogeon.acmicpc;

import java.util.Scanner;

class Q1149{
	int N;
	int[][] input;
	int[][] arr;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();		
		input = new int[N][3];
		arr = new int[N][3];

		for (int i = 0; i < N; i++){
			input[i][0] = sc.nextInt();
			input[i][1] = sc.nextInt();
			input[i][2] = sc.nextInt();
		}
		arr[0] = input[0];

		sc.close();
	}

	public int solve(){
		for (int j = 1; j < N; j++){
			arr[j][0] = input[j][0] + Math.min(arr[j - 1][1], arr[j - 1][2]);
			arr[j][1] = input[j][1] + Math.min(arr[j - 1][0], arr[j - 1][2]);
			arr[j][2] = input[j][2] + Math.min(arr[j - 1][1], arr[j - 1][0]);
		}

		return Math.min(Math.min(arr[N - 1][0], arr[N - 1][1]), arr[N - 1][2]);
	}
}