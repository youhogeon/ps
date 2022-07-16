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
			Q20040 q = new Q20040();
			
			System.out.println(q.solve());
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}

class Q20040{
	int N, M;
	int[] head;
	int result = 0;

	public Q20040() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		head = new int[N];

		for (int i = 0; i < N; i++) {
			head[i] = i;
		}

		for (int i = 0; i < M; i++) {
			str = bf.readLine().split(" ");

			int a = Integer.parseInt(str[0]), b = Integer.parseInt(str[1]);

			if (i % 2 == 0) {
				union1(a, b);
				if (find1(a) == find1(b) && result == 0) result = i + 1;
			} else {
				union2(a, b);
				if (find2(a) == find2(b) && result == 0) result = i + 1;
			}
		}
	}

	public int find(int a) {
		while (a != head[a]) a = head[a]; 
		
		return a;
	}

	public void union(int a, int b) {
		head[find(a)] = find(b);
	}

	public int solve(){
		return result;
	}
}