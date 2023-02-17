package com.youhogeon.kakao.blind2023;

import java.util.*;

class Solution {
    int[] calc(int r, int c, String shortWay, int idx) {
        for (int i = 0; i < idx; i++) {
            char ch = shortWay.charAt(i);

            if (ch == '1') r++;
            else if (ch == '2') c--;
            else if (ch == '3') c++;
            else r--;
        }

        return new int[]{r, c};
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dr = r - x, dc = c - y;
        String shortWay = "";

        if (dr > 0) shortWay = shortWay += "1".repeat(dr);
        if (dc < 0) shortWay = shortWay += "2".repeat(-dc);
        if (dc > 0) shortWay = shortWay += "3".repeat(dc);
        if (dr < 0) shortWay = shortWay += "4".repeat(-dr);
        int left = k - shortWay.length();

        
        if (left < 0 || left % 2 == 1) return "impossible";
        left /= 2;


        int idx = shortWay.lastIndexOf("1") + 1;
        if (idx == 0) idx = 0;
        
        int[] calc = calc(x, y, shortWay, idx);
        int cnt = Math.min(n - calc[0], left);
        cnt = Math.max(0, cnt);
        
        shortWay = "1".repeat(cnt) + shortWay + "4".repeat(cnt);
        left -= cnt;
        //맨앞에 1 맨뒤에 4 최대한 추가

        




        idx = shortWay.indexOf("3");
        if (idx == -1) idx = shortWay.indexOf("4");
        if (idx == -1) idx = shortWay.length();
        
        calc = calc(x, y, shortWay, idx);
        cnt = Math.min(calc[1] - 1, left);
        cnt = Math.max(0, cnt);

        String a = shortWay.substring(0, idx), b = shortWay.substring(idx, shortWay.length());
        shortWay = a + "2".repeat(cnt) + "3".repeat(cnt) + b;
        left -= cnt;
        //처음나오는 3 앞에 22223333추가








        idx = shortWay.lastIndexOf("1") + 1;
        if (idx == 0) idx = 0;
        
        calc = calc(x, y, shortWay, idx);

        if (calc[0] < n) {
            a = shortWay.substring(0, idx);
            b = shortWay.substring(idx, shortWay.length());
            shortWay = a + "14".repeat(left) + b;
            left = 0;
        } 
        //14진동 추가 마지막 1 뒤에





        idx = shortWay.lastIndexOf("2") + 1;
        if (idx == 0) idx = shortWay.lastIndexOf("1") + 1;
        if (idx == 0) idx = 0;
        
        
        calc = calc(x, y, shortWay, idx);

        if (calc[1] > 1) {
            a = shortWay.substring(0, idx);
            b = shortWay.substring(idx, shortWay.length());
            shortWay = a + "23".repeat(left) + b;
            left = 0;
        }
        //23진동 추가 마지막 2 뒤에





        idx = shortWay.lastIndexOf("2") + 1;
        if (idx == 0) idx = shortWay.lastIndexOf("1") + 1;
        if (idx == 0) idx = 0;

        calc = calc(x, y, shortWay, idx);

        if (calc[1] < m) {
            a = shortWay.substring(0, idx);
            b = shortWay.substring(idx, shortWay.length());
            shortWay = a + "32".repeat(left) + b;
            left = 0;
        }
        //32진동 추가 마지막 2 뒤에

        
        idx = shortWay.indexOf("4");
        if (idx == -1) idx = shortWay.length();

        calc = calc(x, y, shortWay, idx);

        if (calc[0] > 1) {
            a = shortWay.substring(0, idx);
            b = shortWay.substring(idx, shortWay.length());
            shortWay = a + "41".repeat(left) + b;
            left = 0;
        }
        //41진동 추가 처음나오는 4 앞에

        return shortWay.replaceAll("1", "d").replaceAll("2", "l").replaceAll("3", "r").replaceAll("4", "u");
    }
}

public class Q6 {

    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(4	,	4	,	4	,	1	,	2	,	3, 1)); //
        solution.add(s.solution(4	,	4	,	4	,	4	,	2	,	2, 10)); //2223232344
        solution.add(s.solution(4	,	4	,	1	,	1	,	3	,	3, 10)); //1113232334
        solution.add(s.solution(4	,	4	,	1	,	4	,	3	,	2, 10)); //1112223234
        solution.add(s.solution(3	,	4	,	2	,	3	,	3	,	1, 5));
        solution.add(s.solution(2	,	2	,	1	,	1	,	2	,	2, 2));
        solution.add(s.solution(3	,	3	,	1	,	2	,	3	,	3, 4));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
