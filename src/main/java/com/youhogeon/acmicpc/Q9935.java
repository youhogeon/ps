package com.youhogeon.acmicpc;

import java.util.Scanner;
import java.util.Stack;

class Q9935{
	char[] str;
	char[] tnt;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		str = sc.nextLine().toCharArray();
		tnt = sc.nextLine().toCharArray();

		sc.close();
	}

	public void solve(){
		Stack<Character> printStack = new Stack<Character>();
		Stack<Character> strStack = new Stack<Character>();

		for (int i = str.length - 1; i >= 0; i--) strStack.push(str[i]);
		
		int tntLength = tnt.length;
		int ptr = 0;
		while (!strStack.isEmpty()){
			char s = strStack.pop();
			printStack.push(s);

			if (s == tnt[ptr]) ptr++;
			else if (s == tnt[0]) ptr = 1;
			else ptr = 0;

			if (ptr == tntLength){
				for (int j = 0; j < tntLength; j++){
					if (!printStack.isEmpty()) printStack.pop();
				}
				for (int j = 0; j < tntLength - 1; j++){
					if (!printStack.isEmpty()) strStack.push(printStack.pop());
				}
				ptr = 0;
			}
		}

		if (printStack.isEmpty()) System.out.println("FRULA");
		else{
			int size = printStack.size();
			char[] test = new char[size];

			for (int i = size - 1; i >= 0; i--) test[i] = printStack.pop();

			System.out.println(String.valueOf(test));
		}
	}
}