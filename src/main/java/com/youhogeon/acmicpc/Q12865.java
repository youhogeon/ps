package com.youhogeon.acmicpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Q12865{
	int N, K;
	int[][] matrix;
	List<Thing> things = new ArrayList<Thing>();

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		matrix = new int[N + 1][K + 1];

		for (int i = 0; i < N; i++){
			int w = sc.nextInt();
			int v = sc.nextInt();
			things.add(new Thing(w, v));
		}

		sc.close();
	}

	public int solve(){
		for (int i = 0; i < N; i++){
			Thing t = things.get(i);

			for (int j = 1; j <= K; j++){
				matrix[i + 1][j] = Math.max(matrix[i][j], (j >= t.w)?(matrix[i][j - t.w] + t.v):-99999);
			}
		}

		int max = -1;
		for (int i = 1; i <= N; i++) max = Math.max(max, matrix[i][K]);

		return max;
	}

	class Thing{
		int w, v;

		public Thing(int w, int v){
			this.w = w;
			this.v = v;
		}
	}
}