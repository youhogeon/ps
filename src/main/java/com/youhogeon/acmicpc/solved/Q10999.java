package com.youhogeon.acmicpc.solved;

import java.util.Scanner;

class Q10999{
	private int N, K, M;

	private long[] arr;
	private long[] lazy;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		K = sc.nextInt();
		M = sc.nextInt();

		this.N = (int)Math.pow(2, 1 + (int)(Math.log(N - 1) / Math.log(2)));
		arr = new long[2 * this.N - 1];
		lazy = new long[2 * this.N - 1];

		for (int i = 0; i < N; i++){
			initTree(i, sc.nextLong());
		}

		for (int i = 0; i < K + M; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			
			if (a == 1){
				long d = sc.nextLong();
				updateTree(0, this.N - 1, 0, b - 1, c - 1, d);
			}else System.out.println(getSum(0, this.N - 1, 0, b - 1, (int)c - 1));
		}

		sc.close();
	}

	public void solve(){

	}

	private void updateLazy(int begin, int end, int i){
		long lz = lazy[i];
		if (lz == 0) return;

		arr[i] += (end - begin + 1) * lz;
		lazy[i] = 0;

		if (begin == end) return;

		lazy[2 * i + 1] += lz;
		lazy[2 * i + 2] += lz;
	}

	public long getSum(int begin, int end, int i, int left, int right){
		if (left > end || right < begin) return 0;

		updateLazy(begin, end, i);
		if (left <= begin && right >= end) return arr[i];

		int n = (end - begin + 1) / 2;
		return getSum(begin, begin + n - 1, i * 2 + 1, left, right) + getSum(end - n + 1, end, i * 2 + 2, left, right);
	}

	public void initTree(int i, long n){
		int k = N - 1 + i;
		long diff = n - arr[k];

		while (true){
			arr[k] += diff;
			
			if (k == 0) break;
			k = (k - 1) / 2;
		}
	}

	public void updateTree(int begin, int end, int i, int left, int right, long diff){
		if (left > end || right < begin) return;

		if (left <= begin && right >= end){
			lazy[i] += diff;

			int j = i;
			while (j > 0){
				j = (j - 1) / 2;

				arr[j] += diff * (end - begin + 1);
			}
			return;
		}

		int n = (end - begin + 1) / 2;
		updateTree(begin, begin + n - 1, i * 2 + 1, left, right, diff);
		updateTree(end - n + 1, end, i * 2 + 2, left, right, diff);
	}
}