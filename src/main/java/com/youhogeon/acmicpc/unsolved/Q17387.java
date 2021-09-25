package com.youhogeon.acmicpc.unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q17387{
	Line l1, l2;

	public Q17387() throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] s = bf.readLine().split(" ");
		l1 = new Line(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
		s = bf.readLine().split(" ");
		l2 = new Line(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
	}

	public int solve(){
		if (l1.x1 == l1.x2 && l2.x1 <= l1.x1 && l2.x2 >= l1.x1) return 1;
		else if(l2.x1 == l2.x2 && l1.x1 <= l2.x1 && l1.x2 >= l2.x1) return 1;

		l1.calc();
		l2.calc();

		if (Double.compare(l1.a, l2.a) == 0){
			if (l1.x1 <= l2.x1 && l1.x2 >= l2.x1 || l2.x1 <= l1.x1 && l2.x2 >= l1.x1) return 1;

			return 0;
		}

		Double cross = 0.0;
		try{
			cross = (l1.axisY - l2.axisY) / (l2.a - l1.a);
		}catch(Exception e){
			return 0;
		}

		if (cross >= l1.x1 && cross <= l1.x2 && cross >= l2.x1 && cross <= l2.x2) return 1;
		
		return 0;
	}

	class Line{
		int x1, x2, y1, y2;
		double a, axisY;

		public Line(int x1, int y1, int x2, int y2){
			if (x1 < x2){
				this.x1 = x1;
				this.x2 = x2;
				this.y1 = y1;
				this.y2 = y2;
			}else{
				this.x1 = x2;
				this.x2 = x1;
				this.y1 = y2;
				this.y2 = y1;
			}

			calc();
		}

		public void calc(){
			this.a = x1==x2?Double.MAX_VALUE:(y2 - y1) / (x2 - x1);
			this.axisY = y1 - x1 * this.a;
		}
	}
}