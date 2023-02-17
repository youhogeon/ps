package com.youhogeon.naverfinancial.intern2023;

import java.util.*;
public class Q3 {

    class Solution {
        int day = 0;
    
        public int solution(int[][] games) {
            int sum = 0;
            List<Game> gamesQ = new ArrayList<>();
    
            int max = -1;
            for (int i = 0; i < games.length; i++) {
                Game g = new Game(i, games[i][0], games[i][1], games[i][2]);
                gamesQ.add(g);
                max = Math.max(max, g.saleDay);
            }
    
            Collections.sort(gamesQ, new Comparator<Game>() {
                @Override
                public int compare(Game o1, Game o2) {
                    if (o1.salePrice > o2.salePrice) return 1;
                    if (o1.salePrice < o2.salePrice) return -1;
    
                    return 0;
                }
            });
    
            for (int i = 0; i < games.length - max - 1; i++) {
                sum += gamesQ.get(0).price - gamesQ.get(0).salePrice;
                gamesQ.remove(0);
            }
    
            for (day = 0; day < games.length; day++) {
                if (gamesQ.isEmpty()) break;
    
                Collections.sort(gamesQ);
    
                if (gamesQ.get(0).saleDay == day) {
                    sum += gamesQ.get(0).price - gamesQ.get(0).salePrice;
                    gamesQ.remove(0);
                } else {
                    Collections.sort(gamesQ, new Comparator<Game>() {
                        @Override
                        public int compare(Game o1, Game o2) {
                            if (o1.salePrice > o2.salePrice) return 1;
                            if (o1.salePrice < o2.salePrice) return -1;
    
                            return 0;
                        }
                    });
    
                    sum += gamesQ.get(0).price;
                    gamesQ.remove(0);
                }
            }
    
            return sum;
        }

        class Game implements Comparable<Game> {
            int price;
            int saleDay;
            int salePrice;
            int id;
    
            public Game(int id, int price, int saleDay, int rate) {
                this.id = id;
                this.price = price;
                this.saleDay = saleDay;
                this.salePrice = price * rate / 100;
            }
    
            @Override
            public int compareTo(Game o) {
                if (o.saleDay < this.saleDay) return 1;
                if (o.saleDay > this.saleDay) return -1;
    
                return 0;
            }
        }
    }
    

    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(new int[][]{new int[]{66000, 0, 50}, new int[]{16000, 2, 10}, new int[]{84500, 3, 20}, new int[]{5500, 2, 75}}));
        System.out.println(s.solution(new int[][]{new int[]{100, 0, 50}, new int[]{1000, 0, 50}, new int[]{10000, 0, 50}}));
        System.out.println(s.solution(new int[][]{new int[]{100, 2, 50}, new int[]{100, 2, 50}, new int[]{1000, 4, 50}, new int[]{1000, 4, 50}, new int[]{1000, 4, 50}}));
        System.out.println(s.solution(new int[][]{new int[]{100, 0, 50}, new int[]{100, 0, 50}, new int[]{100, 0, 50}, new int[]{10000, 3, 50}}));

    }
}
