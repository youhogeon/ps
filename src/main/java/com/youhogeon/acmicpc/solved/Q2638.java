package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Q2638{
	int N, M;
	int[][] arr;
	List<Dot> dots = new ArrayList<Dot>();
	int count = 0;
	final int[] dx = {-1, 1, 0, 0};
	final int[] dy = {0, 0, -1, 1};

	public Q2638() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		arr = new int[N][M];

		for (int i = 0; i < N; i++){
			char[] c = bf.readLine().toCharArray();

			for (int j = 0; j < M; j++){
				arr[i][j] = c[j * 2] == '0'?0:1;

				if (arr[i][j] == 1){
					dots.add(new Dot(i, j));
					count++;
				}
			}
		}
	}

	public void bfs(){
		Queue<Dot> blanks = new LinkedList<Dot>();
		blanks.offer(new Dot(0, 0));
		boolean[][] visited = new boolean[N][M];

		while (!blanks.isEmpty()){
			Dot d = blanks.poll();

			for (int i = 0; i < 4; i++){
				if (d.x + dx[i] < 0 || d.x + dx[i] >= N || d.y + dy[i] < 0 || d.y + dy[i] >= M) continue;

				if (arr[d.x + dx[i]][d.y + dy[i]] == 0){
					if (visited[d.x + dx[i]][d.y + dy[i]]) continue;
					visited[d.x + dx[i]][d.y + dy[i]] = true;

					blanks.offer(new Dot(d.x + dx[i], d.y + dy[i]));
				}else arr[d.x + dx[i]][d.y + dy[i]]++;
			}
		}
	}

	public int solve(){
		int result = 0;
		while (count > 0){
			result++;
			bfs();

			for (int i = 0; i < dots.size(); i++){
				Dot me = dots.get(i);

				if (arr[me.x][me.y] >= 3){
					dots.remove(i);
					arr[me.x][me.y] = 0;
					i--;
					count--;
				}else arr[me.x][me.y] = 1;
			}
		}

		return result;
	}

	class Dot{
		int x, y;

		public Dot(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}