package com.youhogeon.kakao.blind2021;

import java.util.*;

public class Q4 {
    class Solution {
        public int[] solution(String[] info, String[] query) {
            Map<String, List<Integer>> map = new HashMap<>();
    
    
            for (int i = 0; i < info.length; i++) {
                String[] strs = info[i].split(" ");
    
                for (int j = 0; j < 16; j++) {
                    StringBuilder sb = new StringBuilder();
                    
                    sb.append(((j & 1) == 1) ? strs[0] : '-');
                    sb.append(" and ");
                    
                    sb.append(((j & 2) == 2) ? strs[1] : '-');
                    sb.append(" and ");
                    
                    sb.append(((j & 4) == 4) ? strs[2] : '-');
                    sb.append(" and ");
                    
                    sb.append(((j & 8) == 8) ? strs[3] : '-');
    
                    String s = sb.toString();
                    List<Integer> list = map.getOrDefault(s, new ArrayList<>());
                    list.add(Integer.parseInt(strs[4]));
    
                    if (list.size() == 1) map.put(s, list);
                }
            }
    
            for (String key : map.keySet()) Collections.sort(map.get(key));
            
            int[] answer = new int[query.length];
    
            for (int i = 0; i < query.length; i++) {
                String[] tmp = query[i].replaceAll("^([a-z-\\s]+)\\s([0-9]+)$", "$1@$2").split("@");
                String key = tmp[0];
                int cutLine = Integer.parseInt(tmp[1]);
    
                List<Integer> list = map.getOrDefault(key, null);
                if (list == null) answer[i] = 0;
                else {
                    // answer[i] = list.size();
                    // for(int j : list) {
                    //     if (j < cutLine) {
                    //         answer[i]--;
                    //     }else break;
                    // }
    
                    int bs = Collections.binarySearch(list, cutLine);
                    if (bs < 0) bs = bs * -1 - 1;
                    
                    while (bs > 0 && list.get(bs - 1) >= cutLine) bs--;
                    answer[i] = list.size() - bs;
                }
            }
    
            return answer;
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"}, new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}