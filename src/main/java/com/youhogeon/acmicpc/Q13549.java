package com.youhogeon.acmicpc;

import java.util.PriorityQueue;
import java.util.Scanner;

class Q13549{
	int N, K;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		sc.close();
	}

	public int solve(){
		boolean[] visited = new boolean[2 * K + 1];
		PriorityQueue<Set> queue = new PriorityQueue<Set>();

		int cost = 0;
		if (N == 0){
			cost++;
			N++;
		}

		if (N - cost >= K) return N - cost - K;

		queue.offer(new Set(N, cost));

		while (!queue.isEmpty()){
			Set s = queue.poll();
			int n = s.x;

			while (n < K * 2){
				if (visited[n]) break;
				if (n == K) return s.cost;
				
				visited[n] = true;
				if (!visited[n + 1]) queue.offer(new Set(n + 1, s.cost + 1));
				if (n >= 1 && !visited[n - 1]) queue.offer(new Set(n - 1, s.cost + 1));

				n *= 2;
			}
		}

		return -1;
	}

	class Set implements Comparable<Set>{
		int x, cost;

		public Set(int x, int cost){
			this.x = x;
			this.cost = cost;
		}

		public int compareTo(Set s){
			if (cost > s.cost) return 1;
			if (cost < s.cost) return -1;

			if (x > s.x) return -1;
			if (x < s.x) return 1;

			return 0;
		}
	}
}