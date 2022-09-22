package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Q1197 {

	int V;
	PriorityQueue<Line> lines = new PriorityQueue<Line>();
	int[] head;

	public Q1197() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		V = Integer.parseInt(str[0]);
		int E = Integer.parseInt(str[1]);

		head = new int[V];
		for (int i = 1; i < V; i++) head[i] = i;
		
		while (E-- > 0) {
			str = bf.readLine().split(" ");
			
			lines.add(new Line(Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2])));
		}
	}

	public int solve() {
		int sum = 0;

		while (!lines.isEmpty() && V > 1){
			Line l = lines.poll();

			if (find(l.from) == find(l.to)) continue;

			union(l.from, l.to);
			sum += l.cost;
			V--;
		}

		return sum;
	}

	public int find(int a) {
		Queue<Integer> queue = new LinkedList<Integer>();

		while (a != head[a]) {
			queue.add(a);
			a = head[a];
		}

		while (!queue.isEmpty()) {
			head[queue.poll()] = a;
		}

		return a;
	}

	public void union(int a, int b) {
		head[find(a)] = find(b);
	}

	class Line implements Comparable<Line> {

		int from, to, cost;

		public Line(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Line o) {
			if (this.cost > o.cost) return 1;
			if (this.cost < o.cost) return -1;

			return 0;
		}

	}

}