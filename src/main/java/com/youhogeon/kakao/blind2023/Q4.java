package com.youhogeon.kakao.blind2023;

import java.util.*;

public class Q4 {
    class Solution {
    
        public int[] solution(long[] numbers) {
            int[] answer = new int[numbers.length];
    
            for (int i = 0; i < numbers.length; i++) {
                int N = (int)Math.ceil(log2(numbers[i] + 1));
                int depth = (int)log2(N);
        
                Node head = new Node();
                makeChild(head, depth);
        
                List<Node> list = new ArrayList<>();
                loop(list, head);
        
                for (int j = 0; j < list.size(); j++) list.get(j).v = (numbers[i] & (long)((long)1 << (long)(list.size() - 1 - j))) > 0 ? 1 : 0;
        
                answer[i] = isValid(head) ? 1 : 0;
    
                //if (isAllLeftNodeZero(list)) answer[i] = 0;
            }
    
            return answer;
        }
    
        boolean isAllLeftNodeZero(List<Node> list) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).left != null) continue;
    
                if (list.get(i).v == 1) return false;
            }
    
            return true;
        }
    
        boolean isValid(Node n) {
            if (n.left == null) return true;
    
            if (n.left != null && n.v == 0 && (n.left.v | n.right.v) != 0) return false;
    
            return isValid(n.left) & isValid(n.right);
        }
    
        void loop(List<Node> list, Node n) {
            if (n.left == null) {
                list.add(n);
                return;
            }
    
            loop(list, n.left);
            list.add(n);
            loop(list, n.right);
        }
    
        void makeChild(Node n, int depth) {
            if (depth-- == 0) return;
    
            n.left = new Node();
            n.right = new Node();
    
            makeChild(n.left, depth);
            makeChild(n.right, depth);
        }
    
        class Node {
            int v;
            Node left, right;
        }
    
        double log2(double x) {
            return Math.log(x) / Math.log(2);
        }
    }
    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new long[]{58, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}));
        solution.add(s.solution(new long[]{7, 5}));
        solution.add(s.solution(new long[]{63, 111, 95}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
