package com.youhogeon.kakao.blind2019;

import java.util.*;

public class Q5 {
    class Solution {
        public int[][] solution(int[][] nodeinfo) {
            Queue<Node> queue = new PriorityQueue<>();
            List<Node> prevNodes = new ArrayList<Node>();
            List<Node> currentNodes = new ArrayList<Node>();
            
            int id = 0;
            for (int[] n : nodeinfo) queue.add(new Node(n[0], n[1], ++id));
            
            Node head = queue.poll();
            prevNodes.add(head);
    
            while (!queue.isEmpty()) {
                Node n = queue.poll();
    
                currentNodes.add(n);
    
                if (queue.isEmpty() || n.y != queue.peek().y) {
                    int prevId = 0;
    
                    for (Node m : currentNodes) {
                        while (true) {
                            if (m.x < prevNodes.get(prevId).x && m.x > prevNodes.get(prevId).minX) {
                                prevNodes.get(prevId).left = m;
                                m.maxX = prevNodes.get(prevId).x;
                                m.minX = prevNodes.get(prevId).minX;
                                break;
                            } else if (m.x > prevNodes.get(prevId).x && m.x < prevNodes.get(prevId).maxX) {
                                prevNodes.get(prevId).right = m;
                                m.minX = prevNodes.get(prevId).x;
                                m.maxX = prevNodes.get(prevId).maxX;
                                break;
                            } else prevId++;
                        }
    
                        if (prevId >= prevNodes.size()) break;
                    }
    
                    prevNodes = currentNodes;
                    currentNodes = new ArrayList<Node>();
                }
            }
            //트리 만들기 종료
    
            List<Integer> 전위순회 = new ArrayList<>();
            List<Integer> 후위순회 = new ArrayList<>();
    
            전위순회(head, 전위순회);
            후위순회(head, 후위순회);
    
            int[][] answer = new int[2][nodeinfo.length];
    
            for (int i = 0; i < nodeinfo.length; i++) answer[0][i] = 전위순회.get(i);
            for (int i = 0; i < nodeinfo.length; i++) answer[1][i] = 후위순회.get(i);
    
            return answer;
        }
    
        void 전위순회(Node n, List<Integer> result) {
            result.add(n.id);
    
            if (n.left != null) 전위순회(n.left, result);
            if (n.right != null) 전위순회(n.right, result);
        }
    
        void 후위순회(Node n, List<Integer> result) {
            if (n.left != null) 후위순회(n.left, result);
            if (n.right != null) 후위순회(n.right, result);
    
            result.add(n.id);
        }
    
        class Node implements Comparable<Node>{
            int x, y, id;
            Node left, right;
            int maxX = 9999999, minX = -1;
    
            public Node(int x, int y, int id) {
                this.x = x;
                this.y = y;
                this.id = id;
            }
    
            @Override
            public int compareTo(Node o) {
                if (o.y > this.y) return 1;
                if (o.y < this.y) return -1;
    
                if (o.x < this.x) return 1;
                if (o.x > this.x) return -1;
    
                return 0;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new int[][]{new int[]{5,3},new int[]{11,5},new int[]{13,3},new int[]{3,5},new int[]{6,1},new int[]{1,3},new int[]{8,6},new int[]{7,2},new int[]{2,2}}	));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}