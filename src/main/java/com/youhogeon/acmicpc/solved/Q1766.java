package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Q1766{
	int N, M;
	Node[] nodes;

	public Q1766() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		nodes = new Node[N];

		for (int i = 0; i < N; i++) nodes[i] = new Node();

		for (int i = 0; i < M; i++){
			s = bf.readLine().split(" ");
			int a = Integer.parseInt(s[0]) - 1;
			int b = Integer.parseInt(s[1]) - 1;

			nodes[a].child.add(b);
			nodes[b].parent++;
		}
	}

	public String solve(){
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

		List<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < N; i++){
			if (nodes[i].isSolvable()) queue.offer(i);
		}

		while (!queue.isEmpty()){
			int v = queue.poll();

			result.add(v);

			for (int i = 0; i < nodes[v].child.size(); i++){
				int child = nodes[v].child.get(i);
				nodes[child].parent--;

				if (nodes[child].isSolvable()) queue.offer(child);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.size(); i++){
			sb.append(result.get(i) + 1);
			if (i != result.size() - 1) sb.append(" ");
		}

		return sb.toString();
	}

	class Node{
		int parent = 0;
		List<Integer> child = new ArrayList<Integer>();

		public boolean isSolvable(){
			return (parent > 0)?false:true;
		}
	}
}