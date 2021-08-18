package com.youhogeon.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q22873{
	private int N, Q;
	private short[] A, B, C;

	private void scan() throws IOException{
		/*Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		Q = sc.nextInt();

		String A, B;
		A = sc.next();
		B = sc.next();*/

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String[] S = bf.readLine().split(" ");
		N = Integer.parseInt(S[0]);
		Q = Integer.parseInt(S[1]);
		
		String A, B;
		A = bf.readLine();
		B = bf.readLine();

		this.A = new short[N+1];
		this.B = new short[N+1];
		this.C = new short[N+1];
		
		for (int i = 0; i < N; i++){
			this.A[i] = Short.parseShort(String.valueOf(A.charAt(N - i - 1)));
			this.B[i] = Short.parseShort(String.valueOf(B.charAt(N - i - 1)));

			if (this.A[i] + this.B[i] + this.C[i] >= 10) this.C[i + 1] = 1;
		}

		for (int j = 0; j < Q; j++){
			/*String S = sc.next();
			int i = sc.nextInt();
			short d = sc.nextShort();*/
			S = bf.readLine().split(" ");
			int i = Integer.parseInt(S[1]);
			short d = Short.parseShort(S[2]);

			short gap;
			if (S[0].charAt(0) == 'A'){
				gap = (short) (d - this.A[i - 1]);
				this.A[i - 1] = d;
			}else{
				gap = (short) (d - this.B[i - 1]);
				this.B[i - 1] = d;
			}

			System.out.println(calcFrom(i - 1, gap));
		}

		//sc.close();
	}

	/*
	public void calcAll(){
		short upDigit = 0;

		for (int i = 0; i <= N; i++){
			C[i] = (short) (A[i] + B[i] + upDigit);
			upDigit = 0;

			if (C[i] >= 10){
				C[i] -= 10;
				upDigit = 1;
			}
		}
	}*/

	public int calcFrom(int digit, short gap){
		int count = 0;

		int newSum = A[digit] + B[digit] + C[digit];
		int oldSum = newSum - gap;

		while (true){
			if (newSum % 10 == oldSum % 10) return count;
			
			int i = digit + ++count;
			if (i > N) return count;

			oldSum = A[i] + B[i] + C[i];
			C[i] = (short) ((newSum >= 10)?1:0);
			newSum = A[i] + B[i] + C[i];

			/*
			int newUpDigit;
			if (newSum >= 10) newUpDigit = 1;
			else newUpDigit = (newSum > 0)?0:-1;

			if (C[i + 1] == newUpDigit) return count + 1;

			upDigit = (C[i + 1] > newUpDigit)?-1:1;

			newSum = originalSum + upDigit;
			C[i + 1] = (short) ((newSum >= 10)?1:0);
			count++;

			if (C[i] < 0){
				C[i] += 10;
				C[i + 1]--;
			}else if (C[i] >= 10){
				C[i] -= 10;
				C[i + 1]++;
			}else return count;
			count++;*/
		}
	}

	public void solve(){
		try{
			scan();
		}catch(IOException e){ 

		}
	}
}