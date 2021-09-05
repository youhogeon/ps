package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Q2623{
	int N, M;
	int count;
	Node[] nodes;

	public Q2623() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		nodes = new Node[N];

		for (int i = 0; i < N; i++) nodes[i] = new Node();

		for (int i = 0; i < M; i++){
			s = bf.readLine().split(" ");

			int cnt = Integer.parseInt(s[0]);
			for (int j = 1; j < cnt; j++){
				int parent = Integer.parseInt(s[j]) - 1;
				int child = Integer.parseInt(s[j + 1]) - 1;
				if (nodes[parent].addChild(child)) nodes[child].parent++;
			}
		}
	}

	public String solve(){
		Queue<Integer> result = new LinkedList<Integer>();
		
		count = N;
		while (true){
			Queue<Integer> queue = new LinkedList<Integer>();
			
			for (int i = 0; i < N; i++){
				if (nodes[i].parent != 0) continue;

				queue.add(i);
				result.add(i + 1);
				nodes[i].parent--;
				count--;
			}
			
			if (queue.isEmpty()) return "0";
			
			while (!queue.isEmpty()){
				int me = queue.poll();
				for (int i = 0; i < nodes[me].child.size(); i++){
					nodes[nodes[me].child.get(i)].parent--;
				}
			}
			
			if (count == 0) break;
		}

		StringBuilder sb = new StringBuilder();
		while (true){
			int me = result.poll();
			sb.append(me);

			if (!result.isEmpty()) sb.append("\n");
			else break;
		}

		return sb.toString();
	}

	class Node{
		int parent;
		List<Integer> child = new ArrayList<Integer>();
		Map<Integer, Boolean> childMap = new HashMap<Integer, Boolean>();

		public boolean addChild(int n){
			if (childMap.containsKey(n)) return false;

			childMap.put(n, true);
			child.add(n);

			return true;
		}
	}
}