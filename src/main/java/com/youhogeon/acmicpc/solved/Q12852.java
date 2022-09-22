package com.youhogeon.acmicpc.solved;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Q12852{
	int N;

	public Q12852() throws IOException{
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();

		sc.close();
	}

	public void solve(){
		if (N == 1){
			System.out.println(0);
			System.out.println(1);
			return;
		}

		int[] count = new int[N + 1];
		int[] from = new int[N + 1];
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.offer(1);
		
		while (true){
			int me = queue.poll();

			if (me * 3 <= N && count[me * 3] == 0){
				queue.offer(me * 3);
				count[me * 3] = count[me] + 1;
				from[me * 3] = me;
				if (me * 3 == N) break;
			}

			if (me * 2 <= N && count[me * 2] == 0){
				queue.offer(me * 2);
				count[me * 2] = count[me] + 1;
				from[me * 2] = me;
				if (me * 2 == N) break;
			}

			if (me + 1 <= N && count[me + 1] == 0){
				queue.offer(me + 1);
				count[me + 1] = count[me] + 1;
				from[me + 1] = me;
				if (me + 1 == N) break;
			}
		}

		System.out.println(count[N]);
		while (N > 1){
			System.out.print(N);
			N = from[N];
			System.out.print(" ");
		}
		System.out.println(1);
	}
}