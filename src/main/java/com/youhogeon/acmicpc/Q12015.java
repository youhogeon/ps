package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q12015{
	int N;
	int[] arr;
	int[] list;
	int count = 0;

	public Q12015() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		list = new int[N];

		String[] s = bf.readLine().split(" ");
		for (int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(s[i]);
		}
	}

	public int solve(){
		for (int i = 0; i < N; i++){
			pushList(arr[i]);
		}

		return count;
	}

	public void pushList(int n){
		int left = 0;
		int right = count - 1;

		while (left <= right){
			int mid = (left + right) / 2;

			if (list[mid] == n){
				left = mid;
				break;
			}else if (list[mid] > n) right = mid - 1;
			else left = mid + 1;
		}

		if (list[left] == 0) count++;
		list[left] = n;
	}
}