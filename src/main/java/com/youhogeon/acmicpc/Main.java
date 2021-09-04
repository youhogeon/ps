package com.youhogeon.acmicpc;
import com.youhogeon.unsolved.*;

// class Q14502{
// 	int N, M;

// 	public Q14502() throws IOException{
// 		Scanner sc = new Scanner(System.in);
		

// 		sc.close();
// 	}
// }

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		try{
			Q1202 q = new Q1202();
			
			//q.solve();
			System.out.println(q.solve());
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}