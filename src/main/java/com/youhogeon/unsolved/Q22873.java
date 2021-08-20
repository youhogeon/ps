package com.youhogeon.unsolved;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q22873{
	private int N, Q;
	private short[] A, B, C;
	private List<Dot> sec = new ArrayList<Dot>();

	public void scan() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String[] S = bf.readLine().split(" ");
		N = Integer.parseInt(S[0]);
		Q = Integer.parseInt(S[1]);
		
		char[] A, B;
		A = bf.readLine().toCharArray();
		B = bf.readLine().toCharArray();

		this.A = new short[N+1];
		this.B = new short[N+1];
		this.C = new short[N+1];
		
		for (int i = 0; i < N; i++){
			this.A[i] = (short) (A[N - i - 1] - '0');
			this.B[i] = (short) (B[N - i - 1] - '0');
			
			this.C[i] += this.A[i] + this.B[i];
			if (this.C[i] >= 10){
				this.C[i] -= 10;
				this.C[i + 1] = 1;
			}
		}
		
		int prev_number = this.C[0], count = 0;
		for (int i = 0; i <= N; i++){
			if (this.C[i] == prev_number) count++;
			else{
				sec.add(new Dot(i - count, i - 1, prev_number));
				prev_number = this.C[i];
				count = 1;
			}
		}
		sec.add(new Dot(N - count + 1, N, prev_number));

		

		for (int j = 0; j < Q; j++){
			S = bf.readLine().split(" ");

			int i = Integer.parseInt(S[1]);
			short d = (short)(S[2].charAt(0) - '0');

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
	}

	public void solve(){

	}

	private int calcFrom(int i, short gap){
		if (gap == 0) return 0;

		int k = findIdx(i);
		Dot s = sec.get(k);

		int newSum = s.v + gap;

		if (newSum < 10 && newSum >= 0){
			change(k, i, newSum);
			return 1;
		}else if (newSum >= 10){
			Dot next = sec.get(k + 1);

			if (next.v == 9){
				Dot nextnext = sec.get(k + 2);
				next.v = 0;
				change(k + 2, next.y + 1, nextnext.v + 1);
				change(k, i, newSum - 10);

				return next.y - next.x + 3;
			}else{
				change(k + 1, next.x, next.v + 1);
				change(k, i, newSum - 10);

				return 2;
			}
		}else{
			Dot next = sec.get(k + 1);

			if (next.v == 0){
				Dot nextnext = sec.get(k + 2);
				next.v = 9;
				change(k + 2, next.y + 1, nextnext.v - 1);
				change(k, i, newSum + 10);

				return next.y - next.x + 3;
			}else{
				change(k + 1, next.x, next.v - 1);
				change(k, i, newSum + 10);

				return 2;
			}
		}
	}

	private int findIdx(int i){
		int secLen = sec.size();
		for (int k = 0; k < secLen; k++){
			Dot v = sec.get(k);

			if (v.x <= i && v.y >= i) return k;
		}

		return 0;
	}

	private void merge(int k){
		int secLen = sec.size();
		if (k < secLen - 1){
			Dot v1 = sec.get(k);
			Dot v2 = sec.get(k + 1);

			if (v1.v == v2.v){
				sec.remove(k + 1);
				v1.y = v2.y;
			}
		}

		if (k > 0){
			Dot v1 = sec.get(k - 1);
			Dot v2 = sec.get(k);

			if (v1.v == v2.v){
				sec.remove(k);
				v1.y = v2.y;
			}
		}
	}

	private void change(int k, int i, int value){
		Dot v = sec.get(k);

		if (v.x == v.y){
			v.v = value;
			merge(k);
		}else if (v.x == i){
			sec.add(k, new Dot(i, i, value));
			v.x++;
			merge(k);
		}else if (v.y == i){
			sec.add(k + 1, new Dot(i, i, value));
			v.y--;
			merge(k + 1);
		}else{
			sec.add(k + 1, new Dot(i + 1, v.y, v.v));
			sec.add(k + 1, new Dot(i, i, value));
			v.y = i - 1;
			merge(k - 1);
			merge(k + 1);
		}
	}

	class Dot{
		public int x;
		public int y;
		public int v;

		public Dot(int x, int y, int v){
			this.x = x;
			this.y = y;
			this.v = v;
		}
	}
}