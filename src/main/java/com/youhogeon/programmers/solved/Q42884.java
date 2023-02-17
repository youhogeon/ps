package com.youhogeon.programmers.solved;

import java.util.*;

public class Q42884 {


    class Solution {
        public int solution(int[][] routes) {
            Queue<Car> cars = new PriorityQueue<>();
    
            for (int[] route: routes) cars.add(new Car(route[0], route[1]));
    
            int count = 0;
            int x = Integer.MIN_VALUE;
            while (!cars.isEmpty()) {
                Car c = cars.poll();
    
                if (x < c.a) {
                    count++;
                    x = c.b;
                }
            }
    
            return count;
        }
    
        class Car implements Comparable<Car>{
            int a, b;
    
            public Car(int a, int b) {
                this.a = a;
                this.b = b;
            }
    
            @Override
            public int compareTo(Car o) {
                if (o.b < this.b) return 1;
                if (o.b > this.b) return -1;
    
                return 0;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(new int[][]{new int[]{-20,-15}, new int[]{-14,-5}, new int[]{-18,-13}, new int[]{-5,-3}}));
    }
}
