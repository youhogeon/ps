package com.youhogeon.kakao.blind2018.test3;

import java.util.*;

public class Q4 {
    class Solution {
        public String solution(String m, String[] musicinfos) {
            Queue<Music> found = new PriorityQueue<>();
    
            String target = util.replace(m);
    
            for (int i = 0; i < musicinfos.length; i++) {
                Music music = new Music(i, musicinfos[i]);
    
                if (music.melody.indexOf(target) >= 0) found.add(music);
            }
    
            if (found.isEmpty()) return "(None)";
    
            return found.poll().name;
        }
    
        class Music implements Comparable<Music> {
            String name, melody = "";
            int duration, idx;
    
            public Music(int idx, String infoStr) {
                String[] info = infoStr.split(",");
    
                this.idx = idx;
                duration = util.time2int(info[1]) - util.time2int(info[0]);
                name = info[2];
                String patialMelody = util.replace(info[3]);
    
                int len = patialMelody.length();
                while (melody.length() != duration) {
                    if (duration - melody.length() >= len) melody += patialMelody;
                    else {
                        melody += patialMelody.substring(0, duration - melody.length());
                    }
                }
            }
    
            @Override
            public int compareTo(Music o) {
                if (o.duration > this.duration) return 1;
                if (o.duration < this.duration) return -1;
    
                if (o.idx < this.idx) return 1;
                if (o.idx > this.idx) return -1;
    
                return 0;
            }
        }
    }
    
    class util {
        static String replace(String meoldy) {
            return meoldy.replaceAll("A#", "0")
                .replaceAll("C#", "1")
                .replaceAll("D#", "2")
                .replaceAll("F#", "3")
                .replaceAll("G#", "4");
        }
    
        public static int time2int(String string) {
            String[] str = string.split(":");
    
            return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        solution.add(s.solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
        solution.add(s.solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}