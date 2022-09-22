package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q9466{
	int T, N;
	int[] arr;

	public Q9466() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(bf.readLine());

		while (T-- > 0){
			N = Integer.parseInt(bf.readLine());
			arr = new int[N];

			String[] s = bf.readLine().split(" ");
			for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(s[i]) - 1;

			System.out.println(solve());
		}
	}

	public int solve(){
		boolean[] isCycle = new boolean[N];
		boolean[] visited = new boolean[N];
		int soloCount = 0;

		for (int i = 0; i < N; i++){
			if (isCycle[i]) continue;

			int x = i;
			while (true){
				x = arr[x];

				if (isCycle[x] || visited[x] && !isCycle[x]){ //사이클에 포함되지않음
					int rollback = x;

					x = i;
					while (true){
						if (x == rollback) break;
						isCycle[x] = false;
						x = arr[x];
					}

					break;
				}

				isCycle[x] = true;
				visited[x] = true;
				if (x == i){ //사이클에포함됨
					break;
				}
			}
		}

		for (int i = 0; i < N; i++){
			if (!isCycle[i]) soloCount++;
		}

		return soloCount;
	}
}