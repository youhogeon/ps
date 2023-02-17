package com.youhogeon.programmers.solved;

import java.util.*;

public class Q86971 {
    
    class Solution {
        public int solution(int n, int[][] wires) {
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) nodes[i] = new Node();
            
            for (int[] wire : wires) {
                nodes[wire[0] - 1].childNodes.add(nodes[wire[1] - 1]);
                nodes[wire[1] - 1].childNodes.add(nodes[wire[0] - 1]);
            }
    
            find(nodes[0]);
            
            int answer = 0x7FFFFFFF;
            for (Node node : nodes) {
                answer = Math.min(answer, Math.abs(n - node.count * 2));
            }
    
            return answer;
        }
    
        int find(Node node) {
            int sum = 1;
            node.count = -1;
    
            for (Node n : node.childNodes) {
                if (n.count == -1) continue;
                
                sum += find(n);
            }
    
            node.count = sum;
    
            return sum;
        }
    
        class Node {
            int count = 0;
            List<Node> childNodes = new ArrayList<>();
        }
    }

    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(9, new int[][]{new int[]{1,3},new int[]{2,3},new int[]{3,4},new int[]{4,5},new int[]{4,6},new int[]{4,7},new int[]{7,8},new int[]{7,9}}));
        System.out.println(s.solution(4, new int[][]{new int[]{1,2},new int[]{2,3},new int[]{3,4}}));
        System.out.println(s.solution(7, new int[][]{new int[]{1,2},new int[]{2,7},new int[]{3,7},new int[]{3,4},new int[]{4,5},new int[]{6,7}}));
    }
}
