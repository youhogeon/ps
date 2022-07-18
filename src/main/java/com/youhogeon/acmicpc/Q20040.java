package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Q20040{
	int N, M;
	int[] head;
	int result = 0;

	public Q20040() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		head = new int[N];

		for (int i = 0; i < N; i++) {
			head[i] = i;
		}

		for (int i = 0; i < M; i++) {
			str = bf.readLine().split(" ");

			int a = Integer.parseInt(str[0]), b = Integer.parseInt(str[1]);

			if (find(a) == find(b) && result == 0) result = i + 1;
			union(a, b);
		}
	}

	public int find(int a) {
		Queue<Integer> queue = new LinkedList<Integer>();

		while (a != head[a]) {
			queue.add(a);
			a = head[a];
		}

		while (!queue.isEmpty()) head[queue.poll()] = a;
		
		return a;
	}

	public void union(int a, int b) {
		head[find(a)] = find(b);
	}

	public int solve(){
		return result;
	}
}