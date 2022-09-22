package com.youhogeon.acmicpc.solved;

import java.util.Scanner;

class Q1629{
	int A, B, C;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();

		A = A % C;

		sc.close();
	}

	public void solve(){
		long result = power(A, B);

		System.out.println(result);
	}

	public long power(int a, int n){
		if (n == 1) return a;

		long result = power(a, n / 2) % C;
		result = (result * result) % C;
		if (n % 2 == 1) result = (result * a) % C;

		return result;
	}
}