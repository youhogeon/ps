package com.youhogeon.acmicpc;

import java.util.*;

class Q22116 implements Q{
	private int N;
	private int[][] A;

	public void scan(){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();

		A = new int[N][N];
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				A[i][j] = sc.nextInt();
			}
		}

        sc.close();
	}

	public int solve(){
		scan();

		return 1;
	}
}