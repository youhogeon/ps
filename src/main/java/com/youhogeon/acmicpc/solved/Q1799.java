package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Q1799{
	int N;
	int[][] map;
	int[][] org;
	int[] dx = {-1, -1, 1, 1};
	int[] dy = {-1, 1, 1, -1};

	public Q1799() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		org = new int[N][N];

		for (int i = 0; i < N; i++){
			String[] s = bf.readLine().split(" ");
			for (int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(s[j]);
				org[i][j] = map[i][j];
			}
		}
	}

	public int solve(){
		return calc(0, 0, 0) + calc(0, 1, 1);
	}

	public int calc(int x, int y, int div){
		int max = 0;
		int count = 0;

		for (int i = x; i < N; i++){
			for (int j = 0; j < N; j++){
				if (map[i][j] == 0) continue;
				if (i == x && j < y) continue;
				if ((i + j) % 2 != div) continue;

				List<Integer> lists = putBishop(i, j);
				if (lists == null) continue;

				int nextj = (j + 1) % N;
				int nexti = i + ((nextj < j)?1:0);
				int add = calc(nexti, nextj, div) + 1;
				count += add;

				if (count > max) max = count;

				deleteBishop(i, j, lists);
				count -= add;
			}
		}

		return max;
	}

	public List<Integer> checkBishop(int x, int y){
		List<Integer> lists = new ArrayList<Integer>();

		for (int i = 1; i < N; i++){
			for (int j = 0; j < 4; j++){
				int x2 = dx[j] * i + x;
				int y2 = dy[j] * i + y;

				if (x2 < 0 || y2 < 0 || x2 >= N || y2 >= N) continue;

				if (map[x2][y2] == 2) return null;
				if (map[x2][y2] == 1) lists.add(x2 * 100 + y2);
			}
		}

		return lists;
	}

	public List<Integer> putBishop(int x, int y){
		List<Integer> lists = checkBishop(x, y);
		if (checkBishop(x, y) == null) return null;	

		for (int i = 0; i < lists.size(); i++) map[lists.get(i) / 100][lists.get(i) % 100] = 0;

		map[x][y] = 2;
		return lists;
	}

	public void deleteBishop(int x, int y, List<Integer> lists){
		if (map[x][y] != 2) return;

		map[x][y] = 1;
		for (int i = 0; i < lists.size(); i++) map[lists.get(i) / 100][lists.get(i) % 100] = 1;
	}
}