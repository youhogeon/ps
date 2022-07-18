package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q2042{
	int N, M, K;
	long[] nums;
	long[] subSum;

	public Q2042() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);

		nums = new long[N];
		subSum = new long[N / 1000 + 1];

		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(bf.readLine());
			subSum[i / 1000] += nums[i];
		}

		for (int i = 0; i < M + K; i++) {
			str = bf.readLine().split(" ");
			int j = Integer.parseInt(str[1]) - 1;
			long k = Long.parseLong(str[2]);

			if (Integer.parseInt(str[0]) == 1) {
				subSum[j / 1000] -= nums[j];
				nums[j] = k;
				subSum[j / 1000] += k;
			} else {
				System.out.println(getSum(j, (int)k - 1));
			}
		}
	}

	public long getSum(int begin, int end){
		long sum = 0;

		for (int i = begin; i <= end; i++) {
			if (i % 1000 == 0 && i + 999 <= end) {
				sum += subSum[i / 1000];
				i += 999;
			} else sum += nums[i];
		}

		return sum;
	}
}