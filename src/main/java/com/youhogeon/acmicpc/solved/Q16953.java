package com.youhogeon.acmicpc.solved;

import java.util.Scanner;

class Q16953{
	int A, B;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		A = sc.nextInt();
		B = sc.nextInt();

		sc.close();
	}

	public int solve(){
		int count = 1;

		while (true){
			if (B < A) return -1;
			if (B == A) return count;

			if (B % 10 == 1) B = B / 10;
			else if (B % 2 == 1) return -1;
			else B = B / 2;
			count++;
		}
	}
}