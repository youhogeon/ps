package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Q22354{
	int N;
	char[] S;
	int count;
	List<Integer> group = new ArrayList<Integer>();

	public void scan() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		S = bf.readLine().toCharArray();
		String x = bf.readLine();

		String[] tmp = x.split(" ");

		char s = 'X';
		int idx = -1;
		for (int i = 0; i < N; i++){
			int input = Integer.parseInt(tmp[i]);
			if (s == S[i]){
				if (group.get(idx) < input) group.set(idx, input);
			}else{
				group.add(input);
				idx++;
			}
			s = S[i];
		}
		count = idx + 1;
	}

	public long solve(){
		long sum = 0;

		group.set(0, -1);
		group.set(count - 1, -1);

		group.sort(Collections.reverseOrder());

		int max = (count - 1) / 2;
		for (int i = 0; i < max; i++) sum += group.get(i);

		return sum;
	}
}