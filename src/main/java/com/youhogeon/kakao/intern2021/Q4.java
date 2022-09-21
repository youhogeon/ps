package com.youhogeon.kakao.intern2021;

import java.util.*;

public class Q4 {
    class Solution {
        public int solution(int n, int start, int end, int[][] roads, int[] traps) {
            List<Node> nodes = new ArrayList<>();
    
            for (int i = 0; i < n; i++) nodes.add(new Node(i));
            for (int i = 0; i < traps.length; i++) nodes.get(traps[i] - 1).isTrap = true;
    
            for (int i = 0; i < roads.length; i++) {
                Road r = new Road(roads[i][0] - 1, roads[i][1] - 1, roads[i][2]);
    
                nodes.get(r.from).roads.add(r);
                nodes.get(r.to).roads.add(r);
            }
    
            --start;
            --end;
    
            Map<String, Integer> cache = new HashMap<>();
    
            Queue<State> queue = new PriorityQueue<>();
            queue.add(new State(nodes.get(start), 0));
    
            while (!queue.isEmpty()) {
                State state = queue.poll();
    
                if (state.current.id == end) return state.cost;
                
                for (Road r : state.current.roads) {
                    boolean trapEnabled = state.isTrapEnabled(r.from, r.to);
    
                    if (trapEnabled && state.current.id != r.to) continue;
                    if (!trapEnabled && state.current.id != r.from) continue;
    
                    State newState = new State(state.current.id == r.from ? nodes.get(r.to) : nodes.get(r.from), state.cost + r.cost, state.trapEnabled);
    
                    if (newState.current.isTrap) newState.enableTrap(newState.current.id);
    
                    String key = newState.toString();
                    if (cache.getOrDefault(key, 0) > 1) continue;
    
                    cache.put(key, cache.getOrDefault(key, 0) + 1);
                    queue.add(newState);
                }
            }
    
            return -1;
        }
    
        class State implements Comparable<State> {
            Node current;
            Set<Integer> trapEnabled;
            int cost;
    
            public State(Node current, int cost) {
                this.current = current;
                this.cost = cost;
                trapEnabled = new HashSet<>();
            }
    
            public State(Node current, int cost, Set<Integer> trapEnabled) {
                this.current = current;
                this.cost = cost;
                this.trapEnabled = new HashSet<>(trapEnabled);
            }
    
            boolean isTrapEnabled(int from, int to) {
                return trapEnabled.contains(from) ^ trapEnabled.contains(to);
            }
    
            void enableTrap(int id) {
                if (trapEnabled.contains(id)) trapEnabled.remove(id);
                else trapEnabled.add(id);
            }
    
            @Override
            public int compareTo(State o) {
                if (this.cost > o.cost) return 1;
                if (this.cost < o.cost) return -1;
    
                return 0;
            }
    
            @Override
            public String toString() {
                return "State [current=" + current.id + ", trapEnabled=" + Arrays.toString(trapEnabled.toArray())
                        + "]";
            }
        }
    
        class Road {
            int from, to, cost;
    
            public Road(int from, int to, int cost) {
                this.from = from;
                this.to = to;
                this.cost = cost;
            }
        }
    
        class Node {
            int id;
            boolean isTrap;
            List<Road> roads = new ArrayList<>();
    
            public Node(int id) {
                this.id = id;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(3	,	1	,	3	,	new int[][]{new int[]{1, 2, 2}, new int[]{3, 2, 3}}	,	new int[]{2}));
        solution.add(s.solution(4	,	1	,	4	,	new int[][]{new int[]{1, 2, 1}, new int[]{3, 2, 1}, new int[]{2, 4, 1}}	,	new int[]{2, 3}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
