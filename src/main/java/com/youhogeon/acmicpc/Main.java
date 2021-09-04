package com.youhogeon.acmicpc;
import com.youhogeon.unsolved.*;

// class Q14502{
// 	int N, M;

// 	public Q14502() throws IOException{
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
// 		Scanner sc = new Scanner(System.in);
		


// 		sc.close();
// 	}
// }

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		try{
			Q1202 q = new Q1202();
			
			//q.solve();
			//System.out.println(q.solve());
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}

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

		while (0 < M && !items.isEmpty()){
			Item item = items.poll();
			int idx = findItem(item.w);

			if (idx == -1) continue;

			M--;
			sum += item.v;
			bags.remove(idx);
		}

		return sum;
	}

	public int findItem(int n){
		int left = 0;
		int right = M - 1;
		int mid = 0;
		
		while (left <= right){
			mid = (left + right) / 2;

			if (bags.get(mid) < n) left = mid + 1;
			else right = mid - 1;
		}

		return (bags.get(mid) < n)?-1:mid;
	}

	class Item implements Comparable<Item>{
		int w, v;

		public Item(int w, int v){
			this.w = w;
			this.v = v;
		}

		public int compareTo(Item i){
			if (this.v > i.v) return -1;
			if (this.v < i.v) return 1;

			return 0;
		}
	}
}