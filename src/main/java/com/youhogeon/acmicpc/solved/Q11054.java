package com.youhogeon.acmicpc.solved;

import java.util.Scanner;

class Q11054{
	int[] A;
	int N;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		A = new int[N];
		for (int i = 0; i < N; i++) A[i] = sc.nextInt();

		sc.close();
	}

	public void solve(){
		int[] rev = new int[N];
		for (int i = 0; i < N; i++) rev[i] = A[N - i - 1];

		int[] arr1 = getAscArr(A);
		int[] arr2 = getAscArr(rev);

		int max = -1;
		for (int i = 0; i < N; i++){
			int v = arr1[i] + arr2[N - i - 1];
			max = Math.max(max, v);
		}

		System.out.println(max - 1);
	}

	public int[] getAscArr(int[] arr){
		int[] result = new int[N];

		for (int i = 0; i < N; i++){
			int max = 1;
			for (int j = 0; j < i; j++){
				if (arr[j] < arr[i]) max = Math.max(max, result[j] + 1);
			}
			result[i] = max;
		}

		return result;
	}
}