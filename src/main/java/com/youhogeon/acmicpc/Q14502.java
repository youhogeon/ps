package com.youhogeon.acmicpc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Q14502{
	final int[] dx = {0, 0, 1, -1};
	final int[] dy = {1, -1, 0, 0};

	int N, M;
	int[][] matrix;
	Queue<Integer> virusQueue = new LinkedList<Integer>();
	int count0 = 0;

	public Q14502() throws Exception{
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		matrix = new int[N][M];

		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				matrix[i][j] = sc.nextInt();

				if (matrix[i][j] == 2) virusQueue.offer(i * M + j);
				else if (matrix[i][j] == 0) count0++;
			}
		}

		sc.close();
	}

	public int solve(){
		int max = -1;

		int mul = N * M;
		for (int i = 0; i < mul; i++){
			for (int j = i + 1; j < mul; j++){
				for (int k = j + 1; k < mul; k++){
					if (matrix[i / M][i % M] != 0 || matrix[j / M][j % M] != 0 || matrix[k / M][k % M] != 0) continue;

					max = Math.max(max, calc(i, k, j));
				}
			}
		}

		return max;
	}

	public int calc(int wall1, int wall2, int wall3){
		Queue<Integer> virusQueue = new LinkedList<Integer>(this.virusQueue);

		boolean[][] visited = new boolean[N][M];

		int count = 0;
		while (!virusQueue.isEmpty()){
			int virus = virusQueue.poll();

			for (int i = 0; i < 4; i++){
				int newX = dx[i] + (int)(virus / M);
				int newY = dy[i] + virus % M;

				if (newX < 0 || newX >= N || newY < 0 || newY >= M || visited[newX][newY] || matrix[newX][newY] != 0 || newX == (int)(wall1 / M) && newY == wall1 % M || newX == (int)(wall2 / M) && newY == wall2 % M || newX == (int)(wall3 / M) && newY == wall3 % M) continue;

				visited[newX][newY] = true;
				virusQueue.offer(newX * M + newY);

				count++;
			}
		}

		return count0 - count - 3;
	}
}