package com.youhogeon.acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Q2263{
	int n;
	int[] inOrder;
	int[] postOrder;
	Node root;

	public void scan() throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(bf.readLine());

		inOrder = new int[n];
		postOrder = new int[n];

		String[] s1 = bf.readLine().split(" ");
		String[] s2 = bf.readLine().split(" ");
		for (int i = 0; i < n; i++){
			inOrder[i] = Integer.parseInt(s1[i]);
			postOrder[i] = Integer.parseInt(s2[i]);
		}
	}

	public void solve(){
		root = makeTree(inOrder, postOrder, n, 0, 0);
		printTree(root, root.v);
	}

	public void printTree(Node root, int v){
		if (v != root.v) System.out.print(" ");
		System.out.print(root.v);
		if (root.left != null) printTree(root.left, v);
		if (root.right != null) printTree(root.right, v);
	}

	public Node makeTree(int[] inOrder, int[] postOrder, int len, int inIdx, int postIdx){
		if (len == 0) return null;

		int r = postOrder[postIdx + len - 1];
		if (len == 1) return new Node(r);

		int rIdx = inIdx;
		for (; rIdx < len + inIdx; rIdx++){
			if (inOrder[rIdx] == r) break;
		}

		Node root = new Node(r);

		root.left = makeTree(inOrder, postOrder, rIdx - inIdx, inIdx, postIdx);
		root.right = makeTree(inOrder, postOrder, len - rIdx + inIdx - 1, rIdx + 1, postIdx + rIdx - inIdx);

		return root;
	}

	class Node{
		Node parent, left, right;
		int v;

		public Node(int v){
			this.v = v;
		}
	}
}