package com.youhogeon.kakao.blind2021.test1;

import java.util.*;

public class Q7 {
    class Solution {
        int[][] cache;
    
        public int solution(int[] sales, int[][] links) {
            cache = new int[2][sales.length];
            Arrays.fill(cache[0], -1);
            Arrays.fill(cache[1], -1);
            
            List<Person> list = new ArrayList<>();
            for (int i = 0; i < sales.length; i++) {
                list.add(new Person(i, sales[i]));
            }
            
            for (int i = 0; i < links.length; i++) {
                Person parent = list.get(links[i][0] - 1);
                Person child = list.get(links[i][1] - 1);
                parent.children.add(child);
                child.parent = parent;
            }
            
            Person ceo = list.get(0);
    
            return Math.min(dp1(ceo), dp2(ceo));
        }
    
        int dp1(Person p) { //나 포함
            if (cache[0][p.id] >= 0) return cache[0][p.id];
    
            int result = p.cost;
    
            for (Person c : p.children) {
                result += Math.min(dp1(c), dp2(c));
            }
    
            cache[0][p.id] = result;
            return result;
        }
    
        int dp2(Person p) { //나 제외
            if (cache[1][p.id] >= 0) return cache[1][p.id];
    
            int min = 0x7FFFFFFF;
    
            for (Person c : p.children) {
                int result = dp1(c);
    
                for (Person c2 : p.children) {
                    if (c.id == c2.id) continue;
    
                    result += Math.min(dp1(c2), dp2(c2));
                }
    
                min = Math.min(result, min);
            }
    
            min = min == 0x7FFFFFFF ? 0 : min;
            cache[1][p.id] = min;
            return min;
        }
    
        class Person {
            int id, cost;
            List<Person> children = new ArrayList<>();
            Person parent;
    
            public Person(int id, int cost) {
                this.id = id;
                this.cost = cost;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new int[]{14, 17, 15, 18, 19, 14, 13, 16, 28, 17},	new int[][]{new int[]{10, 8}, new int[]{1, 9}, new int[]{9, 7}, new int[]{5, 4}, new int[]{1, 5}, new int[]{5, 10}, new int[]{10, 6}, new int[]{1, 3}, new int[]{10, 2}}));
        solution.add(s.solution(new int[]{5, 6, 5, 3, 4},	new int[][]{new int[]{2,3}, new int[]{1,4}, new int[]{2,5}, new int[]{1,2}}));
        solution.add(s.solution(new int[]{5, 6, 5, 1, 4},	new int[][]{new int[]{2,3}, new int[]{1,4}, new int[]{2,5}, new int[]{1,2}}));
        solution.add(s.solution(new int[]{10, 10, 1, 1},	new int[][]{new int[]{3,2}, new int[]{4,3}, new int[]{1,4}}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}