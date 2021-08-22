package com.youhogeon.acmicpc;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Q1238{
	int N, M, X;
	List<List<Line>> lines = new ArrayList<List<Line>>();

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt() - 1;

		for (int i = 0; i < N; i++) lines.add(new ArrayList<Line>());

		while (M-- > 0){
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int c = sc.nextInt();

			lines.get(a).add(new Line(b, c));
		}

		sc.close();
	}

	public void solve(){
		int max = -1;
		for (int i = 0; i < N; i++){
			max = Math.max(max, dijkstra(i, X) + dijkstra(X, i));
		}
		
		System.out.println(max);
	}

	private int dijkstra(int from, int to){
		if (from == to) return 0;

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int[] nodes = new int[N];
		boolean[] visited = new boolean[N];
		pq.offer(new Node(from, 0));

		for (int i = 0; i < N; i++) nodes[i] = 2147000000;
		nodes[from] = 0;

		while(!pq.isEmpty()){
			Node thisNode = pq.poll();
			List<Line> thisLines = lines.get(thisNode.id);

			if (visited[thisNode.id]) continue;
			visited[thisNode.id] = true;

			for (int j = 0; j < thisLines.size(); j++){
				Line toLine = thisLines.get(j);
				int newCost = toLine.cost + nodes[thisNode.id];
				if (newCost < nodes[toLine.to]){
					pq.offer(new Node(toLine.to, newCost));
					nodes[toLine.to] = newCost;
				}
			}

			if (to == thisNode.id) break;
		}

		return nodes[to];
	}

	class Line{
		public int to, cost;

		public Line(int to, int cost){
			this.to = to;
			this.cost = cost;
		}
	}

	class Node implements Comparable<Node>{
		public int id, cost;

		public Node(int id, int cost){
			this.id = id;
			this.cost = cost;
		}

		public int compareTo(Node n){
			if (n.cost > this.cost) return -1;
			if (n.cost == this.cost) return 0;

			return 1;
		}
	}
}