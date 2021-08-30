package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q17070{
	/***
	 * 처음엔 각 블럭에서 갈 수 있는 모든 칸을 Queue에 넣고
	 * queue가 빌 때 까지 반복하였으나, 이럴 경우 한 노드를 여러번 방문하는 문제가 생김(시간초과)
	 * 따라서 DP를 이용해 문제를 해결함.
	 */
	int N;
	boolean[][] map;
	int[][][] count;

	public Q17070() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		map = new boolean[N][N];
		count = new int[N][N][3];
		
		for (int i = 0; i < N; i++){
			char[] s = bf.readLine().toCharArray();
			for (int j = 0; j < N; j++) map[i][j] = s[j * 2] == '0';
		}
	}

	public int solve(){
		count[0][1][0] = 1;

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				if (!map[i][j]) continue;

				boolean xN1 = i == N - 1;
				boolean yN1 = j == N - 1;
				if (xN1 && yN1) continue;

				for (int k = 0; k < 3; k++){
					int v = count[i][j][k];
					if (v == 0) continue;

					if (!xN1 && !yN1 && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) count[i + 1][j + 1][2] += v;
		
					if (!yN1 && k != 1 && map[i][j + 1]) count[i][j + 1][0] += v;
		
					if (!xN1 && k != 0 && map[i + 1][j]) count[i + 1][j][1] += v;
				}
			}
		}

		return sum(count[N - 1][N - 1]);
	}

	private int sum(int[] arr){
		return arr[0] + arr[1] + arr[2];
	}
}