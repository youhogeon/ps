package com.youhogeon.acmicpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Q15666{
	int N, M;
	List<Integer> nums = new ArrayList<Integer>();
	
	public void scan(){
		Scanner sc = new Scanner(System.in);
		HashMap<Integer, Boolean> cache = new HashMap<Integer, Boolean>();

		N = sc.nextInt();
		M = sc.nextInt();

		for (int i = 0; i < N; i++){
			int n = sc.nextInt();
			if (!cache.containsKey(n)) nums.add(n);
			cache.put(n, true);
		}
		Collections.sort(nums);

		sc.close();
	}

	public void solve(){
		combination(nums, new ArrayList<Integer>(), M);
	}

	public void combination(List<Integer> queue, List<Integer> selected, int n){
		if (n == 0){
			int size = selected.size();
			String s = "";
			for (int i = 0; i < size; i++){
				s += Integer.toString(selected.get(i));
				if (i != size - 1) s += " ";
			}
			System.out.println(s);
		}else{
			int size = queue.size();
			for (int i = 0; i < size; i++){
				List<Integer> clone = new ArrayList<Integer>(selected);
				int get = queue.get(i);
				if (clone.isEmpty() || clone.get(clone.size() - 1) <= get){
					clone.add(get);
					combination(queue, clone, n - 1);
				}
			}
		}
	}
}