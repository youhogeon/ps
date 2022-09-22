package com.youhogeon.acmicpc.solved;

import java.util.Scanner;

class Q9251{
	char[] A, B;
	int lenA, lenB;
	int[][] matrix;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		A = sc.nextLine().toCharArray();
		B = sc.nextLine().toCharArray();
		lenA = A.length;
		lenB = B.length;
		matrix = new int[lenA + 1][lenB + 1];

		sc.close();
	}

	public int solve(){
		for (int i = 1; i <= lenA; i++){
			for (int j = 1; j <= lenB; j++){
				if (A[i - 1] == B[j - 1]) matrix[i][j] = matrix[i - 1][j - 1] + 1;
				else matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
			}
		}

		return matrix[lenA][lenB];
	}
}