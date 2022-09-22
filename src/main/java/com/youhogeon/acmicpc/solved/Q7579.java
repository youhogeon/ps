package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q7579{
	int N, M;
	int[] mem, cost;

	public Q7579() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		
		mem = new int[N];
		cost = new int[N];

		s = bf.readLine().split(" ");
		for (int i = 0; i < N; i++) mem[i] = Integer.parseInt(s[i]);
		s = bf.readLine().split(" ");
		for (int i = 0; i < N; i++) cost[i] = Integer.parseInt(s[i]);
	}

	public int solve(){
		int[][] arr = new int[N + 1][10001];
		
		for (int j = 0; j <= 10000; j++){ //for cost
			for (int i = 1; i <= N; i++){ //for app
				if (cost[i - 1] > j) arr[i][j] = arr[i - 1][j];
				else arr[i][j] = Math.max(arr[i - 1][j], arr[i - 1][j - cost[i - 1]] + mem[i - 1]);
				
				if (arr[i][j] >= M) return j;
			}
		}

		return -1;
	}
}