package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q17143{
	int R, C, M;
	Shark[] sharks;
	Shark[][] map;

	public Q17143() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		M = Integer.parseInt(str[2]);

		sharks = new Shark[M];
		map = new Shark[C][R];

		for (int i = 0; i < M; i++) {
			str = bf.readLine().split(" ");
			sharks[i] = new Shark(Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]));
			map[sharks[i].c][sharks[i].r] = sharks[i];
		}
	}

	public int solve() {
		int sum = 0;

 		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				if (map[i][j] == null) continue;

				sum += map[i][j].z;
				map[i][j].die();
				break;
			}
			//사냥

			map = new Shark[C][R];
			for (int j = 0; j < M; j++) {
				if (sharks[j].r < 0) continue;

				sharks[j].move();

				Shark prev = map[sharks[j].c][sharks[j].r];

				if (prev == null) map[sharks[j].c][sharks[j].r] = sharks[j];
				else if (prev.z < sharks[j].z) {
					prev.die();
					map[sharks[j].c][sharks[j].r] = sharks[j];
				} else {
					sharks[j].die();
				}
			}
			// 이동

			//print();
		}

		return sum;
	}

	class Shark {
		int r, c, s, dr, dc, z; //row, column, speed, direction, size
		int phase = 0;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.z = z;

			if (d == 1) dr = -1;
			else if (d == 2) dr = 1;
			else if (d == 3) dc = 1;
			else dc = -1;
		}

		public void die() {
			this.r = -1;
			this.c = -1;
			this.s = 0;
		}

		public void move() {
			int newR = r + s * dr, newC = c + s * dc;

			if (dr != 0) {
				//dr = ((newR / (R - 1)) % 2 == 0) ? 1 : -1;
				if (newR == 0 || R == 1) dr = 1;
				else dr *= (( (newR / (R - 1)) % 2 == 0 ) ? 1 : -1) * (newR / Math.abs(newR));
				r = R - 1 - Math.abs(Math.abs(newR % (2 * R - 2)) - R + 1);
			}

			if (dc != 0){
				//dc = ((newC / (C - 1)) % 2 == 0) ? 1 : -1;
				if (newC == 0 || C == 1) dc = 1;
				else dc *= (( (newC / (C - 1)) % 2 == 0 ) ? 1 : -1) * (newC / Math.abs(newC));
				c = C - 1 - Math.abs(Math.abs(newC % (2 * C - 2)) - C + 1);
			}
		}
	}
}