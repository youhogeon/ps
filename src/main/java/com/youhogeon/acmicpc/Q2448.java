package com.youhogeon.acmicpc;

import java.io.IOException;
import java.util.Scanner;

class Q2448{
	int N;
	char[][] arr;
	int center;

	public Q2448() throws IOException{
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		sc.close();
	}

	public void solve(){
		int K = (int)(Math.log(N / 3) / Math.log(2));

		int size = calcSize(K);
		int sizeY = calcSizeY(K);
		arr = new char[sizeY][size];
		for (int i = 0; i < sizeY; i++){
			for (int j = 0; j < size; j++) arr[i][j] = ' ';
		}

		center = size / 2;
		arr[0][center] = '*';
		arr[1][center - 1] = '*';
		arr[1][center + 1] = '*';
		arr[2][center - 2] = '*';
		arr[2][center - 1] = '*';
		arr[2][center] = '*';
		arr[2][center + 1] = '*';
		arr[2][center + 2] = '*';

		makeTri(K);

		StringBuilder a = new StringBuilder();
		for (int i = 0; i < N; i++){
			a.append(new String(arr[i]));
			a.append("\n");
		}

		System.out.println(a);
	}

	private int calcSize(int K){
		return 6 * (int)Math.pow(2, K) - 1;
	}

	private int calcSizeY(int K){
		return 3 * (int)Math.pow(2, K);
	}

	public void makeTri(int K){
		if (K == 0) return;

		makeTri(K - 1);

		int smallSize = calcSize(K - 1);
		int smallSizeY = calcSizeY(K - 1);
		int start = center - smallSize / 2;

		for (int i = 0; i < smallSizeY; i++){
			for (int j = 0; j < smallSize; j++){
				arr[i + smallSizeY][j + center - smallSize] = arr[i][start + j];
				arr[i + smallSizeY][j + center + 1] = arr[i][start + j];
			}
		}
	}
}