package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q1806{
	int N, S;
	int[] nums;

	public Q1806() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		S = Integer.parseInt(str[1]);

		str = bf.readLine().split(" ");
		nums = new int[N];
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(str[i]);
	}

	public int solve(){
		int idxA = 0, idxB = 0, sum = 0, min = 0x7FFFFFFF;

		while (true) {
			if (sum >= S) {
				sum -= nums[idxA];
				idxA++;
			}else if (sum < S) {
				if (idxB >= N) break;

				sum += nums[idxB];
				idxB++;
			}

			if (sum >= S) {
				min = Math.min(min, idxB - idxA);
			}

		}

		return min > 100000 ? 0 : min;
	}
}