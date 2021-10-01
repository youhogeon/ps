package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q10775{
	int G, P;
	int[] arr;

	public Q10775() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		G = Integer.parseInt(bf.readLine());
		P = Integer.parseInt(bf.readLine());

		arr = new int[P];
		for (int i = 0; i < P; i++) arr[i] = Integer.parseInt(bf.readLine()) - 1;
	}

	public int solve(){
		int count = 0;
		int[] check = new int[G];

		for (int i = 0; i < P; i++){
			int backup = count;

			for (int j = arr[i]; j >= 0; j--){
				if (check[j] > 5){
					j = check[j] - 10;
					continue;
				}

				check[j] = 10 + j;
				check[arr[i]] = 10 + j;
				count++;
				break;
			}

			if (backup == count) return count;
		}

		return count;
	}
}