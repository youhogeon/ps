package com.youhogeon.kakao.intern2021;

import java.util.*;

public class Q3 {
    class Solution {
        //더 좋은 풀이 https://school.programmers.co.kr/learn/courses/30/lessons/81303/solution_groups?language=java
    
        List<Node> list;
        Node current = null;
        Stack<Node> logs = new Stack<>();
    
        public String solution(int n, int k, String[] cmd) {
            list = new ArrayList<>();
            logs = new Stack<>();
            current = null;
            
            for (int i = 0; i < n; i++) {
                Node node = new Node();
                
                if (i > 0) {
                    node.prev = list.get(i - 1);
                    node.prev.next = node;
                }
                
                list.add(node);
                if (i == k) current = node;
            }
    
            for (int i = 0; i < cmd.length; i++) {
                char c = cmd[i].charAt(0);
    
                if (c == 'C') remove();
                else if (c == 'Z') restore();
                else move(Integer.parseInt(cmd[i].split(" ")[1]), c == 'U');
            }
    
            StringBuilder sb = new StringBuilder();
            for (Node node : list)  sb.append(node.isDeleted ? 'X' : 'O');
    
            return sb.toString();
        }
    
        void move(int k, boolean isUp) {
            if (isUp) while (k-- > 0) current = current.prev;
            else while(k-- > 0) current = current.next;
        }
    
        void remove() {
            current.isDeleted = true;
            logs.push(current);
    
            if (current.next == null) {
                current.prev.next = null;
                current = current.prev;
            } else {
                if (current.prev != null) current.prev.next = current.next;
                current.next.prev = current.prev;
                current = current.next;
            }
        }
    
        void restore() {
            Node n = logs.pop();
    
            n.isDeleted = false;
    
            if (n.prev != null) n.prev.next = n;
            if (n.next != null) n.next.prev = n;
        }
    
        class Node {
            boolean isDeleted;
            Node prev, next;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(4	,	3	,	new String[]{"C", "C", "C", "Z", "Z", "Z"}));
        solution.add(s.solution(8	,	2	,	new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
        solution.add(s.solution(8	,	2	,	new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
