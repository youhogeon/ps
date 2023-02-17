package com.youhogeon.kakao.blind2023;

import java.util.*;

public class Q5 {
    
class Solution {
    Cell[][] table = new Cell[50][50];

    public String[] solution(String[] commands) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                table[i][j] = new Cell(i, j);
            }
        }

        List<String> answer = new ArrayList<>();

        for (int i = 0; i < commands.length; i++) {
            String[] strs = commands[i].split(" ");
            char command = strs[0].charAt(1);

            if (command == 'P' && strs.length == 4) { //update r c value
                int r = Integer.parseInt(strs[1]) - 1, c = Integer.parseInt(strs[2]) - 1;
                String value = strs[3];

                table[r][c].value = value;
            } else if (command == 'P') { //update v1 v2
                String v1 = strs[1], v2 = strs[2];

                for (int k = 0; k < 50; k++) {
                    for (int j = 0; j < 50; j++) {
                        if (table[k][j].value.equals(v1)) table[k][j].value = v2;
                    }
                }
            } else if (command == 'E') { //merge r1 c1 r2 c2
                int r1 = Integer.parseInt(strs[1]) - 1, c1 = Integer.parseInt(strs[2]) - 1;
                int r2 = Integer.parseInt(strs[3]) - 1, c2 = Integer.parseInt(strs[4]) - 1;

                if (table[r1][c1].value.length() == 0) table[r1][c1].value = table[r2][c2].value;
                table[r1][c1].merged.addAll(table[r2][c2].merged);

                for (Coor coor : table[r1][c1].merged) {
                    table[coor.r][coor.c] = table[r1][c1];
                }
            } else if (command == 'N') { //unmerge r c
                int r = Integer.parseInt(strs[1]) - 1, c = Integer.parseInt(strs[2]) - 1;

                String value = table[r][c].value;

                for (Coor coor : table[r][c].merged) {
                    table[coor.r][coor.c] = new Cell(coor.r, coor.c);
                }

                table[r][c].value = value;
            } else { //print r c
                int r = Integer.parseInt(strs[1]) - 1, c = Integer.parseInt(strs[2]) - 1;

                answer.add((table[r][c].value.length() == 0) ? "EMPTY" : table[r][c].value);
            }
        }

        return answer.toArray(new String[answer.size()]);
    }

    class Cell {
        String value = "";
        Set<Coor> merged;

        public Cell(int r, int c) {
            merged = new HashSet<>();
            merged.add(new Coor(r, c));
        }
    }

    class Coor {
        int r, c;

        public Coor(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}


    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        solution.add(s.solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"}));
        solution.add(s.solution(new String[]{"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
