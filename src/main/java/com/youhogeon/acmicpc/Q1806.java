package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q1806{
	int N, M;
	int[] arr;

	public Q1806() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		arr = new int[N];

		s = bf.readLine().split(" ");
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(s[i]);
	}

	public int solve(){
		int sum = arr[0];
		int begin = 0;
		int end = 1;
		int min = 99999999;

		while(true){
			if (sum >= M) sum -= arr[begin++];
			
			if (sum < M && end > N - 1) break;
			if (sum < M) sum += arr[end++];

			if (sum >= M) min = Math.min(min, end - begin);
		}

		return min==99999999?0:min;
	}
}