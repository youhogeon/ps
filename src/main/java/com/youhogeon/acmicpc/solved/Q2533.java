package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Q2533{
	int N;
	Node[] nodes;
	int cache[][];

	public Q2533() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		nodes = new Node[N];
		cache = new int[N][2];

		for (int i = 0; i < N; i++) {
			nodes[i] = new Node();
			cache[i][0] = -1;
			cache[i][1] = -1;
		}
		
		for (int i = 1; i < N; i++) {
			String[] str = bf.readLine().split(" ");
			int a = Integer.parseInt(str[0]) - 1, b = Integer.parseInt(str[1]) - 1;

			nodes[a].child.add(b);
			nodes[b].child.add(a);
		}
	}

	public int solve() {
		return Math.min(calc(-1, 0, true), calc(-1, 0, false));
	}

	public int calc(int parent, int current, boolean isEarlyAdaptor) {
		int sum = isEarlyAdaptor ? 1 : 0;

		if (nodes[current].child.size() == 0) return sum;

		if (cache[current][sum] > 0) return cache[current][sum];

		Iterator<Integer> it = nodes[current].child.iterator();
		while (it.hasNext()) {
			int item = it.next();
			if (item == parent) continue;

			if (!isEarlyAdaptor) sum += calc(current, item, true);
			else sum += Math.min(calc(current, item, true), calc(current, item, false));
		}

		cache[current][isEarlyAdaptor ? 1 : 0] = sum;

		return sum;
	}

	class Node {
		List<Integer> child = new ArrayList<Integer>();
	}
}