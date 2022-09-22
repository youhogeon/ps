package com.youhogeon.acmicpc.solved;

import java.util.Scanner;

class Q11053{
	int N;
	int[] arr;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N];

		for (int i = 0; i < N; i++){
			arr[i] = sc.nextInt();
		}

		sc.close();
	}

	public int solve(){
		int max = -1;
		int[] dp = new int[N];

		for (int i = 0; i < N; i++){
			for (int j = 0; j < i; j++){
				if (arr[j] < arr[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
			}

			max = Math.max(max, dp[i]);
		}

		return max + 1;
	}
}