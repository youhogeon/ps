package com.youhogeon.acmicpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Q22353{
	int a, d, k;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		a = sc.nextInt();
		d = sc.nextInt();
		k = sc.nextInt();

		sc.close();
	}

	public Double solve(){
		double sum = 0.0;
		double mul = (double)d / 100;
		List<Double> prob = new ArrayList<Double>();

		while (true){
			double p = (1 - sum) * mul;
			prob.add(p);

			if (mul >= 1.0) break;

			sum += p;
			mul *= 1 + (double)k / 100;
			mul = Math.min(1.0, mul);
		}

		double result = 0.0;
		int size = prob.size();
		for (int i = 0; i < size; i++){
			result += a * prob.get(i) * (i + 1);
		}

		return result;
	}
}