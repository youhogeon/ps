package com.youhogeon;
import com.youhogeon.acmicpc.*;

// class Q14502{
// 	int N, M;

// 	public Q14502() throws IOException{
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

// 	}

//	public int solve(){

//	}
// }

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		try{
			Q2887 q = new Q2887();
			
			System.out.println(q.solve());
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}

class Q2887{
	int N;
	List<Node> lists = new ArrayList<Node>();
	int type = 1;

	public Q2887() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());

		for (int i = 0; i < N; i++){
			String[] s = bf.readLine().split(" ");

			lists.add(new Node(i, Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
		}
	}

	public long solve(){
		int[] min = new int[N];
		for (int i = 0; i < N; i++) min[i] = Integer.MAX_VALUE;

		lists.sort(Comparator.naturalOrder());

		for (int i = 0; i < N - 1; i++){
			Node me = lists.get(i);

			Node next = lists.get(i + 1);
			min[me.id] = Math.min(min[me.id], Math.abs(me.x - next.x));
		}

		type = 2;
		lists.sort(Comparator.naturalOrder());
		for (int i = 0; i < N - 1; i++){
			Node me = lists.get(i);

			Node prev = lists.get(i + 1);
			min[me.id] = Math.min(min[me.id], Math.abs(me.y - prev.y));
		}

		type = 3;
		lists.sort(Comparator.naturalOrder());

		for (int i = 0; i < N - 1; i++){
			Node me = lists.get(i);

			Node prev = lists.get(i + 1);
			min[me.id] = Math.min(min[me.id], Math.abs(me.z - prev.z));
		}

		long sum = 0;
		int max = -1;
		for (int i = 0; i < N; i++){
			max = Math.max(max, min[i]);
			sum += min[i];
		}

		return sum - max;
	}

	class Node implements Comparable<Node>{
		int id, x, y, z;

		public Node(int id, int x, int y, int z){
			this.id = id;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public int compareTo(Node n){
			if (type == 1){
				if (this.x > n.x) return 1;
				if (this.x < n.x) return -1;
			}else if (type == 2){
				if (this.y > n.y) return 1;
				if (this.y < n.y) return -1;
			}else{
				if (this.z > n.z) return 1;
				if (this.z < n.z) return -1;
			}

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