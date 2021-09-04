package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q1007{
	int T, N;
	int[] x;
	int[] y;
	boolean[] visited;
	int half;

	public Q1007() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(bf.readLine());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0){
			N = Integer.parseInt(bf.readLine());
			
			x = new int[N];
			y = new int[N];
			visited = new boolean[N];
			half = N / 2;

			for (int i = 0; i < N; i++){
				String[] s = bf.readLine().split(" ");

				x[i] = Integer.parseInt(s[0]);
				y[i] = Integer.parseInt(s[1]);
			}


			sb.append(solve());
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	public double solve(){
		return combination(0, 0);
	}

	public double combination(int idx, int count){
		if (count == half) return calc();
		
		double min = 2147000000.0;
		for (int i = idx; i < N; i++){
			visited[i] = true;
			min = Math.min(min, combination(i + 1, count + 1));
			visited[i] = false;
		}

		return min;
	}

	public double calc(){
		int sumX = 0;
		int sumY = 0;

		for (int i = 0; i < N; i++){
			if (visited[i]){
				sumX += x[i];
				sumY += y[i];
			}else{
				sumX -= x[i];
				sumY -= y[i];
			}
		}

		return Math.sqrt(Math.pow(sumX, 2) + Math.pow(sumY, 2));
	}
}