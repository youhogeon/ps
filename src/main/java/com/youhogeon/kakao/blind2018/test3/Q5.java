package com.youhogeon.kakao.blind2018.test3;

import java.util.*;

public class Q5 {
    class Solution {
        class Node {
            int cnt = 1;
            Node[] children = new Node[26];
        }
    
        public int solution(String[] words) {
            Node head = new Node();
    
            for (int i = 0; i < words.length; i++) {
                char[] chars = words[i].toCharArray();
                Node n = head;
    
                for (int j = 0; j < chars.length; j++) {
                    int x = chars[j] - 'a';
                    
                    if (n.children[x] == null) n.children[x] = new Node();
                    else n.children[x].cnt++;
    
                    n = n.children[x];
                }
            }
    
            int answer = 0;
    
            for (int i = 0; i < words.length; i++) {
                char[] chars = words[i].toCharArray();
                Node n = head;
    
                for (int j = 1; j <= chars.length; j++) {
                    int x = chars[j - 1] - 'a';
    
                    if (n.children[x].cnt == 1 || j == chars.length) {
                        answer += j;
                        break;
                    }
    
                    n = n.children[x];
                }
            }
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new String[]{"go","gone","guild"}));
        solution.add(s.solution(new String[]{"abc","def","ghi","jklm"}));
        solution.add(s.solution(new String[]{"word","war","warrior","world"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}