package com.youhogeon.kakao.blind2018.test3;

import java.util.*;

public class Q3 {
    class Solution {
        public String[] solution(String[] files) {
            Queue<File> queue = new PriorityQueue<>();
            
            for (int i = 0; i < files.length; i++) queue.add(new File(files[i], i));
            
            String[] answer = new String[files.length];
            int i = 0;
            while (!queue.isEmpty()) answer[i++] = queue.poll().fileName;
    
            return answer;
        }
    
        class File implements Comparable<File> {
    
            String head, tail, fileName;
            int number, idx;
    
            public File(String str, int idx) {
                int tailIdx = str.length(), numberIdx = 0;
                boolean foundNum = false;
                char[] chars = str.toCharArray();
    
                for (int i = 0; i < chars.length; i++) {
                    boolean isNum = chars[i] >= '0' && chars[i] <= '9';
                    
                    if (isNum && !foundNum) {
                        foundNum = true;
                        numberIdx = i;
                    }else if (foundNum && !isNum) {
                        tailIdx = i;
                        break;
                    }
                }
    
                head = str.substring(0, numberIdx).toUpperCase();
                number = Integer.parseInt(str.substring(numberIdx, tailIdx));
                tail = str.substring(tailIdx, str.length());
                fileName = str;
                this.idx = idx;
            }
    
            @Override
            public int compareTo(File o) {
                if (this.head.compareTo(o.head) > 0) return 1;
                if (this.head.compareTo(o.head) < 0) return -1;
    
                if (this.number > o.number) return 1;
                if (this.number < o.number) return -1;
    
                if (this.idx > o.idx) return 1;
                if (this.idx < o.idx) return -1;
    
                return 0;
            }
            
        }
    }
    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"}));
        solution.add(s.solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}