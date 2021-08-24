package com.youhogeon.acmicpc;
import com.youhogeon.unsolved.*;

// class Q14502{
// 	int N, M;

// 	public Q14502() throws Exception{
// 		Scanner sc = new Scanner(System.in);

		

// 		sc.close();
// 	}
// }

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		try{
			Q2448 q = new Q2448();
			
			q.solve();
			//System.out.println(q.solve());
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}

class Q2448{
	int N;
	char[][] arr;

	public Q2448() throws Exception{
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		sc.close();
	}

	public void solve(){
		int K = (int)(Math.log(N / 3) / Math.log(2));
		int size = (K + 1) * 5 + (int)Math.pow(2, K) - 1;
		arr = new char[size][size];

		makeTri(K);

		System.out.println(size);
	}

	public char[][] makeTri(int K){
		int size = (K + 1) * 5 + (int)Math.pow(2, K) - 1;
		char[][] arr = new char[size][size];

		if (K == 0){
			char[0][0] = ' ';
		}
	}
}