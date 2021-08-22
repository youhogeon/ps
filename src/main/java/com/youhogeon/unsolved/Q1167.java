package com.youhogeon.unsolved;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Q1167{
	int N;
	boolean[] isLast;
	List<List<Line>> nodes = new ArrayList<List<Line>>();

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		isLast = new boolean[N];

		for (int i = 0; i < N; i++){
			nodes.add(new ArrayList<Line>());
		}

		while (N-- > 0){
			int id = sc.nextInt() - 1;

			int input;
			while ((input = sc.nextInt()) > 0){
				nodes.get(id).add(new Line(id, input - 1, sc.nextInt()));
			}

			if (nodes.get(id).size() == 1) isLast[id] = true;
		}

		sc.close();
	}
	
	public int solve(){
		System.out.println(findFar(0, 0, 0));
		//System.out.println(findCost(0, 1, 0));
		//System.out.println(findCost(1, 4, 0));
		return 1;
	}

	private int findFar(int from, int id, int defaultCost){
		List<Line> line = nodes.get(id);
		Queue<Line> queue = new LinkedList<Line>();
		
		int max = -1;
		int maxId = 0;

		for (int i = 0; i < line.size(); i++){
			Line l = line.get(i);
			if (l.to == from) continue;

			int cost = defaultCost + l.cost;
			if (max < cost){
				max = cost;
				maxId = i;
			}
		}

		return -99999;
	}

	class Line{
		public int to, cost, from;

		public Line(int from, int to, int cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}