package com.youhogeon.acmicpc.solved;

import java.util.Scanner;

class Q5639{
	TreeNode root;

	public void scan(){
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()){
			TreeNode n = new TreeNode(sc.nextInt());

			if (root == null) root = n;
			else{
				TreeNode parent = root;

				while (true){
					if (parent.v > n.v){
						if (parent.left != null) parent = parent.left;
						else{
							parent.left = n;
							break;
						}
					}else{
						if (parent.right != null) parent = parent.right;
						else{
							parent.right = n;
							break;
						}
					}
				}
			}
		}

		sc.close();
	}

	public void solve(){
		printPostOrder(root);
	}

	public void printPostOrder(TreeNode tn){
		if (tn.left != null) printPostOrder(tn.left);
		if (tn.right != null) printPostOrder(tn.right);
		System.out.println(tn.v);
	}

	class TreeNode{
		TreeNode parent, left, right;
		int v;

		public TreeNode(int v){
			this.v = v;
		}
	}
}