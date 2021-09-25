package com.youhogeon.kakao;

import java.util.*;

class Q4 {
	int INF = Integer.MAX_VALUE;
	int N;
	List<List<Line>> lines = new ArrayList<List<Line>>();

    public int solution(int n, int s, int a, int b, int[][] fares){
		N = n;

		for (int i = 0; i < n; i++) lines.add(new ArrayList<Line>());

		for (int i = 0; i < fares.length; i++){
			lines.get(fares[i][0] - 1).add(new Line(fares[i][1] - 1, fares[i][2]));
			lines.get(fares[i][1] - 1).add(new Line(fares[i][0] - 1, fares[i][2]));
		}

		int[] fromS = dijkstra(s -  1);
		int[] fromA = dijkstra(a - 1);
		int[] fromB = dijkstra(b - 1);
		int answer = INF;

		for (int i = 0; i < n; i++){
			if (fromS[i] == INF || fromA[i] == INF || fromB[i] == INF) continue;

			answer = Math.min(answer, fromS[i] + fromA[i] + fromB[i]);
		}

        return answer;
    }

	private int[] dijkstra(int from){
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int[] nodes = new int[N];
		boolean[] visited = new boolean[N];
		pq.offer(new Node(from, 0));

		for (int i = 0; i < N; i++) nodes[i] = INF;
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
		int to;
		int cost;

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