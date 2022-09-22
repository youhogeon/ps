package com.youhogeon.acmicpc.solved;

import java.util.Scanner;

class Q11444{
	long N;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextLong();

		sc.close();
	}

	public void solve(){
		Matrix m = power(new Matrix(), N);

		System.out.println(m.b);
	}

	public Matrix power(Matrix m, long n){
		if (n == 1) return m;

		Matrix result = power(m, n / 2);
		result = multiply(result, result);
		if (n % 2 == 1) result = multiply(result, new Matrix());

		return result;
	}

	private Matrix multiply(Matrix a, Matrix b){
		return new Matrix(a.a * b.a + a.b * b.c, a.a * b.b + a.b * b.d, a.c * b.a + a.d * b.c, a.c * b.b + a.d * b.d);
	}

	class Matrix{
		long a = 1, b = 1, c = 1, d = 0;
		long x = 1000000007;

		public Matrix(){

		}

		public Matrix(long a, long b, long c, long d){
			this.a = a % x;
			this.b = b % x;
			this.c = c % x;
			this.d = d % x;
		}
	}
}