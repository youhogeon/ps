package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Q1504{
	int N, E, v1, v2;
	List<List<Line>> lines = new ArrayList<List<Line>>();

	public Q1504() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		E = Integer.parseInt(s[1]);

		for (int i = 0; i < N; i++) lines.add(new ArrayList<Line>());

		for (int i = 0; i < E; i++){
			s = bf.readLine().split(" ");
			int a = Integer.parseInt(s[0]) - 1;
			int b = Integer.parseInt(s[1]) - 1;
			int c = Integer.parseInt(s[2]);

			lines.get(a).add(new Line(b, c));
			lines.get(b).add(new Line(a, c));
		}

		s = bf.readLine().split(" ");
		v1 = Integer.parseInt(s[0]) - 1;
		v2 = Integer.parseInt(s[1]) - 1;
	}

	public long solve(){
		long[] from0 = dijkstra(0);
		long[] fromV1 = dijkstra(v1);
		long[] fromV2 = dijkstra(v2);

		long result = Math.min(from0[v1] + fromV1[v2] + fromV2[N - 1], from0[v2] + fromV2[v1] + fromV1[N - 1]);

		return (result >= 223372036854775807L)?-1:result;
	}

	public long[] dijkstra(int from){
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		long[] nodes = new long[N];
		boolean[] visited = new boolean[N];
		pq.offer(new Node(from, 0));

		for (int i = 0; i < N; i++) nodes[i] = 223372036854775807L;
		nodes[from] = 0;

		while(!pq.isEmpty()){
			Node thisNode = pq.poll();
			List<Line> thisLines = lines.get(thisNode.id);

			if (visited[thisNode.id]) continue;
			visited[thisNode.id] = true;

			for (int j = 0; j < thisLines.size(); j++){
				Line toLine = thisLines.get(j);
				long newCost = toLine.cost + nodes[thisNode.id];
				if (newCost < nodes[toLine.to]){
					pq.offer(new Node(toLine.to, newCost));
					nodes[toLine.to] = newCost;
				}
			}
		}

		return nodes;
	}

	class Line{
		int to, cost;

		public Line(int to, int cost){
			this.to = to;
			this.cost = cost;
		}
	}

	class Node implements Comparable<Node>{
		public int id;
		public long cost;

		public Node(int id, long cost){
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