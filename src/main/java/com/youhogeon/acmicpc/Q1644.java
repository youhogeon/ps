package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Q1644{
	int N;

	public Q1644() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
	}

	public int solve(){
		if (N == 1) return 0;
		if (N == 2) return 1;

		List<Integer> primes = getPrime(N);

		int begin = 0;
		int end = 1;
		int sum = 2;
		int size = primes.size();
		int count = 0;

		while (true){
			if (sum <= N){
				if (end < size) sum += primes.get(end);
				else break;
				end++;
			}else if (sum > N){
				if (begin < size) sum -= primes.get(begin);
				else break;
				begin++;
			}

			if (sum == N) count++;
		}

		return count;
	}

	public List<Integer> getPrime(int max){
		List<Integer> primes = new ArrayList<Integer>();

		if (max >= 2) primes.add(2);

		int last = Math.min(max / 2 + 10, max);
		for (int i = 3; i <= last; i+= 2){
			if (isPrime(i)) primes.add(i);
		}

		if (primes.get(primes.size() - 1) != max && isPrime(max)) primes.add(max);

		return primes;
	}

	public boolean isPrime(int n){
		if (n > 2 && n % 2 == 0) return false;

		for (int i = 3; i <= Math.sqrt(n); i+=2){
			if (n % i == 0) return false;
		}

		return true;
	}
}