package com.youhogeon.acmicpc;

import java.util.*;

class Q22114 implements Q{
	private int N, K;
	private int[] list;
	private int[] arr1;
	private List<Integer> arr2 = new Vector<Integer>();
	private List<Integer> arr3 = new Vector<Integer>();

	public void scan(){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();

		list = new int[N-1];
		arr1 = new int[N-1];

		for (int i = 0; i < N - 1; i++){
			list[i] = sc.nextInt();
			arr1[i] = (list[i] > K)?1:0;
		}

        sc.close();
	}

	public int solve(){
		scan();

		int combo = 0;
		for (int i = 0; i < N - 1; i++){
			if (arr1[i] == 0) combo++;
			
			if (combo > 0 && (arr1[i] != 0 || i == N - 2)){
				arr2.add(combo);
				combo = 0;
			}

			if (arr1[i] != 0) arr2.add(0);
		}
		
		int size = arr2.size();
		if (size == 2) return arr2.get(0) + arr2.get(1) + 2;
		else if (size == 1) return Math.max(2, arr2.get(0) + 1);

		for (int i = 0; i < size - 2; i++){
			arr3.add(arr2.get(i) + arr2.get(i + 1) + arr2.get(i + 2));
		}

		return Collections.max(arr3) + 2;
	}
}