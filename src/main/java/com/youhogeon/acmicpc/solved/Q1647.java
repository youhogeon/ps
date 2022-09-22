package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

class Q1647{
	int N, M;
	Queue<Line> lines = new PriorityQueue<Line>();
	int[] nodes;

	public Q1647() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		nodes = new int[N];
		for (int i = 0; i < N; i++) nodes[i] = -1;

		for (int i = 0; i < M; i++){
			s = bf.readLine().split(" ");

			lines.offer(new Line(Integer.parseInt(s[0]) - 1, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2])));
		}
	}

	public int getParent(int x){
		if (nodes[x] == -1) return x;

		nodes[x] = getParent(nodes[x]);

		return nodes[x];
	}

	public int solve(){
		int count = 0;
		int sum = 0;

		while (!lines.isEmpty()){
			if (count == N - 2) break;

			Line me = lines.poll();

			if (getParent(me.to) != -1 && getParent(me.to) == getParent(me.from)) continue;

			count++;
			sum += me.cost;

			nodes[getParent(me.to)] = getParent(me.from);
		}

		return sum;
	}

	class Line implements Comparable<Line>{
		int from, to, cost;

		public Line(int from, int to, int cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		public int compareTo(Line line){
			if (this.cost < line.cost) return -1;
			if (this.cost > line.cost) return 1;
			
			return 0;
		}
	}
}