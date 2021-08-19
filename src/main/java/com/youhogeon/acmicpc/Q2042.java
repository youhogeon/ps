package com.youhogeon.acmicpc;

import java.util.Scanner;

class Q2042{
	private int N, K, M;

	private long[] arr;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		K = sc.nextInt();
		M = sc.nextInt();

		this.N = (int)Math.pow(2, 1 + (int)(Math.log(N - 1) / Math.log(2)));
		arr = new long[2 * this.N - 1];

		for (int i = 0; i < N; i++){
			updateTree(i, sc.nextLong());
		}

		for (int i = 0; i < K + M; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			long c = sc.nextLong();

			if (a == 1) updateTree(b - 1, c);
			else System.out.println(getSum(0, this.N - 1, 0, b - 1, (int)c - 1));
		}

		sc.close();
	}

	public void solve(){

	}

	public long getSum(int begin, int end, int i, int left, int right){
		if (left > end || right < begin) return 0;

		if (left <= begin && right >= end) return arr[i];

		int n = (end - begin + 1) / 2;
		return getSum(begin, begin + n - 1, i * 2 + 1, left, right) + getSum(end - n + 1, end, i * 2 + 2, left, right);
	}

	public void updateTree(int i, long n){
		int k = N - 1 + i;
		long diff = n - arr[k];

		while (true){
			arr[k] += diff;
			
			if (k == 0) break;
			k = (k - 1) / 2;
		}
	}
}