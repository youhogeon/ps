package com.youhogeon.kakao.intern2020;

import java.util.*;

public class Q2 {
    class Solution {
        public long solution(String expression) {
            String[] strNumbers = expression.split("[\\*\\+\\-]");
            char[] strOps = expression.replaceAll("[0-9]", "").toCharArray();
    
            List<Long> numbers = new ArrayList<>();
            List<Integer> ops = new ArrayList<>();
            for (String str : strNumbers) numbers.add(Long.parseLong(str));
            for (char c : strOps) ops.add((int) c);
    
            long max = -1;
            int[][] priorities = { { '+', '-', '*' }, { '+', '*', '-' }, { '-', '+', '*' }, { '-', '*', '+' }, { '*', '-', '+' }, { '*', '+', '-' } };
            for (int[] priority : priorities) max = Math.max(max, calc(numbers, ops, priority));
    
            return max;
        }
    
        public long opCalc(int op, long a, long b) {
            if (op == '+') return a + b;
            if (op == '-') return a - b;
    
            return a * b;
        }
    
        public long calc(List<Long> n, List<Integer> o, int[] priority) {
            List<Long> numbers = new ArrayList<>(n);
            List<Integer> ops = new ArrayList<>(o);
    
            for (int i = 0; i < priority.length; i++) {
                for (int j = 0; j < ops.size(); j++) {
                    if (ops.get(j) != priority[i]) continue;
    
                    numbers.set(j, opCalc(priority[i], numbers.get(j), numbers.get(j + 1)));
    
                    numbers.remove(j + 1);
                    ops.remove(j--);
                }
            }
    
            return Math.abs(numbers.get(0));
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution("100-200*300-500+20"));
        solution.add(s.solution("50*6-3*2"));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
