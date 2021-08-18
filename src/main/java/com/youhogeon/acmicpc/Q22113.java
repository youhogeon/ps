package com.youhogeon.acmicpc;

import java.util.Scanner;

public class Q22113{
	
	private int N, M;
	private int list[];
	private int price[][];
    
	public void scan(){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();

		list = new int[M];
		price = new int[N][N];

		for (int i = 0; i < M; i++) list[i] = sc.nextInt() - 1;

		for (int j = 0; j < N; j++){
			for (int i = 0; i < N; i++){
				price[j][i] = sc.nextInt();
			}
		}

        sc.close();
	}

    public int solve(){
        scan();

        int sum = 0;

		for (int i = 0; i < M - 1; i++){
			sum += price[list[i]][list[i+1]];
		}

		return sum;
    }
}
