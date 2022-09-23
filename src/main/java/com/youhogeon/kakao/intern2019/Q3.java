package com.youhogeon.kakao.intern2019;

import java.util.*;

public class Q3 {
    class Solution {
        Map<Long, Node> map = new HashMap<>();
    
        Node find(long rn) {
            Node n = map.get(rn);
    
            Queue<Node> log = new LinkedList<>();
            while (n.parent != null) {
                log.add(n);
                n = n.parent;
            }
    
            while (!log.isEmpty()) {
                log.poll().parent = n;
            }
            
            return n;
        }
    
        public long[] solution(long k, long[] room_number) {
            long[] result = new long[room_number.length];
    
            for (int i = 0; i < room_number.length; i++) {
                long rn = room_number[i];
    
                if (map.containsKey(rn)) rn = find(rn).id + 1;
    
                Node newNode = new Node(rn);
                map.put(rn, newNode);
    
                Node prevNode = map.get(rn - 1);
                if (prevNode != null) prevNode.parent = newNode;
                
                Node nextNode = map.get(rn + 1);
                if (nextNode != null) newNode.parent = nextNode;
                
                result[i] = rn;
            }
    
            return result;
        }
    
        class Node {
            Node parent = null;
            long id;
    
            public Node(long id) {
                this.id = id;
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(10	,	new long[]{1,3,4,1,3,1}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
