package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Q9328{
	int N, M;
	char[][] map;
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, -1, 1};
	StringBuilder sb = new StringBuilder();

	public Q9328() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());


		while (T-- > 0){
			String[] s = bf.readLine().split(" ");

			N = Integer.parseInt(s[0]);
			M = Integer.parseInt(s[1]);

			map = new char[N][M];

			for (int i = 0; i < N; i++) map[i] = bf.readLine().toCharArray();

			char[] keys = bf.readLine().toCharArray();
			if (keys[0] != '0'){
				int size = keys.length;
				for (int i = 0; i < size; i++) foundKey(keys[i]);
			}

			sb.append(solve2());
			sb.append("\n");
		}
	}

	public String solve(){
		return sb.toString();
	}

	public int solve2(){
		Queue<Node> queue = new LinkedList<Node>();
		boolean[][] visited = new boolean[N][M];

		int foundMoney = findEntrance(queue);

		while (!queue.isEmpty()){
			Node me = queue.poll();

			for (int i = 0; i < 4; i++){
				int x = me.x + dx[i], y = me.y + dy[i];
				if (x < 0 || y < 0 || x >= N || y >= M || visited[x][y]) continue;

				char v = map[x][y];
				visited[x][y] = true;

				if (v == '$'){
					map[x][y] = '.';
					v = '.';
					foundMoney++;
				}

				if (v == '.') queue.offer(new Node(x, y));
				else if (v >= 'a' && v <= 'z'){
					foundKey(v);
					findEntrance(queue);
					visited = new boolean[N][M];
				}
			}
		}

		return foundMoney;
	}

	public void foundKey(char x){
		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				if (map[i][j] == x || map[i][j] == x - 32) map[i][j] = '.';
			}
		}
	}

	public int findEntrance(Queue<Node> queue){
		queue.clear();
		int foundMoney = 0;

		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				if (i * j * (N - i - 1) * (M - j - 1) != 0) continue;

				if (map[i][j] == '$'){
					foundMoney++;
					map[i][j] = '.';
				}

				if (map[i][j] == '.') queue.offer(new Node(i, j));
				else if (map[i][j] >= 'a' && map[i][j] <= 'z'){
					foundKey(map[i][j]);
					queue.clear();
					i = 0;
					j = 0;
				}
			}
		}

		return foundMoney;
	}

	class Node{
		public int x, y;

		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}