package com.youhogeon.acmicpc.solved;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Q14938{
	int n, m, r;
	int[] t;
	List<List<Line>> lines = new ArrayList<List<Line>>();

	public void scan(){
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		r = sc.nextInt();

		t = new int[n];
		for (int i = 0; i < n; i++){
			t[i] = sc.nextInt();
			lines.add(new ArrayList<Line>());
		}

		while (r-- > 0){
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int c = sc.nextInt();

			lines.get(a).add(new Line(b, c));
			lines.get(b).add(new Line(a, c));
		}

		sc.close();
	}

	public void solve(){
		int max = -1;
		for (int i = 0; i < n; i++){
			int[] arr = dijkstra(i);
			int sum = 0;
			for (int j = 0; j < n; j++){
				if (arr[j] <= m) sum += t[j];
			}

			max = Math.max(max, sum);
		}

		System.out.println(max);
	}
	
	private int[] dijkstra(int from){
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int[] nodes = new int[n];
		boolean[] visited = new boolean[n];
		pq.offer(new Node(from, 0));

		for (int i = 0; i < n; i++) nodes[i] = 2147000000;
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

	class Line{
		int to, cost;

		public Line(int to, int cost){
			this.to = to;
			this.cost = cost;
		}
	}
}