package com.youhogeon.acmicpc;

import java.util.Scanner;

class Q1546{
	short max = -1;
	int sum = 0;
	short N;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextShort();

		for (int i = 0; i < N; i++){
			short s = sc.nextShort();
			sum += s;
			if (max < s) max = s;
		}

		double avg = 0.0;

		if (sum > 0) avg = (double)sum / N / max * 100;
		System.out.println(avg);

		sc.close();
	}
}