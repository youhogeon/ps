package com.youhogeon.acmicpc;

import java.util.*;

class Q22116{
	private int N;
	private int[][] A;
	private int min = 1000000001;
	private int max = -1;

	private int[] dx = {-1, 0, 1, 0};
	private int[] dy = {0, -1, 0, 1};
	
	public void scan(){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();

		A = new int[N][N];
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				A[i][j] = sc.nextInt();

				min = Math.min(min, A[i][j]);
				max = Math.max(max, A[i][j]);
			}
		}

        sc.close();
	}

	public int solve(){
		scan();

		if (N == 1) return 0;

		int left = 0;
		int right = max - min;

		while (left <= right){
			int mid = (left + right) / 2;

			if (can(mid)) right = mid - 1;
			else left = mid + 1;
		}

		return left;
	}

	private boolean can(int n){
		boolean[][] visited = new boolean[N][N];

		Queue<Dot> queue = new LinkedList<Dot>();
		queue.offer(new Dot(0,0));
		visited[0][0] = true;

		Dot current;
		while (!Objects.isNull(current = queue.poll())){
			for (int i = 0; i < 4; i++){
				int nextHeight;
				int currentHeight = A[current.x][current.y];
				int x = current.x + dx[i];
				int y = current.y + dy[i];
				try{
					nextHeight = A[x][y];
				}catch(Exception e){
					continue;
				}

				if (visited[x][y] || Math.abs(nextHeight - currentHeight) > n) continue;

				if (x == N - 1 && y == N - 1) return true;

				queue.add(new Dot(x, y));
				visited[x][y] = true;
			}
		}

		return false;
	}

	class Dot{
		public int x;
		public int y;

		public Dot(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}