package com.youhogeon.acmicpc;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		Q14232 q = new Q14232();
		try{
			q.scan();
		}catch(Exception e){}

		q.solve();
		//System.out.println(q.solve());
	}
}

class Q14232{
	private long K;
	private List<Long> L = new ArrayList<Long>();
	private List<Integer> primes = new ArrayList<Integer>();
	int primes_size = 0;

	public void scan(){
		Scanner sc = new Scanner(System.in);
		K = sc.nextLong();
		sc.close();
	}

	public void solve(){
		//long time = System.currentTimeMillis();
		int sqrt = (int)Math.sqrt(K / 2) + 1;
		for (int i = 2; i <= sqrt; i++){
			while(K % i == 0){
				K /= i;
				L.add((long)i);
			}
		}
		//System.out.println(System.currentTimeMillis() - time);

		//time = System.currentTimeMillis();
		/*for (int i = 0; i < primes_size; i++){
			int div = primes.get(i);
			if (K < div) break;

			while(K % div == 0){
				K /= div;
				L.add((long)div);
			}
		}*/
		//System.out.println(System.currentTimeMillis() - time);
		
		//time = System.currentTimeMillis();
		if (K >= 2) L.add(K);

		int size = L.size();
		System.out.println(size);
		System.out.println(L.stream().map(v->v.toString()).collect(Collectors.joining(" ")));
		//System.out.println(System.currentTimeMillis() - time);
	}
}