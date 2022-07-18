package com.youhogeon;

// class Q14502{
// 	int N, M;

// 	public Q14502() throws IOException {
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

//		String[] str = bf.readLine().split(" ");
//		N = Integer.parseInt(str[0]);
//		M = Integer.parseInt(str[1]);
// 	}

//	public int solve() {
//		return 0;
//	}
// }

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		try{
			Q17143 q = new Q17143();
			
			System.out.println(q.solve());
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}