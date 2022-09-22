package com.youhogeon.acmicpc;

import java.util.*;
import java.io.*;

class Q2741{
	int N;

	public Q2741() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());

	}
	
	public void solve() {
		for (int i = 1; i <= N; i++) System.out.println(i);
	}
}

class Q2753{
	int N;

	public Q2753() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
	}

	public int solve() {
		return (N % 4 == 0 && (N % 400 == 0 || N % 100 > 0)) ? 1 : 0;
	}
}

class Q2739{
	int N;

	public Q2739() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
	}

	public String solve() {
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= 9; i++) {
			sb.append(N + " * " + i + " = " + (N * i) + "\n");
		}

		return sb.toString();
	}
}

class Q11720{
	int N, M;

	public Q11720() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());

		char[] nums = bf.readLine().toCharArray();

		while (N-- > 0) M += (nums[N] - '0');
	}

	public int solve() {
		return M;
	}
}

class Q10998{
	int N, M;

	public Q10998() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
	}

	public int solve() {
		return N * M;
	}
}

class Q2577{
	int N, M, O;

	public Q2577() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());
		O = Integer.parseInt(bf.readLine());
	}

	public String solve() {
		char[] chars = Integer.toString(N * M * O).toCharArray();
		int[] count = new int[10];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < chars.length; i++) count[chars[i] - '0']++;

		for (int i = 0; i < 10; i++) sb.append(count[i] + "\n");

		return sb.toString();
	}
}

class Q2562{
	int N, M;

	public Q2562() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = -1;
		for (int i = 0; i < 9; i++) {
			int scan = Integer.parseInt(bf.readLine());

			if (scan > N) {
				N = scan;
				M = i + 1;
			}
		}
	}

	public int solve() {
		System.out.println(N);
		return M;
	}
}

class Q2475{
	int N;

	public Q2475() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");

		for (int i = 0; i < 5; i++) N += Integer.parseInt(str[i]) * Integer.parseInt(str[i]);
	}

	public int solve() {
		return N % 10;
	}
}

class Q2439{
	int N;

	public Q2439() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
	}

	public String solve() {
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < N - i; j++) {
				sb.append(" ");
			}
			for (int j = 0; j < i; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}

class Q2438{
	int N;

	public Q2438() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
	}

	public String solve() {
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}

class Q1330{
	int N, M;

	public Q1330() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
	}

	public String solve() {
		if (N < M) return "<";
		if (N > M) return ">";
		return "==";
	}
}

class Q1157{
	char[] chars;

	public Q1157() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		chars = bf.readLine().toUpperCase().toCharArray();
	}

	public char solve() {
		int[] count = new int[91];

		for (int i = 0; i < chars.length; i++) count[(int)chars[i]]++;

		int max = -1, maxChar = 0;
		for (int i = 'A'; i <= 'Z'; i++) {
			if (max <= count[i]) {
				if (max == count[i] && maxChar != 0) maxChar = '?';
				else maxChar = i;

				max = count[i];
			}
		}

		return (char)maxChar;
	}
}

class Q1152{
	int N;

	public Q1152() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().trim().split(" ");
		N = str[0].length() == 0 ? 0 : str.length;
	}

	public int solve() {
		return N;
	}
}

class Q1008{
	int N, M;

	public Q1008() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] str = bf.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
	}

	public double solve() {
		return (double)N / (double)M;
	}
}

class Q1546{
	short max = -1;
	int sum = 0;
	short N;

	public Q1546() throws IOException{
		Scanner sc = new Scanner(System.in);

		N = sc.nextShort();

		for (int i = 0; i < N; i++){
			short s = sc.nextShort();
			sum += s;
			if (max < s) max = s;
		}

		double avg = 0.0;

		if (sum > 0) avg = (double)sum / N / max * 100;
		System.out.println(avg);

		sc.close();
	}
}