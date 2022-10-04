package com.youhogeon.kakao.blind2023;

import java.util.*;

public class Q1 {
    class Solution {
        public int[] solution(String today, String[] terms, String[] privacies) {
            int[] termDates = new int[26];
            Date todayDate = new Date(today);
    
            for (int i = 0; i < terms.length; i++) {
                String[] strs = terms[i].split(" ");
                termDates[terms[i].charAt(0) - 'A'] = Integer.parseInt(strs[1]);
            }
    
            List<Integer> result = new ArrayList<>();
    
            for (int i = 0; i < privacies.length; i++) {
                String[] strs = privacies[i].split(" ");
                Date d = new Date(strs[0]);
                int term = termDates[strs[1].charAt(0) - 'A'];
    
                d.addMonth(term);
    
                if (todayDate.id >= d.id) result.add(i + 1);
            }
    
            return result.stream().mapToInt(i -> i).toArray();
        }
    
        class Date {
            int year, month, day;
            int id;
    
            public Date(String str) {
                String[] strs = str.split("\\.");
    
                year = Integer.parseInt(strs[0]);
                month = Integer.parseInt(strs[1]);
                day = Integer.parseInt(strs[2]);
    
                calcid();
            }
    
            void calcid() {
                id = year * 10000 + month * 100 + day;
            }
    
            void addMonth(int i) {
                month += i;
                
                month--;
                year += month / 12;
                month %= 12;
                month++;
    
                calcid();
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution("2022.05.19"	,	new String[]{"A 6", "B 12", "C 3"}	,	new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"}));
        solution.add(s.solution("2020.01.01"	,	new String[]{"Z 3", "D 5"}	,	new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
