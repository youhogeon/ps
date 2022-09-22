package com.youhogeon.acmicpc.solved;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Q11725{
	int N;
	List<List<Integer>> lines = new ArrayList<List<Integer>>();
	int[] parent;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		parent = new int[N];

		for (int i = 0; i < N; i++){
			lines.add(new ArrayList<Integer>());
			parent[i] = -1;
		}

		for (int i = 0; i < N - 1; i++){
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;

			lines.get(a).add(b);
			lines.get(b).add(a);
		}

		sc.close();
	}

	public void solve(){
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);

		while (!queue.isEmpty()){
			int me = queue.poll();
			List<Integer> childs = lines.get(me);

			for (int i = 0; i < childs.size(); i++){
				int child = childs.get(i);
				if (parent[child] == -1){
					queue.offer(child);
					parent[child] = me;
				}
			}
		}

		for (int i = 1; i < N; i++) System.out.println(parent[i] + 1);
	}
}