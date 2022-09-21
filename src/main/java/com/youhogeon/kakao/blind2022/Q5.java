package com.youhogeon.kakao.blind2022;

import java.util.*;

public class Q5 {

    class Solution {
        Node[] nodes;
    
        public int solution(int[] info, int[][] edges) {
            nodes = new Node[info.length];
            for (int i = 0; i < info.length; i++) nodes[i] = new Node(i, info[i]);
            for (int i = 0; i < edges.length; i++) nodes[edges[i][0]].addChild(nodes[edges[i][1]]);
    
            int max = -1;
            Queue<State> queue = new LinkedList<>();
    
            queue.add(new State());
    
            while (!queue.isEmpty()) {
                State s = queue.poll();
    
                List<State> newState = s.find();
                if (newState.size() > 0) {
                    for (State ss : newState) queue.add(ss);
                }
    
                max = Math.max(max, s.count0);
            }
    
            return max;
        }
    
        class State {
            boolean[] visited = new boolean[17];
            int count0 = 1, count1 = 0;
    
            public State() {
                visited[0] = true;
            }
    
            public State(boolean[] visited) {
                this.visited = visited;
            }
    
            List<State> find() {
                List<State> states = new ArrayList<>();
    
                Queue<Node> queue = new LinkedList<>();
                queue.add(nodes[0]);
                while (!queue.isEmpty()) {
                    Node a = queue.poll();
                    Node b = a.children[0], c = a.children[1];
    
                    if (b != null) {
                        if (visited[b.id]) {
                            queue.add(b);
                        } else {
                            State s = new State(Arrays.copyOf(visited, visited.length));
                            s.visited[b.id] = true;
                            s.count0 = this.count0;
                            s.count1 = this.count1;
                            if (b.info == 0) s.count0++;
                            else s.count1++;
    
                            if (s.count0 > s.count1) states.add(s);
                        }
                    }
    
                    if (c != null) {
                        if (visited[c.id]) {
                            queue.add(c);
                        } else {
                            State s = new State(Arrays.copyOf(visited, visited.length));
                            s.visited[c.id] = true;
                            s.count0 = this.count0;
                            s.count1 = this.count1;
                            if (c.info == 0) s.count0++;
                            else s.count1++;
    
                            if (s.count0 > s.count1) states.add(s);
                        }
                    }
                }
    
                return states;
            }
        }
    
        class Node {
            int id, info;
            Node[] children = new Node[2];
    
            public Node(int id, int info) {
                this.id = id;
                this.info = info;
            }
    
            void addChild(Node n) {
                if (children[0] == null) children[0] = n;
                else children[1] = n;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new int[]{0,0,1,1,1,0,1,0,1,0,1,1},new int[][]{new int[]{0,1},new int[]{1,2},new int[]{1,4},new int[]{0,8},new int[]{8,7},new int[]{9,10},new int[]{9,11},new int[]{4,3},new int[]{6,5},new int[]{4,6},new int[]{8,9}}));
        solution.add(s.solution(new int[]{0,1,0,1,1,0,1,0,0,1,0},new int[][]{new int[]{0,1},new int[]{0,2},new int[]{1,3},new int[]{1,4},new int[]{2,5},new int[]{2,6},new int[]{3,7},new int[]{4,8},new int[]{6,9},new int[]{9,10}}));


        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
