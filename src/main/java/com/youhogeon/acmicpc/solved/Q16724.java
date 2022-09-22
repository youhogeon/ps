package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Q16724{
	int N, M;
	int[] heads;

	public Q16724() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		heads = new int[N * M];
		for (int i = 0; i < N * M; i++) heads[i] = i;

		int idx = 0;
		for (int i = 0; i < N; i++) {
			char[] lines = bf.readLine().toCharArray();

			for (int j = 0; j < M; j++) {
				int to;

				if (lines[j] == 'D') to = idx + M;
				else if (lines[j] == 'L') to = idx - 1;
				else if (lines[j] == 'R') to = idx + 1;
				else to = idx - M;

				union(idx, to);

				idx++;
			}
		}
	}

	public void union(int idx, int to) {
		heads[find(idx)] = find(to);
	}

	public int find(int idx) {
		Queue<Integer> queue = new LinkedList<Integer>();

		while (idx != heads[idx]) {
			queue.add(idx);
			idx = heads[idx];
		}

		while (!queue.isEmpty()) heads[queue.poll()] = idx;

		return idx;
	}

	public int solve(){
		Set<Integer> sets = new HashSet<Integer>();

		for (int i = 0; i < N * M; i++) {
			sets.add(find(i));
		}

		return sets.size();
	}
}