package com.youhogeon.acmicpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Q2239{
	int[][] arr = new int[9][9];
	List<int[]> zeros = new ArrayList<int[]>();

	public Q2239() throws IOException{
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 9; i++){
			char[] s = sc.nextLine().toCharArray();

			for (int j = 0; j < 9; j++){
				arr[i][j] = s[j] - '0';

				if (arr[i][j] == 0){
					int[] z = new int[2];
					z[0] = i;
					z[1] = j;
					zeros.add(z);
				}
			}
		}

		sc.close();
	}

	public void solve(){
		calc(0);

		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}

	public boolean calc(int idx){
		if (idx == zeros.size()) return true;

		int[] coor = zeros.get(idx);

		for (int i = 1; i <= 9; i++){
			if (!validate(coor[0], coor[1], i)) continue;

			arr[coor[0]][coor[1]] = i;
			if (calc(idx + 1)) return true;
			arr[coor[0]][coor[1]] = 0;
		}

		return false;
	}

	public boolean validate(int x, int y, int n){
		for (int i = 0; i < 9; i++){
			if (arr[i][y] == n || arr[x][i] == n) return false;
		}

		int startX = (x / 3) * 3;
		int startY = (y / 3) * 3;
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if (arr[startX + i][startY + j] == n) return false;
			}
		}

		return true;
	}
}