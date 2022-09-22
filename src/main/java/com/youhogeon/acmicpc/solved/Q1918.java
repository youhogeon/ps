package com.youhogeon.acmicpc.solved;

import java.util.Scanner;
import java.util.Stack;

class Q1918{
	char[] eq;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		eq = sc.nextLine().toCharArray();

		sc.close();
	}

	public void solve(){
		Stack<Character> stack = new Stack<Character>();

		int len = eq.length;
		String result = "";
		for (int i = 0; i < len; i++){
			// if (eq[i] == '(') continue;

			// if (eq[i] == ')'){
			// 	if (stack.isEmpty()) continue;
			// 	result += stack.pop();
			// }else if (eq[i] == '+' || eq[i] == '-' || eq[i] == '*' || eq[i] == '/') stack.push(eq[i]);
			// else result += eq[i];

			if (eq[i] == '(') stack.push('(');
			else if (eq[i] == ')'){
				while (!stack.isEmpty() && stack.peek() != '(') result += stack.pop();
				stack.pop();
			}else if (eq[i] == '*' || eq[i] == '/' || eq[i] == '+' || eq[i] == '-'){
				while (!stack.isEmpty() && priority(stack.peek()) >= priority(eq[i])) result += stack.pop();
				stack.push(eq[i]);
			}else result += eq[i];
		}

		while (!stack.isEmpty()) result += stack.pop();

		System.out.println(result);
	}

	public int priority(char x){
		switch (x){
			case '*':
			case '/':
				return 2;
			case '+':
			case '-':
				return 1;
		}
		
		return 0;
	}
}