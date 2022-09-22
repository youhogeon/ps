package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Q22351{
	private String S;

	private void scan(){
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			setS(bf.readLine());
		}catch(Exception e){}
	}

	public void setS(String S){
		this.S = S;
	}

	public String solve(){
		String tmp;

		scan();

		for (int i = 1; i <= 999; i++){
			tmp = "";

			for (int j = i; j <= 999; j++){
				tmp += Integer.toString(j);
				if (S.equals(tmp)) return Integer.toString(i) + " " + Integer.toString(j);
			}
		}

		return "";
	}
}