package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Q2108{
	int N, sum, min = 0x7FFFFFFF, max = 0x80000000;
	int[] nums;
	List<Obj> count = new ArrayList<Obj>();

	public Q2108() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		nums = new int[N];

		for (int i = 0; i < 8001; i++) {
			count.add(new Obj(i - 4000));
		}

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(bf.readLine());
			sum += nums[i];
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
			count.get(nums[i] + 4000).j++;
		}
	}

	public int solve(){
		Arrays.sort(nums);
		Collections.sort(count);

		System.out.println(Math.round((double)sum / (double)N));
		System.out.println(nums[N / 2]);

		if (count.get(0).j == count.get(1).j) System.out.println(count.get(1).i);
		else System.out.println(count.get(0).i);

		return max - min;
	}

	class Obj implements Comparable<Obj> {
		int i, j;

		public Obj(int i) {
			this.i = i;
			this.j = 0;
		}

		@Override
		public int compareTo(Obj o) {
			if (this.j < o.j) return 1;
			else if (this.j > o.j) return -1;

			if (this.i > o.i) return 1;
			else if (this.i < o.i) return -1;

			return 0;
		}
	}
}