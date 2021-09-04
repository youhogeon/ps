package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q2467{
	int N;
	int[] arr;

	public Q2467() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];

		String[] s = bf.readLine().split(" ");
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(s[i]);
	}

	public String solve(){
		int begin = 0;
		int end = N - 1;

		int min = Integer.MAX_VALUE;
		int minA = 0, minB = 0;

		while (begin < end){
			int sum = arr[begin] + arr[end];

			if (min > Math.abs(sum)){
				minA = begin;
				minB = end;
				min = Math.abs(sum);
			}

			if (sum == 0) break;

			if (sum > 0) end--;
			else begin++;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(arr[minA]);
		sb.append(" ");
		sb.append(arr[minB]);

		return sb.toString();
	}
}