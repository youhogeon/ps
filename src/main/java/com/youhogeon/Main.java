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
			Q9527 q = new Q9527();
			
			System.out.println(q.solve());
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}

class Q9527{
	long N, M;

	public Q9527() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Long.parseLong(bf.readLine());
		M = Long.parseLong(bf.readLine());
	}

	public long solve(){
		
	}
}