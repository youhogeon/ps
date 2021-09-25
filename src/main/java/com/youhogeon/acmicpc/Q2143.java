package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Q2143{
	int N, M, T;
	int[] A, B;

	public Q2143() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(bf.readLine());
		
		N = Integer.parseInt(bf.readLine());
		A = new int[N];
		String[] s = bf.readLine().split(" ");
		for (int i = 0; i < N; i++) A[i] = Integer.parseInt(s[i]);
		
		M = Integer.parseInt(bf.readLine());
		B = new int[M];
		s = bf.readLine().split(" ");
		for (int i = 0; i < M; i++) B[i] = Integer.parseInt(s[i]);
	}

	public long solve(){
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();

		for (int i = 0; i < N; i++){
			for (int j = i; j < N; j++) addMap(map1, sum(A, i, j));
		}

		for (int i = 0; i < M; i++){
			for (int j = i; j < M; j++) addMap(map2, sum(B, i, j));
		}

		long sum = 0;
		for (Integer k : map1.keySet()){
			int goal = T - k;
			if (!map2.containsKey(goal)) continue;

			sum += (long)map1.get(k) * (long)map2.get(goal);
		}

		return sum;
	}

	public void addMap(Map<Integer, Integer> m, int key){
		if (!m.containsKey(key)) m.put(key, 1);
		else m.put(key, m.get(key) + 1);
	}

	public int sum(int[] arr, int i, int j){
		int sum = 0;

		for (int k = i; k <= j; k++) sum += arr[k];

		return sum;
	}
}