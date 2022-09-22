package com.youhogeon.acmicpc.solved;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Q12851{
	int N, K;

	public Q12851(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		sc.close();
	}

	public void solve(){
		int[] visited = new int[2 * K + 1];
		Queue<Set> queue = new LinkedList<Set>();

		int cost = 0;
		if (N == 0){
			cost++;
			N++;
		}

		if (N - cost >= K){
			System.out.println(N - cost - K);
			System.out.println(1);
			return;
		}

		queue.offer(new Set(N, cost));

		int count = 0;
		int finalCost = 0x7FFFFFFF;

		while (!queue.isEmpty()){
			Set s = queue.poll();
			int n = s.x;

			if (s.cost > finalCost) break;
			if (visited[n] > 0 && visited[n] < s.cost) continue;

			if (n == K){
				count++;
				finalCost = s.cost;
				continue;
			}

			if (s.cost == finalCost) continue;

			visited[n] = s.cost;

			if (visited[n + 1] <= s.cost + 1) queue.offer(new Set(n + 1, s.cost + 1));
			if (2 * n >= 1 && n < K && visited[n * 2] <= s.cost + 1) queue.offer(new Set(2 * n, s.cost + 1));
			if (n >= 1 && visited[n - 1] <= s.cost + 1) queue.offer(new Set(n - 1, s.cost + 1));
		}

		System.out.println(finalCost);
		System.out.println(count);
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