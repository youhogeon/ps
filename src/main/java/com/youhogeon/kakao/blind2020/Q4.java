package com.youhogeon.kakao.blind2020;

import java.util.*;

public class Q4 {
    class Solution {
        public int[] solution(String[] words, String[] queries) {
            Node head1 = new Node(), head2 = new Node();
            int[] answer = new int[queries.length];
    
            for (int i = 0; i < words.length; i++) {
                int len = words[i].length();
                char[] chars = words[i].toCharArray();
                Node n1 = head1, n2 = head2;
    
                for (int j = 0; j < len; j++) {
                    n1.addCount(len);
                    n1 = n1.getChild(chars[j]);
    
                    n2.addCount(len);
                    n2 = n2.getChild(chars[len - j - 1]);
                }
            }
    
            for (int i = 0; i < answer.length; i++) {
                char[] chars;
                Node n;
                int len = queries[i].length();
    
                if (queries[i].charAt(0) == '?') {
                    char[] tmp = queries[i].toCharArray();
                    chars = new char[len];
                    for (int j = 0; j < len; j++) {
                        chars[j] = tmp[len - j - 1];
                    }
    
                    n = head2;
                } else {
                    chars = queries[i].toCharArray();
                    n = head1;
                }
    
                for (int j = 0; j < len; j++) {
                    if (chars[j] == '?' || n == null) break;
    
                    n = n.children[chars[j] - 'a'];
                }
    
                answer[i] = n == null ? 0 : n.count.getOrDefault(len, 0);
            }
    
            return answer;
        }
    
        class Node {
            Node[] children = new Node[26];
            Map<Integer, Integer> count = new HashMap<>();
    
            void addCount(int len) {
                count.put(len, count.getOrDefault(len, 0) + 1);
            }
    
            Node getChild(char c) {
                if (children[c - 'a'] == null) children[c - 'a'] = new Node();
        
                return children[c - 'a'];
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[]{"fro??", "????o", "fr???", "fro???", "pro?"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}