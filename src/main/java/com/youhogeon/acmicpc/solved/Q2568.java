package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Q2568{
	int N;
	List<Pair> list = new ArrayList<Pair>();
	List<Integer> LIS = new ArrayList<Integer>(), seq = new ArrayList<Integer>();

	public Q2568() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());

		for (int i = 0; i < N; i++) {
			String[] str = bf.readLine().split(" ");
			list.add(new Pair(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
		}
	}

	public void solve() {
		Collections.sort(list);

		for (int i = 0; i < N; i++) {
			if (i == 0 || LIS.get(LIS.size() - 1) < list.get(i).b ) {
				seq.add(LIS.size());
				LIS.add(list.get(i).b);
			} else {
				int search = binarySearch(list.get(i).b);
				seq.add(search);
				LIS.set(search, list.get(i).b);
			}
		}

		List<Integer> result = new ArrayList<Integer>();
		int target = LIS.size() - 1;
		for (int i = N - 1; i >= 0; i--) {
			if (seq.get(i) == target) target--;
			else result.add(i);
		}

		System.out.println(result.size());
		for (int i = result.size() - 1; i >= 0; i--) {
			System.out.println(list.get(result.get(i)).a);
		}
	}

	public int binarySearch(int bound) {
		int begin = 0, end = LIS.size() - 1;

		while (end >= begin) {
			int mid =  (begin + end) / 2;

			if (LIS.get(mid) == bound) return mid;

			if (LIS.get(mid) <= bound) begin = mid + 1;
			else end = mid - 1;
		}

		return begin;
	}

	class Pair implements Comparable<Pair> {
		int a, b;

		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.a > o.a) return 1;
			if (this.a < o.a) return -1;

			return 0;
		}
	}
}