package com.youhogeon.acmicpc.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Q2162{
	int N;
	Line[] lines;
	int[] head;

	public Q2162() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		lines = new Line[N];
		head = new int[N];

		for (int i = 0; i < N; i++) head[i] = i;

		for (int i = 0; i < N; i++) {
			String[] str = bf.readLine().split(" ");
			lines[i] = new Line(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]));

			for (int j = 0; j < i; j++) {
				if (lines[i].isMet(lines[j])) union(i, j);
			}
		}
	}

	public int solve() {
		int[] count = new int[N];
		Set<Integer> sets = new HashSet<Integer>();

		int max = -1;
		for (int i = 0; i < N; i++) {
			sets.add(find(i));
			count[find(i)]++;
			max = Math.max(max, count[find(i)]);
		}

		System.out.println(sets.size());

		return max;
	}

	public void union(int a, int b) {
		head[find(a)] = find(b);
	}

	public int find(int a) {
		Queue<Integer> queue = new LinkedList<Integer>();

		while (a != head[a]) {
			queue.add(a);
			a = head[a];
		}

		while (!queue.isEmpty()) head[queue.poll()] = a;

		return a;
	}

	class Line {
		Dot a, b;

		public Line(int x1, int y1, int x2, int y2) {
			a = new Dot(x1, y1);
			b = new Dot(x2, y2);

			if (x1 > x2) {
				Dot c = b;
				b = a;
				a = c;
			}
		}

		public long CCW(Dot c) {
			return a.x * b.y + b.x * c.y + c.x * a.y - b.x * a.y - c.x * b.y - a.x * c.y;
		}

		public boolean isMet(Line l) {
			long ccw1 = this.CCW(l.a) * this.CCW(l.b), ccw2 = l.CCW(this.a) * l.CCW(this.b);

			if (ccw1 < 0 && ccw2 < 0) return true;

			return (ccw1 <= 0 && ccw2 <= 0 && !(ccw1 == 0 && ccw2 == 0 && (this.b.x < l.a.x || l.b.x < this.a.x || Math.max(this.a.y, this.b.y) < Math.min(l.a.y, l.b.y) || Math.max(l.a.y, l.b.y) < Math.min(this.a.y, this.b.y))));
		}
	}

	class Dot {
		long x, y;
		
		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}