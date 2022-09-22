package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Q2473{
	int N;
	List<Integer> arr = new ArrayList<Integer>();

	public Q2473() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());

		String[] s = bf.readLine().split(" ");
		for (int i = 0; i < N; i++) arr.add(Integer.parseInt(s[i]));
	}

	public String solve(){
		long result = Long.MAX_VALUE;
		int resultIdx1 = 0, resultIdx2 = 0, resultIdx3 = 0;

		arr.sort(Comparator.naturalOrder());

		for (int i = 0; i < N; i++){
			int begin = i + 1;
			int end = N - 1;
			long min = Long.MAX_VALUE;
			int minIdx1 = 0, minIdx2 = 0;

			while (begin < end){
				long sum = (long)arr.get(begin) + arr.get(end) + arr.get(i);
				
				if (Math.abs(sum) < min){
					minIdx1 = begin;
					minIdx2 = end;
					min = Math.abs(sum);
				}
				
				if (sum == 0) break;

				if (sum < 0) begin++;
				else end--;
			}

			if (result > min){
				result = min;
				resultIdx1 = i;
				resultIdx2 = minIdx1;
				resultIdx3 = minIdx2;
			}

			if (result == 0) break;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(arr.get(resultIdx1));
		sb.append(" ");
		sb.append(arr.get(resultIdx2));
		sb.append(" ");
		sb.append(arr.get(resultIdx3));

		return sb.toString();
	}
}