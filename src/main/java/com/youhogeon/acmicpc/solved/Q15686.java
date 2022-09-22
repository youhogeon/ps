package com.youhogeon.acmicpc.solved;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Q15686{
	int N, M;
	List<Dot> house = new ArrayList<Dot>();
	List<Dot> chicken = new ArrayList<Dot>();
	int[][] distance;

	public Q15686() throws IOException{
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				int a = sc.nextInt();
				
				if (a == 1) house.add(new Dot(i, j));
				else if (a == 2) chicken.add(new Dot(i, j));
			}
		}

		sc.close();
	}
	
	public int solve(){
		distance = new int[house.size()][chicken.size()];

		for (int i = 0; i < house.size(); i++){
			Dot h = house.get(i);

			for (int j = 0; j < chicken.size(); j++){
				Dot c = chicken.get(j);

				distance[i][j] = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
			}
		}

		List<int[]> comb = combination(0, chicken.size() - 1, M);
		int min = 99999999;
		for (int i = 0; i < comb.size(); i++){
			min = Math.min(min, sum(comb.get(i)));
		}

		return min;
	}

	public List<int[]> combination(int from, int to, int pick){
		List<int[]> result = new ArrayList<int[]>();

		if (pick == 1){
			for (int i = from; i <= to; i++){
				int[] a = new int[1];
				a[0] = i;
				result.add(a);
			}

			return result;
		}

		for (int i = from; i <= to - pick + 1; i++){
			result.addAll(combHelper(combination(i + 1, to, pick - 1), i));
		}

		return result;
	}

	public List<int[]> combHelper(List<int[]> arr, int add){
		int size = arr.get(0).length;
		for (int i = 0; i < arr.size(); i++){
			int[] newArr = new int[size + 1];

			for (int j = 0; j < size; j++) newArr[j] = arr.get(i)[j];
			newArr[size] = add;

			arr.set(i, newArr);
		}

		return arr;
	}

	public int sum(int[] survived){
		int sum = 0;

		for (int i = 0; i < house.size(); i++){
			int min = 99999999;

			int size = survived.length;
			for (int j = 0; j < size; j++){
				min = Math.min(min, distance[i][survived[j]]);
			}

			sum += min;
		}

		return sum;
	}

	class Dot{
		int x, y;

		public Dot(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}