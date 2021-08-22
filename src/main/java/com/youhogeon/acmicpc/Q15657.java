package com.youhogeon.acmicpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Q15657{
	private int N, M;
	private List<Integer> num = new ArrayList<Integer>();

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		for (int i = 0; i < N; i++){
			num.add(sc.nextInt());
		}

		Collections.sort(num);

		sc.close();
	}

	public void solve(){
		makeP(new ArrayList<Integer>(), 1, N, M);
	}

	private void makeP(List<Integer> arr, int from, int to, int len){
		if (len == 1){
			for (int i = from; i <= to; i++){
				for (int j = 0; j < arr.size(); j++){
					System.out.print(num.get(arr.get(j) - 1));
					System.out.print(" ");
				}
				System.out.print(num.get(i - 1));
				System.out.println("");
			}
		}else{
			for (int i = from; i <= to; i++){
				List<Integer> clone = new ArrayList<Integer>(arr);
				clone.add(i);
				makeP(clone, i, to, len - 1);
			}	
		}
	}
}