package com.youhogeon.kakao;

import java.util.*;

class Q2 {
    public String[] solution(String[] orders, int[] course) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Map<Integer, Integer> max = new HashMap<Integer, Integer>();

		for (int i = 0; i < orders.length; i++){
			String menu = orders[i];

			for (int j = 1; j < Math.pow(2, menu.length()); j++){
				String subMenu = getSub(menu, j);
				int len = subMenu.length();
				if (len == 1) continue;

				int subMenuInt = str2int(subMenu);

				if (!map.containsKey(subMenuInt)) map.put(subMenuInt, 0);
				if (!max.containsKey(len)) max.put(len, -1);

				map.put(subMenuInt, map.get(subMenuInt) + 1);
				max.put(len, Math.max(max.get(len), map.get(subMenuInt)));
			}
		}

		List<String> result = new ArrayList<String>();
		
		for (int i = 0; i < course.length; i++){
			if (!max.containsKey(course[i])) continue;

			int localMax = max.get(course[i]);
			if (localMax < 2) continue;

			for (Integer k : map.keySet()){
				if (map.get(k) == localMax && int2str(k).length() == course[i]) result.add(int2str(k));
			}
		}

		result.sort(Comparator.naturalOrder());


        String[] answer = result.toArray(new String[result.size()]);
        return answer;
    }

	private int str2int(String str){
		int menu = 0;

		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length; i++) menu += Math.pow(2, arr[i] - 'A');

		return menu;
	}

	private String int2str(int n){
		String s = "";
		for (int i = 0; i < 26; i++){
			if ((n >> i & 1) == 1) s = s + (char)('A' + i);
		}

		return s;
	}

	private String getSub(String str, int n){
		String result = "";

		for (int i = 0; i < str.length(); i++){
			if ((n >> i & 1) == 1) result = result + str.charAt(i);
		}

		return result;
	}
}