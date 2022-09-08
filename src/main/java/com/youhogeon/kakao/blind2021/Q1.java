package com.youhogeon.kakao.blind2021;

import java.util.*;

public class Q1 {
    class Solution {
        public String solution(String new_id) {
            new_id = new_id.toLowerCase()
                .replaceAll("[^a-z0-9-_\\.]", "")
                .replaceAll("([\\.]{2,})", ".")
                .replaceAll("^([\\.]?)(.+)$", "$2")
                .replaceAll("^(.*)([\\.])$", "$1");
    
            if (new_id.length() == 0) new_id = "aaa";
            else if (new_id.length() == 1) new_id = new_id + new_id + new_id;
            else if (new_id.length() == 2) new_id = new_id.concat(new_id.substring(1, 2));
            else if (new_id.length() >= 16) {
                new_id = new_id.substring(0, 15).replaceAll("^(.+)([\\.])$", "$1");
            }
    
            return new_id;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution("...!@BaT#*..y.abcdefghijklm"));
        solution.add(s.solution("z-+.^."));
        solution.add(s.solution("=.="));
        solution.add(s.solution("123_.def"));
        solution.add(s.solution("abcdefghijklmn.p"));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}