package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Q2098{
	int N;
	int[][] cost;
	Map<Integer, Long> cache = new HashMap<Integer, Long>();
	int max;

	public Q2098() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);

		cost = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			str = bf.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(str[j]);
				if (cost[i][j] == 0) cost[i][j] = Integer.MAX_VALUE;
			}
		}

		max = (1 << N) - 1;
	}

	public int solve() {
		int result = (int)calc(0, 1);
		return result;
	}

	public long calc(int id, int visited) {
		long min = Long.MAX_VALUE;
		int key = makeKey(id, visited);

		if (visited == max) return cost[id][0];

		if (cache.containsKey(key)) return cache.get(key);

		for (int i = 0; i < N; i++) {
			if ((visited & (1 << i)) != 0) continue;

			min = Math.min(min, calc(i, visited | (1 << i)) + cost[id][i]);
		}

		cache.put(key, min);
		return min;
	}

	public int makeKey(int id, int visited) {
		int key = id << 20;
		key = key | visited;

		return key;
	}
}