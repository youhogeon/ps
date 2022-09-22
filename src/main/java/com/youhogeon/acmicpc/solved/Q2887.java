package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Q2887{
	int N;
	List<Node> x = new ArrayList<Node>();
	List<Node> y = new ArrayList<Node>();
	List<Node> z = new ArrayList<Node>();
	int[] parent;

	public Q2887() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		parent = new int[N];
		for (int i = 0; i < N; i++) parent[i] = -1;
		
		for (int i = 0; i < N; i++){
			String[] s = bf.readLine().split(" ");
			
			x.add(new Node(i, Integer.parseInt(s[0])));
			y.add(new Node(i, Integer.parseInt(s[1])));
			z.add(new Node(i, Integer.parseInt(s[2])));
		}
	}
	
	public long solve(){
		x.sort(Comparator.naturalOrder());
		y.sort(Comparator.naturalOrder());
		z.sort(Comparator.naturalOrder());
		
		List<Line> xyz = new ArrayList<Line>();
		for (int i = 0; i < N - 1; i++){
			xyz.add(new Line(x.get(i).id, x.get(i + 1).id, x.get(i + 1).v - x.get(i).v));
			xyz.add(new Line(y.get(i).id, y.get(i + 1).id, y.get(i + 1).v - y.get(i).v));
			xyz.add(new Line(z.get(i).id, z.get(i + 1).id, z.get(i + 1).v - z.get(i).v));
		}

		xyz.sort(Comparator.naturalOrder());

		long sum = 0;
		int count = 0;
		for (int i = 0; i < xyz.size(); i++){
			Line me = xyz.get(i);

			int a = findParent(me.from);
			int b = findParent(me.to);

			if (a == b) continue;

			parent[Math.min(a, b)] = Math.max(a, b);
			sum += me.cost;
			count++;

			if (count == N - 1) break;
		}

		return sum;
	}

	public int findParent(int p){
		while (true){
			if (parent[p] < 0) return p;
			
			p = parent[p];
		}
	}

	class Node implements Comparable<Node>{
		int id, v;

		public Node(int id, int v){
			this.id = id;
			this.v = v;
		}

		public int compareTo(Node n){
			if (this.v > n.v) return 1;
			if (this.v < n.v) return -1;
			return 0;
		}
	}

	class Line implements Comparable<Line>{
		int from, to, cost;

		public Line(int from, int to, int cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		public int compareTo(Line l){
			if (this.cost > l.cost) return 1;
			if (this.cost < l.cost) return -1;

			return 0;
		}
	}
}