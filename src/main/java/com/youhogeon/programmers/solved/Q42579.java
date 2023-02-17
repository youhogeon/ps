package com.youhogeon.programmers.solved;

import java.util.*;

public class Q42579 {

    class Solution {
        public int[] solution(String[] genres, int[] plays) {
            Map<String, Genre> genreMap = new HashMap<>();
    
            for (int i = 0; i < genres.length; i++) {
                Genre genre = genreMap.getOrDefault(genres[i], new Genre());
    
                genre.count += plays[i];
                genre.queue.add(new Music(i, plays[i]));
    
                genreMap.put(genres[i], genre);
            }
    
            List<Genre> genreList = new ArrayList<>(genreMap.values());
            Collections.sort(genreList);
    
            List<Integer> answer = new ArrayList<>();
            for (Genre genre : genreList) {
                answer.add(genre.queue.poll().id);
                
                if (genre.queue.isEmpty()) continue;
                answer.add(genre.queue.poll().id);
            }
    
            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    
        class Genre implements Comparable<Genre> {
            int count = 0;
            Queue<Music> queue = new PriorityQueue<>();
    
            @Override
            public int compareTo(Genre o) {
                if (o.count > this.count) return 1;
                if (o.count < this.count) return -1;
    
                return 0;
            }
        }
    
        class Music implements Comparable<Music>{
            int count;
            int id;
    
            public Music(int id, int count) {
                this.id = id;
                this.count = count;
            }
    
            @Override
            public int compareTo(Music o) {
                if (o.count > this.count) return 1;
                if (o.count < this.count) return -1;
    
                if (o.id < this.id) return 1;
                if (o.id > this.id) return -1;
    
                return 0;
            }
        }
    }
    

    public void solve() {
        Solution s = new Solution();

        System.out.println(s.solution(new String[]{"classic", "pop", "classic", "classic", "pop", "test"}, new int[]{500, 600, 150, 800, 2500, 10000}));
    }
}
