package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Q1202{
	int N, M;
	List<Integer> bags = new ArrayList<Integer>();
	PriorityQueue<Item> items = new PriorityQueue<Item>();

	public Q1202() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		for (int i = 0; i < N; i++){
			s = bf.readLine().split(" ");
			items.offer(new Item(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
		}

		for (int i = 0; i < M; i++) bags.add(Integer.parseInt(bf.readLine()));
	}

	public long solve(){
		long sum = 0;

		bags.sort(Comparator.naturalOrder());
		PriorityQueue<Item2> availableItems = new PriorityQueue<Item2>();

		for (int i = 0; i < M; i++){
			while (!items.isEmpty()){
				Item item = items.peek();

				if (item.w > bags.get(i)) break;

				items.poll();
				availableItems.offer(new Item2(item.w, item.v));
			}

			if (availableItems.isEmpty()) continue;
			Item2 item2 = availableItems.poll();
			sum += item2.v;
		}

		return sum;
	}

	class Item implements Comparable<Item>{
		int w, v;

		public Item(int w, int v){
			this.w = w;
			this.v = v;
		}

		public int compareTo(Item i){ //가벼운순
			if (this.w > i.w) return 1;
			if (this.w < i.w) return -1;

			return 0;
		}
	}

	class Item2 implements Comparable<Item2>{
		int w, v;

		public Item2(int w, int v){
			this.w = w;
			this.v = v;
		}

		public int compareTo(Item2 i){ //비싼순
			if (this.v > i.v) return -1;
			if (this.v < i.v) return 1;

			return 0;
		}
	}
}