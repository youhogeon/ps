package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q2096{
	int N;
	int[][] arr;
	int[][] max;
	int[][] min;
	int[] dx = {-1, 0, 1};
	
	public Q2096() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		arr = new int[N][3];
		min = new int[N][3];
		max = new int[N][3];

		for (int i = 0; i < N; i++){
			String[] s = bf.readLine().split(" ");
			arr[i][0] = Integer.parseInt(s[0]);
			arr[i][1] = Integer.parseInt(s[1]);
			arr[i][2] = Integer.parseInt(s[2]);
		}

		max[0] = arr[0];
		min[0] = arr[0];
	}

	public void solve(){
		for (int i = 1; i < N; i++){
			max[i][0] = Math.max(max[i - 1][0], max[i - 1][1]) + arr[i][0];
			min[i][0] = Math.min(min[i - 1][0], min[i - 1][1]) + arr[i][0];
			max[i][1] = max(max[i - 1][0], max[i - 1][1], max[i - 1][2]) + arr[i][1];
			min[i][1] = min(min[i - 1][0], min[i - 1][1], min[i - 1][2]) + arr[i][1];
			max[i][2] = Math.max(max[i - 1][2], max[i - 1][1]) + arr[i][2];
			min[i][2] = Math.min(min[i - 1][2], min[i - 1][1]) + arr[i][2];
		}

		System.out.print(max(max[N - 1][0], max[N - 1][1], max[N - 1][2]));
		System.out.print(" ");
		System.out.println(min(min[N - 1][0], min[N - 1][1], min[N - 1][2]));
	}

	public int max(int a, int b, int c){
		return Math.max(a, Math.max(b, c));
	}

	public int min(int a, int b, int c){
		return Math.min(a, Math.min(b, c));
	}
}