package com.youhogeon.kakao.blind2020;

import java.util.*;

public class Q2 {
    class Solution {
        public String solution(String p) {
            boolean[] arr = new boolean[p.length()];
            char[] chars = p.toCharArray();
    
            for (int i = 0; i < chars.length; i++) arr[i] = chars[i] == '(';
    
            boolean[] result = 재귀(arr);
    
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length; i++) sb.append(result[i] ? '(' : ')');
    
            return sb.toString();
        }
    
        boolean[] 재귀(boolean[] arr) {
            for (int i = 1; i < arr.length; i += 2) {
                boolean[] u = Arrays.copyOfRange(arr, 0, i + 1);
                boolean[] v = Arrays.copyOfRange(arr, i + 1, arr.length);
    
                if (!is균형잡힌괄호문자열(u)) continue;
    
                boolean[] result = new boolean[arr.length];
                boolean[] newV = 재귀(v);
                if (is올바른괄호문자열(u)) {
                    System.arraycopy(u, 0, result, 0, u.length);
                    System.arraycopy(newV, 0, result, u.length, newV.length);
                } else {
                    result[0] = true;
                    System.arraycopy(newV, 0, result, 1, v.length);
                    result[v.length + 1] = false;
    
                    int k = v.length + 2;
                    for (int j = 0; j < u.length - 2; j++) {
                        result[k + j] = !u[j + 1];
                    }
                }
    
                return result;
            }
    
            return arr;
        }
    
        boolean is균형잡힌괄호문자열(boolean[] arr) {
            int a = 0, b = 0;
    
            for (int i = 0; i < arr.length; i++) {
                if (arr[i]) a++;
                else b++;
            }
    
            return a == b;
        }
    
        boolean is올바른괄호문자열(boolean[] arr) {
            int cnt = 0;
    
            for (int i = 0; i < arr.length; i++) {
                if (arr[i]) cnt++;
                else if (cnt > 0) cnt--;
                else return false;
            }
    
            return true;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution("()))((()"));
        solution.add(s.solution("(()())()"));
        solution.add(s.solution(")("));
        solution.add(s.solution(""));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}