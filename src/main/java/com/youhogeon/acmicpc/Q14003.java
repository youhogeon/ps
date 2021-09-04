package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Q14003{
	int N;
	int[] arr;
	int[] list;
	Stack<Integer> log = new Stack<Integer>();
	int count = 0;

	public Q14003() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		list = new int[N];

		String[] s = bf.readLine().split(" ");
		for (int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(s[i]);
		}
	}

	public void solve(){
		for (int i = 0; i < N; i++) pushList(arr[i]);

		System.out.println(count);

		int last = count - 1;
		Stack<Integer> print = new Stack<Integer>();
		while (last >= 0){
			int v = log.pop();

			if (v != last) continue;
			
			last = v - 1;

			print.push(arr[log.size()]);
		}

		StringBuilder sb = new StringBuilder();
		while(!print.isEmpty()){
			sb.append(print.pop());
			if (print.size() != 0) sb.append(" ");
		}

		System.out.println(sb.toString());
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
		log.push(left);
	}
}