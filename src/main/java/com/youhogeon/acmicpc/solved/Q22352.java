package com.youhogeon.acmicpc.solved;

import java.util.*;

class Q22352{
	int N, M;
	int[][] matrixA, matrixB;
	Node firstChanged = new Node(-1, -1);

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		matrixA = new int[N][M];
		matrixB = new int[N][M];

		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				matrixA[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				matrixB[i][j] = sc.nextInt();
				if (firstChanged.x < 0 && matrixA[i][j] != matrixB[i][j]) firstChanged = new Node(i, j);
			}
		}
		
		sc.close();
	}

	public String solve(){
		if (firstChanged.x < 0) return "YES";

		Queue<Node> queue = new LinkedList<Node>();
		List<Node> sameArea = new ArrayList<Node>();
		boolean[][] visited = new boolean[N][M];
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};

		queue.offer(firstChanged);
		sameArea.add(firstChanged);
		visited[firstChanged.x][firstChanged.y] = true;
		while (!queue.isEmpty()){
			Node item = queue.poll();

			for (int i = 0; i <= 3; i++){
				int x = item.x + dx[i];
				int y = item.y + dy[i];
				
				try{
					if (visited[x][y]) continue;

					if (matrixA[firstChanged.x][firstChanged.y] == matrixA[x][y]){
						visited[x][y] = true;
						sameArea.add(new Node(x, y));
						queue.offer(new Node(x, y));
					}
				}catch(Exception e){
					continue;
				}
			}
		}
		
		int size = sameArea.size();
		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				boolean itemFound = false;
				
				for (int k = 0; k < size; k++){
					Node v = sameArea.get(k);
					if (v.x == i && v.y == j){
						itemFound = true;
						break;
					}
				}

				if (itemFound && matrixB[firstChanged.x][firstChanged.y] != matrixB[i][j] || !itemFound && matrixA[i][j] != matrixB[i][j]) return "NO";
			}
		}

		return "YES";
	}

	public static class Node{
		public int x, y;

		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}