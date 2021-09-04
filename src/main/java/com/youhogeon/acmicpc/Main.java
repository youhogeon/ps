package com.youhogeon.acmicpc;

// class Q14502{
// 	int N, M;

// 	public Q14502() throws IOException{
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
// 		Scanner sc = new Scanner(System.in);
		


// 		sc.close();
// 	}
// }

import java.util.*;
import java.io.*;
//import com.youhogeon.unsolved.*;

public class Main {
	public static void main(String[] args) {
		try{
			Q2467 q = new Q2467();
			
			System.out.println(q.solve());
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}