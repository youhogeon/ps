package com.youhogeon;
import com.youhogeon.acmicpc.*;

// class Q14502{
// 	int N, M;

// 	public Q14502() throws IOException{
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

// 	}

//	public int solve(){

//	}
// }

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		try{
			Q16946 q = new Q16946();
			
			System.out.println(q.solve());
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}

class Q16946{
	int N, M;
	int[][] arr;
	int[] dx = {-1, 0};
	int[] dy = {0, -1};

	public Q16946() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		arr = new int[N][M];

		for (int i = 0; i < N; i++){
			s = bf.readLine().split("");
			for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(s[j]);
		}
	}

	public String solve(){
		int id = 10;
		int[][][] cache = new int[N][M][4];
		//Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] map2 = new int[1000000];
		Queue<Dot> queue = new LinkedList<Dot>();

		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				if (arr[i][j] == 0){
					int max = -1;
					for (int k = 0; k < 2; k++){
						int x = i + dx[k];
						int y = j + dy[k];
	
						if (x < 0 || y < 0 || x >= N || y >= M) continue;
	
						max = Math.max(arr[x][y], max);
					}
	
					if (max >= 10) arr[i][j] = max;
					else arr[i][j] = id++;
					
					map2[arr[i][j]]++;

					for (int k = 0; k < 2; k++){
						int x = i + dx[k];
						int y = j + dy[k];
	
						if (x < 0 || y < 0 || x >= N || y >= M) continue;
	
						for (int q = 0; q < 4; q++){
							if (cache[x][y][q] == 0 || cache[x][y][q] == arr[i][j]){
								cache[x][y][q] = arr[i][j];
								break;
							}
						}
					}
				}else{
					for (int k = 0; k < 2; k++){
						int x = i + dx[k];
						int y = j + dy[k];
	
						if (x < 0 || y < 0 || x >= N || y >= M) continue;
	
						if (arr[x][y] == 1) continue;

						for (int q = 0; q < 4; q++){
							if (cache[i][j][q] == 0 || cache[i][j][q] == arr[x][y]){
								cache[i][j][q] = arr[x][y];
								break;
							}
						}
					}
				}
				
				/*queue.offer(new Dot(i, j));
				while (!queue.isEmpty()){
					Dot me = queue.poll();
					arr[me.x][me.y] = id;
					count++;

					for (int k = 0; k < 4; k++){
						int x = me.x + dx[k];
						int y = me.y + dy[k];
						
						if (x < 0 || y < 0 || x >= N || y >= M) continue;
						if (arr[x][y] == 0) queue.offer(new Dot(x, y));
						else if (arr[x][y] == 1){
							for (int q = 0; q < 4; q++){
								if (cache[x][y][q] == 0 || cache[x][y][q] == id){
									cache[x][y][q] = id;
									break;
								}
							}
						}
					}
				}*/
				
				//map.put(id++, count);
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				if (arr[i][j] >= 10){
					sb.append("0");
					continue;
				}

				int sum = 1;
				for (int k = 0; k < 4; k++){
					if (cache[i][j][k] == 0) break;

					//sum += map.get(cache[i][j][k]);
					sum += map2[cache[i][j][k]];
				}

				sb.append(sum % 10);
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public class Dot{
		int x, y;

		public Dot(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
