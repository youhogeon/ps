package com.youhogeon.acmicpc;

import java.util.Scanner;

class Q9465{
	public void scan(){
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		while (T-- > 0){
			int n = sc.nextInt();
			int[][] arr = new int[2][n + 2];

			for (int i = 0; i < 2; i++){
				for (int j = 0; j < n; j++){
					arr[i][j] = sc.nextInt();
				}
			}

			solve(arr, n);
		}

		sc.close();
	}

	public void solve(int[][] arr, int n){

		for (int i = n - 1; i >= 0; i--){
			arr[0][i] += Math.max(arr[1][i + 1], arr[1][i + 2]);
			arr[1][i] += Math.max(arr[0][i + 1], arr[0][i + 2]);
		}

		System.out.println(Math.max(arr[0][0], arr[1][0	]));
	}
}