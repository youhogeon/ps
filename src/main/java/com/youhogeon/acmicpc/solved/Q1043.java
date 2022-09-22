package com.youhogeon.acmicpc.solved;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Q1043{
	int N, M;
	boolean[] truth;
	List<List<Integer>> parties = new ArrayList<List<Integer>>();
	Queue<Integer> banQueue = new LinkedList<Integer>();

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		truth = new boolean[N + 1];

		int i = sc.nextInt();
		while (i-- > 0){
			int id = sc.nextInt();
			truth[id] = true;
			banQueue.offer(id);
		}

		while (M-- > 0){
			i = sc.nextInt();
			List<Integer> users = new ArrayList<Integer>();
			while (i-- > 0){
				users.add(sc.nextInt());
			}
			parties.add(users);
		}

		sc.close();
	}

	public void solve(){
		while(!banQueue.isEmpty()){
			banUser(banQueue.poll());
		}

		System.out.println(parties.size());
	}
	
	public void banUser(int id){
		int size = parties.size();

		for (int i = 0; i < size; i++){
			List<Integer> party = parties.get(i);
			if (!party.contains(id)) continue;

			parties.remove(i);
			i--;
			size--;

			int innerSize = party.size();
			for (int j = 0; j < innerSize; j++){
				if (truth[party.get(j)]) continue;

				truth[party.get(j)] = true;
				banQueue.offer(party.get(j));
			}
		}
	}
}