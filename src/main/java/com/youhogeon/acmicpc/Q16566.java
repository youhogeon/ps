package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q16566{
	int N, M, K;
	int[] tree, order;
	boolean[] minsoo;
	final int max = 1 << 22;

	public Q16566() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);

		tree = new int[(1 << 23)];
		order = new int[K];

		str = bf.readLine().split(" ");
		for (int i = 0; i < M; i++) addTree(Integer.parseInt(str[i]));

		str = bf.readLine().split(" ");
		for (int i = 0; i < K; i++) order[i] = Integer.parseInt(str[i]);
	}

	public int solve() {
		for (int i = 0; i < K; i++) {
			System.out.println(findTree(order[i] + 1));
		}

		return 0;
	}

	public void addTree(int idx) {
		idx += max - 1;

		while (idx >= 1) {
			tree[idx]++;
			idx /= 2;
		}
	}

	public int deleteTree(int x) {
		int idx = x + max - 1;

		if (tree[idx] == 0) return 0;

		while (idx >= 1) {
			tree[idx]--;
			idx /= 2;
		}
		
		return x;
	}

	public int findTree(int bound) {
		int idx = bound + max - 1;

		if (tree[idx] > 0) {
			return deleteTree(bound);
		}

		while (idx > 1) {
			boolean isEven = idx % 2 == 0;
			if (isEven && tree[idx + 1] > 0) {
				idx++;
				break;
			}
			idx /= 2;
		}
		//올라가면서 1을 찾음

		while (idx < max) {
			idx *= 2;
			if (tree[idx] == 0) idx++;
		}

		return deleteTree(idx - max + 1);
	}
}