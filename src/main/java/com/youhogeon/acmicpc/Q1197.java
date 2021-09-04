package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Q1197{
	int V, E;
	int[] parent;
	PriorityQueue<Line> lines = new PriorityQueue<Line>();

	public Q1197() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		V = Integer.parseInt(s[0]);
		E = Integer.parseInt(s[1]);

		parent = new int[V];

		for (int i = 0; i < E; i++){
			s = bf.readLine().split(" ");
			lines.offer(new Line(Integer.parseInt(s[0]) - 1, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2])));
		}
	}

	public int solve(){
		int count = 0;
		int sum = 0;

		for (int i = 0; i < V; i++) parent[i] = i;

		while (!lines.isEmpty()){
			Line me = lines.poll();

			if (findParent(me.from) == findParent(me.to)) continue;

			parent[findParent(me.from)] = findParent(me.to);
			count++;
			sum += me.len;

			if (count == V - 1) break;
		}

		return sum;
	}

	private int findParent(int idx){
		if (parent[idx] == idx) return idx;

		return findParent(parent[idx]);
	}

	class Line implements Comparable<Line>{
		int len;
		int from, to;

		public Line(int from, int to, int len){
			this.from = from;
			this.to = to;
			this.len = len;
		}

		public int compareTo(Line l){
			if (l.len < this.len) return 1;
			if (l.len > this.len) return -1;

			return 0;
		}
	}
}