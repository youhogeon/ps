package com.youhogeon.acmicpc.solved;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Q15654{
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
		List<Integer> seq = new ArrayList<Integer>();

		for (int i = 0; i < N; i++) seq.add(i);

		makeP("", seq, M);
	}

	private void makeP(String arr, List<Integer> left, int len){
		if (len == 1){
			for (int i = 0; i < left.size(); i++){
				System.out.println(arr + num.get(left.get(i)).toString());
			}
		}else{
			for (int i = 0; i < left.size(); i++){
				List<Integer> cloneLeft = new ArrayList<Integer>(left);
				int q = num.get(cloneLeft.get(i));
				cloneLeft.remove(i);
				makeP(arr + Integer.toString(q) + " ", cloneLeft, len - 1);
			}	
		}
	}
}