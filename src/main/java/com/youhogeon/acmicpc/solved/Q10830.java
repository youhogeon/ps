package com.youhogeon.acmicpc.solved;

import java.io.IOException;
import java.util.Scanner;

class Q10830{
	int N;
	long B;
	int[][] A;

	public Q10830() throws IOException{
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		B = sc.nextLong();

		A = new int[N][N];

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++) A[i][j] = sc.nextInt() % 1000;
		}

		sc.close();
	}

	public void solve(){
		A = power(A, B);
		
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				System.out.print(Integer.toString(A[i][j]));
				if (j != N - 1) System.out.print(" ");
			}
			System.out.println();
		}
	}

	public int[][] power(int[][] A, long B){
		if (B == 1) return A;

		int[][] A2 = power(A, B / 2);
		A2 = mul(A2, A2);
		if (B % 2 == 1) A2 = mul(A2, A);

		return A2;
	}

	public int[][] mul(int[][] A, int[][] B){
		int[][] C = new int[N][N];

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				for (int k = 0; k < N; k++){
					C[i][j] += A[i][k] * B[k][j];
					C[i][j] %= 1000;
				}
				C[i][j] %= 1000;
			}
		}

		return C;
	}
}