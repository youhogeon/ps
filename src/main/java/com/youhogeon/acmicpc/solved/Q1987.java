package com.youhogeon.acmicpc.solved;

import java.io.IOException;
import java.util.Scanner;

class Q1987{
	int N, M;
	int[][] arr;
	int[] dx = {0, 0, 1, -1};
	int[] dy = {1, -1, 0, 0};

	public Q1987() throws IOException{
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];

		sc.nextLine();

		for (int i = 0; i < N; i++){
			char[] s = sc.nextLine().toCharArray();
			for (int j = 0; j < M; j++){
				arr[i][j] = s[j] - 'A';
			}
		}

		sc.close();
	}

	public int solve(){
		boolean[] visited = new boolean[26];
		visited[arr[0][0]] = true;

		return calc(0, 0, 1, visited);
	}

	public int calc(int x, int y, int count, boolean[] visited){
		int max = -1;
		
		for (int i = 0; i < 4; i++){
			int newX = x + dx[i];
			int newY = y + dy[i];

			if (newX < 0 || newX >= N || newY < 0 || newY >= M || visited[arr[newX][newY]]) continue;

			visited[arr[newX][newY]] = true;
			max = Math.max(max, calc(newX, newY, count + 1, visited));
			visited[arr[newX][newY]] = false;
		}

		return max == -1?count:max;
	}
}