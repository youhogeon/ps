package com.youhogeon.acmicpc.solved;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Q11779{
	int N;
	int start, end;
	List<List<Line>> lines = new ArrayList<List<Line>>();

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int M = sc.nextInt();

		for (int i = 0; i < N; i++) lines.add(new ArrayList<Line>());

		while(M-- > 0){
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int c = sc.nextInt();

			lines.get(a).add(new Line(b, c));
		}

		start = sc.nextInt() - 1;
		end = sc.nextInt() - 1;

		sc.close();
	}

	public void solve(){
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int[] nodes = new int[N];
		boolean[] visited = new boolean[N];

		for (int i = 0; i < N; i++) nodes[i] = 2147000000;
		nodes[start] = 0;
		pq.offer(new Node(start, 0));

		int[] availLines = new int[N];

		while (!pq.isEmpty()){
			Node nd = pq.poll();

			if (visited[nd.id]) continue;
			visited[nd.id] = true;

			List<Line> lns = lines.get(nd.id);

			for (int i = 0; i < lns.size(); i++){
				Line toLine = lns.get(i);
				int newCost = nd.cost + toLine.cost;
				if (newCost < nodes[toLine.to]){
					nodes[toLine.to] = newCost;
					pq.offer(new Node(toLine.to, newCost));
					availLines[toLine.to] = nd.id;
				}
			}

			if (nd.id == end) break;
		}

		List<Integer> way = new LinkedList<Integer>();

		int nextNode = end;
		while (true){
			way.add(0, nextNode);
			if (nextNode == start) break;

			nextNode = availLines[nextNode];
		}

		System.out.println(nodes[end]);
		int size = way.size();
		System.out.println(size);
		for (int i = 0; i < size; i++){
			System.out.print(way.get(i) + 1);
			if (i != size - 1) System.out.print(" ");
		}
		System.out.println();
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