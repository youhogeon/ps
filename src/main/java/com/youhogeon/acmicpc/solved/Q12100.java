package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q12100{
	/**
	 * 총 5번 시행하는 시뮬레이션이기 때문에 4^5 번을 다 수행해도 시간이 오래 걸리지 않는다.
	 * 제한시간이 1초이므로, 1억번 연산 가능하다고 가정하면 각 시뮬레이션 당 10만번 연산 가능한 셈.
	 * 
	 * 다만, java Array class의 clone method가 2차원 배열을 복사하지 못한다는 것을 간과해
	 * 많은 시간을 소비함. 분명 clone method로 복사했는데 원본 배열이 망가져서;;
	 */
	int N;
	int[][] map;

	public Q12100() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++){
			String[] s = bf.readLine().split(" ");
			for (int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
	}

	public int solve(){
		int max = -1;

		for (int a = 0; a < 4; a++){
			for (int b = 0; b < 4; b++){
				for (int c = 0; c < 4; c++){
					for (int d = 0; d < 4; d++){
						for (int e = 0; e < 4; e++){
							int[][] clone = clone();
							clone = move(clone, a);
							clone = move(clone, b);
							clone = move(clone, c);
							clone = move(clone, d);
							clone = move(clone, e);
							int m = findMax(clone);
							max = Math.max(m, max);
						}
					}
				}
			}
		}

		return max;
	}

	public int[][] clone(){
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++) map[i][j] = this.map[i][j];
		}

		return map;
	}

	public int findMax(int[][] map){
		int max = -1;

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++) max = Math.max(map[i][j], max);
		}

		return max;
	}

	public int[][] move(int[][] map, int direction){ //0 상 1 하 2 좌 3 우
		for (int i = 0; i < N; i++){
			int idx = -1;
			for (int j = 0; j < N; j++){				
				if (direction == 0){
					while (++idx < N && map[idx][i] == 0);

					if (idx >= N) map[j][i] = 0;
					else if (j > 0 && map[idx][i] == map[j - 1][i]) map[j-- - 1][i] = map[idx][i] * -2;
					else map[j][i] = map[idx][i];
				}else if (direction == 1){
					while (++idx < N && map[N - 1 - idx][i] == 0);

					if (idx >= N) map[N - 1 - j][i] = 0;
					else if (j > 0 && map[N - 1 - idx][i] == map[N - j][i]) map[N - j--][i] = map[N - 1 - idx][i] * -2;
					else map[N - 1 - j][i] = map[N - 1 - idx][i];
				}else if (direction == 2){
					while (++idx < N && map[i][idx] == 0);

					if (idx >= N) map[i][j] = 0;
					else if (j > 0 && map[i][idx] == map[i][j - 1]) map[i][j-- - 1] = map[i][idx] * -2;
					else map[i][j] = map[i][idx];
				}else{
					while (++idx < N && map[i][N - 1 - idx] == 0);

					if (idx >= N) map[i][N - 1 - j] = 0;
					else if (j > 0 && map[i][N - 1 - idx] == map[i][N - j]) map[i][N - j--] = map[i][N - 1 - idx] * -2;
					else map[i][N - 1 - j] = map[i][N - 1 - idx];
				}
			}
		}

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++) map[i][j] = Math.abs(map[i][j]);
		}

		return map;
	}
}