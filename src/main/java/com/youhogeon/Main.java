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
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, -1, 1};

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

	public int solve(){
		int id = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(-1, 0);

		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				if (arr[i][j] > 0) continue;

				Queue<Dot> queue = new LinkedList<Dot>();
				queue.offer(new Dot(i, j));

				int count = 0;
				
				while (!queue.isEmpty()){
					Dot me = queue.poll();
					arr[me.x][me.y] = 10 + id;
					count++;

					for (int k = 0; k < 4; k++){
						int x = me.x + dx[k];
						int y = me.y + dy[k];
						
						if (x < 0 || y < 0 || x >= N || y >= M) continue;
						if (arr[x][y] != 0) continue;
						
						queue.offer(new Dot(x, y));
					}
				}
				
				id++;
				map.put(id, count);
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				if (arr[i][j] >= 10){
					sb.append("0");
					continue;
				}

				int[] cache = {-1, -1, -1, -1};

				loop:
				for (int k = 0; k < 4; k++){
					int x = i + dx[k];
					int y = j + dy[k];
					
					if (x < 0 || y < 0 || x >= N || y >= M) continue;
					if (arr[x][y] == 1) continue;

					for (int q = 0; q < 4; q++){
						if (cache[q] == arr[x][y] - 10) continue loop;
					}

					cache[k] = arr[x][y] - 10;
				}

				sb.append( (map.get(cache[0]) + map.get(cache[1]) + map.get(cache[2]) + map.get(cache[3])) % 10 );
			}
			sb.append("\n");
		}

		return 1;
	}

	public class Dot{
		int x, y;

		public Dot(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
