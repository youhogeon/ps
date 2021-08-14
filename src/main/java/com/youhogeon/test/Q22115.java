package com.youhogeon.test;

import java.util.*;

class Q22115 implements Q{
	private int N, K;
	private List<Integer> C = new LinkedList<Integer>();

	public void scan(){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();

		for (int i = 0; i < N; i++) C.add(sc.nextInt());

		sc.close();
	}

	public int solve(){
		/*Random rand = new Random();

	for (int j = 0; j <= 100; j++){
		int n = rand.nextInt(10) + 1;
		int k = rand.nextInt(100);
		List<Integer> c = new LinkedList<Integer>();
		for (int i = 0; i < n; i++){
			c.add(rand.nextInt(10) + 1);
		}
		System.out.println(k);
		System.out.println(c.toString());
		System.out.println(can(c, k));
		System.out.println("");
		
	}
	return 0;
	*/


		scan();
		int result = can(C, K);
		return result;
	}

	private int can(List<Integer> C, int K){
		//System.out.println(C.toString());
		//System.out.println((K));

		int count = C.size();

		List<List<Integer>> matrixK = new ArrayList<List<Integer>>();
		List<List<Integer>> matrixC = new ArrayList<List<Integer>>();
		for (int i = 0; i <= count; i++){
			List<Integer> rowK = new ArrayList<Integer>();
			List<Integer> rowC = new ArrayList<Integer>();

			for (int j = 0; j <= K; j++){
				int newC = 0;
				int newK = j;

				if (i != 0 && j != 0){
					int localK = C.get(i - 1);

					int upleftC = 0;
					int upleftK = 999999999;
					int upK = matrixK.get(i - 1).get(j);
					int upC = matrixC.get(i - 1).get(j);

					if (j >= localK){
						upleftK = matrixK.get(i - 1).get(j - localK);
						upleftC = matrixC.get(i - 1).get(j - localK);
					}

					if (upleftK < upK){
						newK = upleftK;
						newC = upleftC + 1;
					}else if (upleftK == upK){
						newK = upleftK;
						newC = Math.min(upleftC + 1, upC);
					}else{
						newC = upC;
						newK = upK;

						if (newK >= localK){
							newK -= localK;
							newC++;
						}
					}
				}

				//System.out.println(newK);
				//System.out.println(newC);
				//System.out.println("");

				rowC.add(newC);
				rowK.add(newK);
			}

			matrixK.add(rowK);
			matrixC.add(rowC);
		}

		if (matrixK.get(count).get(K) != 0) return -1;

		return matrixC.get(count).get(K);
	}
}