package com.youhogeon.acmicpc.solved;

import java.io.IOException;
import java.util.Scanner;

class Q17387{
	long[][] dots = new long[4][2];

	public Q17387() throws IOException{
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 2; j++) dots[i][j] = sc.nextLong();
		}

		sc.close();
	}

	public int solve(){
		int abc = CCW(dots[0], dots[1], dots[2]);
		int abd = CCW(dots[0], dots[1], dots[3]);
		int cda = CCW(dots[2], dots[3], dots[0]);
		int cdb = CCW(dots[2], dots[3], dots[1]);

		if (abc * abd == 0 && cda * cdb == 0){
			if (Math.min(dots[0][0], dots[1][0]) <= Math.max(dots[2][0], dots[3][0]) && Math.min(dots[2][0], dots[3][0]) <= Math.max(dots[0][0], dots[1][0]) && Math.min(dots[0][1], dots[1][1]) <= Math.max(dots[2][1], dots[3][1]) && Math.min(dots[2][1], dots[3][1]) <= Math.max(dots[0][1], dots[1][1])) return 1;
		}else if (abc * abd <= 0 && cda * cdb <= 0) return 1;

		return 0;
	}

	public int CCW(long[] A, long[] B, long[] C){
		long v = A[0] * B[1] + B[0] * C[1] + C[0] * A[1] - A[1] * B[0] - B[1] * C[0] - C[1] * A[0];

		if (v > 0) return 1;
		if (v < 0) return -1;
		return 0;
	}
}