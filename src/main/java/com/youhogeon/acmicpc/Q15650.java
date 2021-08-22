package com.youhogeon.acmicpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Q15650{
	private int N, M;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		sc.close();
	}

	public void solve(){
		makeP(new ArrayList<Integer>(), 1, N, M);
	}

	private void makeP(List<Integer> arr, int from, int to, int len){
		if (len == 1){
			for (int i = from; i <= to; i++){
				for (int j = 0; j < arr.size(); j++){
					System.out.print(arr.get(j));
					System.out.print(" ");
				}
				System.out.print(i);
				System.out.println("");
			}
		}else{
			for (int i = from; i <= to; i++){
				List<Integer> clone = new ArrayList<Integer>(arr);
				clone.add(i);
				if (to - i >= len - 1) makeP(clone, i + 1, to, len - 1);
			}	
		}
	}
}