package com.youhogeon.acmicpc;

import java.util.HashMap;
import java.util.Scanner;

class Q1991{
	int N;
	HashMap<Character, TreeNode> treeMap = new HashMap<Character, TreeNode>();
	TreeNode root;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		N = Integer.parseInt(sc.nextLine());

		while (N-- > 0){
			char[] s = sc.nextLine().toCharArray();

			TreeNode t = new TreeNode(s[0]);
			if (s[2] != '.') t.tmpLeft = s[2];
			if (s[4] != '.') t.tmpRight = s[4];

			treeMap.put(s[0], t);
		}

		sc.close();
	}

	public void solve(){
		root = treeMap.get('A');
		makeTree(root);

		printPreOrder(root);
		System.out.println();
		printInOrder(root);
		System.out.println();
		printPostOrder(root);
		System.out.println();
	}

	public void printPreOrder(TreeNode tn){
		System.out.print(tn.v);
		if (tn.left != null) printPreOrder(tn.left);
		if (tn.right != null) printPreOrder(tn.right);
	}

	public void printInOrder(TreeNode tn){
		if (tn.left != null) printInOrder(tn.left);
		System.out.print(tn.v);
		if (tn.right != null) printInOrder(tn.right);
	}

	public void printPostOrder(TreeNode tn){
		if (tn.left != null) printPostOrder(tn.left);
		if (tn.right != null) printPostOrder(tn.right);
		System.out.print(tn.v);
	}

	public void makeTree(TreeNode parent){
		if (parent.tmpLeft != 0){
			parent.left = treeMap.get(parent.tmpLeft);
			makeTree(parent.left);
		}

		if (parent.tmpRight != 0){
			parent.right = treeMap.get(parent.tmpRight);
			makeTree(parent.right);
		}
	}

	class TreeNode{
		TreeNode parent, left, right;
		char v, tmpLeft, tmpRight;

		public TreeNode(char v){
			this.v = v;
		}
	}
}