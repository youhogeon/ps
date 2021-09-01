package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Q2252{
	/**
	 * 위상정렬 개념 이용하는 문제.
	 * https://terms.naver.com/entry.naver?docId=3579618&cid=59086&categoryId=59093
	 */
	int N, M;
	List<List<Integer>> list = new ArrayList<List<Integer>>();
	int[] count;

	public Q2252() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		count = new int[N];

		for (int i = 0; i < N; i++) list.add(new ArrayList<Integer>());

		for (int i = 0; i < M; i++){
			s = bf.readLine().split(" ");
			int a = Integer.parseInt(s[0]) - 1;
			int b = Integer.parseInt(s[1]) - 1;
			list.get(a).add(b);
			count[b]++;
		}
	}

	public void solve(){
		List<Integer> result = new ArrayList<Integer>();

		while (result.size() != N){
			for (int i = 0; i < N; i++){
				if (count[i] != 0) continue;

				result.add(i + 1);
				count[i] = -1;
				
				List<Integer> sub = list.get(i);
				for (int j = 0; j < sub.size(); j++) count[sub.get(j)]--;
			}
		}

		for (int i = 0; i < N; i++){
			System.out.print(result.get(i));
			if (i != N - 1) System.out.print(" ");
		}
	}
}