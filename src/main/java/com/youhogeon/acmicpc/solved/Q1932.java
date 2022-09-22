package com.youhogeon.acmicpc.solved;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Q1932{
	int N;
	List<List<Integer>> arr = new ArrayList<List<Integer>>();;
	List<List<Integer>> result = new ArrayList<List<Integer>>();;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		for (int i = 0; i < N; i++){
			arr.add(new ArrayList<Integer>());
			result.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < N; i++){
			List<Integer> l = arr.get(i);
			List<Integer> r = result.get(i);

			for (int j = 0; j <= i; j++){
				l.add(sc.nextInt());
				r.add(arr.get(0).get(0));
			}
		}

		sc.close();
	}

	public void solve(){
		for (int i = 0; i < N - 1; i++){
			for (int j = 0; j <= i; j++){
				result.get(i + 1).set(j, Math.max(result.get(i + 1).get(j), arr.get(i + 1).get(j) + result.get(i).get(j)));
				result.get(i + 1).set(j + 1, Math.max(result.get(i + 1).get(j + 1), arr.get(i + 1).get(j + 1) + result.get(i).get(j)));
			}
		}

		List<Integer> lastArr = result.get(N - 1);
		int max = -1;
		for (int i = 0; i < lastArr.size(); i++){
			max = Math.max(max, lastArr.get(i));
		}

		System.out.println(max);
	}

	//getMax(0, 0) 호출해 구할 수 있으나, 시간초과
	public int getMax(int h, int i){
		if (h == N - 1) return arr.get(h).get(i);

		return arr.get(h).get(i) + Math.max(getMax(h + 1, i), getMax(h + 1, i + 1));
	}
}