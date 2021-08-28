package com.youhogeon.acmicpc;
import com.youhogeon.unsolved.*;

// class Q14502{
// 	int N, M;

// 	public Q14502() throws IOException{
// 		Scanner sc = new Scanner(System.in);

		

// 		sc.close();
// 	}
// }

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		try{
			Q15686 q = new Q15686();
			
			//q.solve();
			System.out.println(q.solve());
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}

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

		int[] a = {0,1,2};
		return sum(a);
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