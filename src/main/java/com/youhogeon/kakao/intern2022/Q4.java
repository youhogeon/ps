package com.youhogeon.kakao.intern2022;

import java.util.*;

public class Q4 {
    class Solution {
        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) nodes[i] = new Node(i);
    
            for (int i = 0; i < gates.length; i++) nodes[gates[i] - 1].isGate = true;
            for (int i = 0; i < summits.length; i++) nodes[summits[i] - 1].isSummit = true;
    
            for (int i = 0; i < paths.length; i++) {
                if (!nodes[paths[i][1] - 1].isGate) nodes[paths[i][0] - 1].paths.add(new Path(nodes[paths[i][1] - 1], paths[i][2]));
                if (!nodes[paths[i][0] - 1].isGate) nodes[paths[i][1] - 1].paths.add(new Path(nodes[paths[i][0] - 1], paths[i][2]));
            }
    
            Queue<State> queue = new LinkedList<>();
            for (int i = 0; i < gates.length; i++) queue.add(new State(nodes[gates[i] - 1]));
    
            int[] visited = new int[n];
            Arrays.fill(visited, 0x7FFFFFFF);
    
            int summitID = 0x7FFFFFFF, intensity = 0x7FFFFFFF;
            while (!queue.isEmpty()) {
                State s = queue.poll();
    
                if (s.node.isSummit) {
                    if (s.node.id <= summitID && s.intensity == intensity || s.intensity < intensity) {
                        summitID = s.node.id;
                        intensity = s.intensity;
                    }
    
                    continue;
                }
    
                for (Path p : s.node.paths) {
                    State newS = new State(p.to);
                    newS.intensity = Math.max(s.intensity, p.cost);
    
                    if (visited[p.to.id] <= newS.intensity) continue;
                    visited[p.to.id] = newS.intensity;
    
                    queue.add(newS);
                }
            }
    
            return new int[]{ ++summitID, intensity };
        }
    
        class Path{
            Node to;
            int cost;
    
            public Path(Node to, int cost) {
                this.to = to;
                this.cost = cost;
            }
        }
    
        class Node{
            int id;
            List<Path> paths = new ArrayList<Path>();
            boolean isGate = false, isSummit = false;
    
            public Node(int id) {
                this.id = id;
            }
        }
    
        class State{
            int intensity = -1;
            Node node;
    
            public State(Node node) {
                this.node = node;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(6, new int[][]{new int[]{1, 2, 3}, new int[]{2, 3, 5}, new int[]{2, 4, 2}, new int[]{2, 5, 4}, new int[]{3, 4, 4}, new int[]{4, 5, 3}, new int[]{4, 6, 1}, new int[]{5, 6, 1}}, new int[]{1, 3}, new int[]{5}));
        solution.add(s.solution(7, new int[][]{new int[]{1, 4, 4}, new int[]{1, 6, 1}, new int[]{1, 7, 3}, new int[]{2, 5, 2}, new int[]{3, 7, 4}, new int[]{5, 6, 6}}, new int[]{1}, new int[]{2, 3, 4}));
        solution.add(s.solution(7, new int[][]{new int[]{1, 2, 5}, new int[]{1, 4, 1}, new int[]{2, 3, 1}, new int[]{2, 6, 7}, new int[]{4, 5, 1}, new int[]{5, 6, 1}, new int[]{6, 7, 1}}, new int[]{3, 7}, new int[]{1, 5}));
        solution.add(s.solution(5, new int[][]{new int[]{1, 3, 10}, new int[]{1, 4, 20}, new int[]{2, 3, 4}, new int[]{2, 4, 6}, new int[]{3, 5, 20}, new int[]{4, 5, 6}}, new int[]{1, 2}, new int[]{5}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
