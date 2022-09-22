package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Q13460{
	int N, M;
	int[][] map; //0 빈칸 / 1 벽 / 2 홀
	Dot red;
	Dot blue;
	int[] dx = {-1, 0, 1, 0}; //상좌하우
	int[] dy = {0, -1, 0, 1};

	public Q13460() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		map = new int[N][M];

		for (int i = 0; i < N; i++){
			char[] c = bf.readLine().toCharArray();

			for (int j = 0; j < M; j++){
				int res = 0;
				if (c[j] == '#') res = 1;
				else if (c[j] == 'O') res = 2;
				else if (c[j] == 'R') red = new Dot(i, j);
				else if (c[j] == 'B') blue = new Dot(i, j);

				map[i][j] = res;
			}
		}
	}

	public int solve(){
		Queue<State> queue = new LinkedList<State>();
		queue.offer(new State(1, red, blue));

		while (!queue.isEmpty()){
			State me = queue.poll();
			if (me.count > 10) break;

			for (int i = 0; i < 4; i++){
				Dot newRed = new Dot(me.red.x, me.red.y);
				Dot newBlue = new Dot(me.blue.x, me.blue.y);

				boolean redSuccess = false;
				boolean blueSuccess = false;

				while(!redSuccess){
					newRed.x += dx[i];
					newRed.y += dy[i];
					if (newRed.x < 0 || newRed.x >= N || newRed.y < 0 || newRed.y >= M) break;

					int v = map[newRed.x][newRed.y];

					if (v == 1 || newRed.x == newBlue.x && newRed.y == newBlue.y){
						newRed.x -= dx[i];
						newRed.y -= dy[i];
						break;
					}

					if (v == 2){
						redSuccess = true;
						newRed.x = -1;
						newRed.y = -1;
						break;
					}
				}

				while(!blueSuccess){
					newBlue.x += dx[i];
					newBlue.y += dy[i];
					if (newBlue.x < 0 || newBlue.x >= N || newBlue.y < 0 || newBlue.y >= M) break;

					int v = map[newBlue.x][newBlue.y];

					if (v == 1 || newRed.x == newBlue.x && newRed.y == newBlue.y){
						newBlue.x -= dx[i];
						newBlue.y -= dy[i];
						break;
					}

					if (v == 2){
						blueSuccess = true;
						newBlue.x = -1;
						newBlue.y = -1;
						break;
					}
				}

				while(!redSuccess){
					newRed.x += dx[i];
					newRed.y += dy[i];
					if (newRed.x < 0 || newRed.x >= N || newRed.y < 0 || newRed.y >= M) break;

					int v = map[newRed.x][newRed.y];

					if (v == 1 || newRed.x == newBlue.x && newRed.y == newBlue.y){
						newRed.x -= dx[i];
						newRed.y -= dy[i];
						break;
					}

					if (v == 2){
						redSuccess = true;
						newRed.x = -1;
						newRed.y = -1;
						break;
					}
				}

				if (redSuccess && !blueSuccess) return me.count;
				else if (!blueSuccess) queue.offer(new State(me.count + 1, new Dot(newRed.x, newRed.y), new Dot(newBlue.x, newBlue.y)));
			}
		}

		return -1;
	}

	class State{
		int count;
		Dot red, blue;

		public State(int count, Dot red, Dot blue){
			this.count = count;
			this.red = red;
			this.blue = blue;
		}
	}

	class Dot{
		int x, y;

		public Dot(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}