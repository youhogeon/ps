package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Q1208{
	int N, M;
	int[] arr;

	public Q1208() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		arr = new int[N];

		s = bf.readLine().split(" ");
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(s[i]); 
	}

	public long solve(){
		int idx = (N - 1) / 2;
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();

		int x = (int)(Math.pow(2, idx + 1)) - 1;
		while (x >= 0) addMap(map1, sum(0, idx, x--));

		x = (int)(Math.pow(2, N - idx - 1)) - 1;
		while (x >= 0) addMap(map2, sum(idx + 1, N - 1, x--));

		long sum = 0;

		for (int i = -2000000; i <= 2000000; i++){
			int j = -1 * i + M;
			if (!map1.containsKey(i) || !map2.containsKey(j)) continue;

			sum += (long)map1.get(i) * (long)map2.get(j);
		}

		if (M == 0) sum--;

		return sum;
	}

	public void addMap(Map<Integer, Integer> m, int key){
		if (!m.containsKey(key)) m.put(key, 1);
		else m.put(key, m.get(key) + 1);
	}

	public int sum(int begin, int end, int x){
		int s = 0;

		for (int i = 0; i <= end - begin; i++){
			if ((x >> i & 1) == 1) s += arr[begin + i];
		}

		return s;
	}
}