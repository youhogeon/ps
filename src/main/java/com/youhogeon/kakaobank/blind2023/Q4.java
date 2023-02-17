package com.youhogeon.kakaobank.blind2023;

import java.util.*;

public class Q4 {

    class Solution {
        int currentCategory = -1;
    
        public int[] solution(int[][] jobs) {
            Category[] categoriesID = new Category[100];
            Category[] categories = new Category[100];
            for (int i = 0; i < 100; i++) {
                categories[i] = new Category(i);
                categoriesID[i] = categories[i];
            }
            
            int time = 0;
            List<Integer> logs = new ArrayList<>();
    
            int jobIdx = 0;
            while (true) {
                for (int i = jobIdx; i < jobs.length; i++) {
                    if (jobs[i][0] > time) break;
    
                    categoriesID[jobs[i][2]].timeSum += jobs[i][1];
                    categoriesID[jobs[i][2]].prioritySum += jobs[i][3];
                    jobIdx++;
                }
    
                Arrays.sort(categories);
                Category c = categories[0];
    
                if (c.timeSum == 0) {
                    if (jobIdx == jobs.length) break;
                    else {
                        time++;
                        continue;
                    }
                }
    
                addLog(logs, c.id);
                currentCategory = c.id;
                time += c.timeSum;
                c.timeSum = 0;
                c.prioritySum = 0;
            }
    
            int[] answer = new int[logs.size()];
            for (int i = 0; i < answer.length; i++) answer[i] = logs.get(i);
            return answer;
        }
    
        void addLog(List<Integer> logs, int id) {
            if (logs.size() == 0 || logs.get(logs.size() - 1) != id) logs.add(id);
        }
    
        class Category implements Comparable<Category>{
            int id = 0;
            int timeSum = 0;
            int prioritySum = 0;
    
            public Category(int id) {
                this.id = id;
            }
    
            @Override
            public int compareTo(Category o) {
                if (this.timeSum == 0 && o.timeSum > 0) return 1;
                if (this.timeSum > 0 && o.timeSum == 0) return -1;
    
                if (this.id == currentCategory) return -1;
                if (o.id == currentCategory) return 1;
    
                if (this.prioritySum > o.prioritySum) return -1;
                if (this.prioritySum < o.prioritySum) return 1;
                
                if (this.id < o.id) return -1;
                if (this.id > o.id) return 1;
    
                return 0;
            }
        }
    }
    
    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        //solution.add(s.solution());
        //solution.add(s.solution(new int[][]{new int[]{1, 1, 1, 1}, new int[]{3, 1, 1, 1}, new int[]{5, 1, 1, 1}, new int[]{7, 1, 2, 1}, new int[]{9, 1, 3, 1}})); //1 2 3 
        solution.add(s.solution(new int[][]{new int[]{1, 5, 2, 3}, new int[]{2, 2, 3, 2}, new int[]{3, 1, 3, 3}, new int[]{5, 2, 1, 5}, new int[]{7, 1, 1, 1}, new int[]{9, 1, 1, 1}, new int[]{10, 2, 2, 9}})); //2 1 2 3
        solution.add(s.solution(new int[][]{new int[]{1, 2, 1, 5}, new int[]{2, 1, 2, 100}, new int[]{3, 2, 1, 5}, new int[]{5, 2, 1, 5}})); //1 2
        solution.add(s.solution(new int[][]{new int[]{0, 2, 3, 1}, new int[]{5, 3, 3, 1}, new int[]{10, 2, 4, 1}})); //3 4
        solution.add(s.solution(new int[][]{new int[]{0, 5, 1, 1}, new int[]{2, 4, 3, 3}, new int[]{3, 4, 4, 5}, new int[]{5, 2, 3, 2}})); //1 3 4

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}
