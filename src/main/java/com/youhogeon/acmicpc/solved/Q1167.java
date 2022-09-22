package com.youhogeon.acmicpc.solved;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Q1167{
	int N;
	List<List<Line>> lines = new ArrayList<List<Line>>();

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		for (int i = 0; i < N; i++) lines.add(new ArrayList<Line>());

		for (int i = 0; i < N; i++){
			int n = sc.nextInt() - 1;
			List<Line> subLines = lines.get(n);

			while (true){
				int a = sc.nextInt() - 1;
				if (a == -2) break;

				int b = sc.nextInt();

				subLines.add(new Line(a, b));
			}
		}

		sc.close();
	}

	public void solve(){
		
		/*for (int i = 0; i < N; i++){
			for (int j = i + 1; j < N; j++){
				max = Math.max(max, dijkstra(i, j));
			}
		}*/
		
		int[] res = dijkstra(0);
		int max = -1;
		int maxId = 0;
		int len = res.length;
		for (int i = 0; i < len; i++){
			if (max < res[i]){
				max = res[i];
				maxId = i;
			}
		}
		res = dijkstra(maxId);
		for (int i = 0; i < len; i++){
			max = Math.max(max, res[i]);
		}

		System.out.println(max);
	}

	private int[] dijkstra(int from){
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
		}

		return nodes;
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