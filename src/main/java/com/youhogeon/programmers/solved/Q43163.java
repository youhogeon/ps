package com.youhogeon.programmers.solved;

import java.util.*;

public class Q43163 {

    class Solution {
        public int solution(String begin, String target, String[] words) {
            Map<String, Node> map = new HashMap<>();
    
            for (String word : words) map.put(word, new Node(word));
    
            for (int i = 0; i < words.length; i++) {
                for (int j = i + 1; j < words.length; j++) {
                    if (!isSameWithoutOneChar(words[i], words[j])) continue;
    
                    map.get(words[i]).nodes.add(map.get(words[j]));
                    map.get(words[j]).nodes.add(map.get(words[i]));
                }
            }
    
            Queue<Node> queue = new PriorityQueue<>();
    
            if (map.containsKey(target)) queue.add(map.get(target));
    
            while (!queue.isEmpty()) {
                Node node = queue.poll();
    
                if (isSameWithoutOneChar(node.id, begin)) return node.cost;
    
                for (Node n : node.nodes) {
                    if (n.cost != 1) continue;
    
                    n.cost = node.cost + 1;
                    queue.add(n);
                }
            }
    
            return 0;
        }
    
        boolean isSameWithoutOneChar(String a, String b) {
            int diffCnt = 0;
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) diffCnt++;
            }
    
            return diffCnt == 1;
        }
    
        class Node implements Comparable<Node>{
            String id;
            List<Node> nodes = new ArrayList<>();
            int cost = 1;
    
            public Node(String id) {
                this.id = id;
            }
    
            @Override
            public int compareTo(Node o) {
                if (o.cost < this.cost) return 1;
                if (o.cost > this.cost) return -1;
    
                return 0;
            }
        }
    }
    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution("hit","cog",new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(s.solution("hit","cog",new String[]{"hot", "dot", "dog", "lot", "log"}));        
    }
}
