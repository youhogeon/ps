package com.youhogeon.acmicpc.solved;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Q2206{
	private short N, M;
	private boolean[][] map;
	final int MAX = 2147000000;
	final short[] dx = {-1, 0, 0, 1};
	final short[] dy = {0, -1, 1, 0};

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextShort();
		M = sc.nextShort();
		sc.nextLine();

		map = new boolean[N][M];

		for (short i = 0; i < N; i++){
			char[] s = sc.nextLine().toCharArray();
			for (short j = 0; j < M; j++){
				map[i][j] = (s[j] == '0')?true:false;
			}
		}

		sc.close();
	}

	public int solve(){
		return findMap();
	}

	private int findMap(){
		boolean[][][] visited = new boolean[2][N][M];
		Queue<Node> q = new LinkedList<Node>();

		int min = MAX;
		q.offer(new Node((short)0, (short)0, 0));

		while (!q.isEmpty()){
			Node n = q.poll();

			if (n.x == N - 1 && n.y == M - 1){
				min = Math.min(min, n.cost + 1);
				continue;
			}

			for (int i = 0; i < 4; i++){
				if (dx[i] + n.x < 0 || dx[i] + n.x >= N || dy[i] + n.y < 0 || dy[i] + n.y >= M || (n.usingChance && !map[n.x + dx[i]][n.y + dy[i]])) continue;

				if (visited[n.usingChance?1:0][n.x + dx[i]][n.y + dy[i]]) continue;
				visited[n.usingChance?1:0][n.x + dx[i]][n.y + dy[i]] = true;

				Node newNode = new Node((short)(n.x + dx[i]), (short)(n.y + dy[i]), n.cost + 1);
				newNode.usingChance = n.usingChance;
				if (!map[n.x + dx[i]][n.y + dy[i]]) newNode.usingChance = true;

				q.offer(newNode);
			}
		}

		return min==MAX?-1:min;
	}

	class Node{
		public short x, y;
		public int cost;
		public boolean usingChance;

		public Node(short x, short y, int cost){
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
}