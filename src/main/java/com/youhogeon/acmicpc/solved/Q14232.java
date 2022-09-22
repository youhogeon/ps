package com.youhogeon.acmicpc.solved;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Q14232{
	private long K;
	private List<Long> L = new ArrayList<Long>();

	public void scan(){
		Scanner sc = new Scanner(System.in);
		K = sc.nextLong();
		sc.close();
	}

	public void solve(){
		int sqrt = (int)Math.sqrt(K / 2) + 1;
		for (int i = 2; i <= sqrt; i++){
			while(K % i == 0){
				K /= i;
				L.add((long)i);
			}
		}

		if (K >= 2) L.add(K);

		int size = L.size();
		System.out.println(size);
		System.out.println(L.stream().map(v->v.toString()).collect(Collectors.joining(" ")));
	}
}