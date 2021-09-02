package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Q9252{
	char[] A, B;
	int lenA, lenB;
	int[][] arr;

	public Q9252() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		A = bf.readLine().toCharArray();
		B = bf.readLine().toCharArray();
		arr = new int[lenA = A.length][lenB = B.length];
	}

	public void solve(){
		for (int i = 0; i < lenA; i++){
			for (int j = 0; j < lenB; j++){
				int leftTop = (i != 0 && j != 0)?arr[i - 1][j - 1]:0;
				int top = (i != 0)?arr[i - 1][j]:0;
				int left = (j != 0)?arr[i][j - 1]:0;

				if (A[i] == B[j]) arr[i][j] = leftTop + 1;
				else arr[i][j] = Math.max(top, left);
			}
		}

		List<Character> list = new ArrayList<Character>();


		int i = lenA - 1;
		int j = lenB - 1;
		int result = arr[lenA -  1][lenB - 1];

		while (true){
			if (j < 0 || i < 0 || j >= lenB || i >= lenA) break;

			if (A[i] == B[j]){
				list.add(B[j]);

				if (arr[i--][j--] == 1) break;
			}else if (j > 0 && arr[i][j] == arr[i][j - 1]){
				j--;
			}else i--;
		}

		System.out.println(result);
		if (result == 0) return;

		for (i = list.size() - 1; i >= 0; i--) System.out.print(list.get(i));
	}
}