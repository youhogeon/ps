package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Q16946{
	int N, M;
	int[][] map;
	int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};

	public Q16946() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			char[] line = bf.readLine().toCharArray();
			for (int j = 0; j < M; j++) map[i][j] = line[j] - '0';
		}
	}

	public String solve(){
		int id = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (marking(i, j, id)) id++;
			}
		}
		//빈 공간에 ID 부여

		int[] count = new int[id];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				count[map[i][j]]++;
			}
		}
		//갯수 측정

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 1) {
					sb.append("0");
					continue;
				}
				
				Set<Integer> sets = new HashSet<Integer>();
				
				for (int k = 0; k < 4; k++) {
					int x = i + dx[k], y = j + dy[k];
					if (x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 1) continue;

					sets.add(map[x][y]);
				}
				
				int sum = 1;
				for (int k : sets) {
					sum += count[k];
				}

				sb.append(sum % 10);
			}
			sb.append("\n");
		}
		//출력

		return sb.toString();
	}

	public boolean marking(int x, int y, int id) {
		if (x < 0 || y < 0 || x >= N || y >= M || map[x][y] != 0) return false;

		map[x][y] = id;

		for (int i = 0; i < 4; i++) marking(x + dx[i], y + dy[i], id);

		return true;
	}
}