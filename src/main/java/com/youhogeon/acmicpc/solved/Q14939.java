package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q14939{
	boolean[][] map;

	public Q14939() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		map = new boolean[10][10];

		for (int i = 0; i < 10; i++) {
			char[] line = bf.readLine().toCharArray();

			for (int j = 0; j < 10; j++) map[i][j] = line[j] == 'O';
		}
	}

	public int solve() {
		int min = 0x7FFFFFFF;

		for (int i = 0; i <= 1023; i++) {
			boolean[][] clone = new boolean[10][10];

			for (int k = 0; k < 10; k++) {
				for (int j = 0; j < 10; j++) clone[k][j] = map[k][j];
			}

			int count = 0;
			for (int j = 0; j < 10; j++) {
				if (((i >> j) & 1) == 1) {
					check(clone, 0, j);
					count++;
				}
			}

			min = Math.min(min, calc(clone, count));
		}

		return min > 100 ? -1 : min;
	}

	public int calc(boolean[][] map, int count) {
		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (!map[i - 1][j]) continue;

				count++;
				check(map, i, j);
			}
		}

		for (int i = 0; i < 10; i++) {
			if (map[9][i]) count = 0x7FFFFFFF;
		}

		return count;
	}

	public void check(boolean[][] map, int x, int y) {
		int[] dx = {0, 0, 0, 1, -1}, dy = {0, 1, -1, 0, 0};

		for (int i = 0; i < 5; i++) {
			int xx = x + dx[i], yy = y + dy[i];

			if (xx < 0 || yy < 0 || xx >= 10 || yy >= 10) continue;

			map[xx][yy] = !map[xx][yy];
		}
	}
}