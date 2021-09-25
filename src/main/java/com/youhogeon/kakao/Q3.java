package com.youhogeon.kakao;

import java.util.*;

class Q3 {
    public int[] solution(String[] info, String[] query) {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		Map<String, Boolean> isSorted = new HashMap<String, Boolean>();

		for (int i = 0; i < info.length; i++){
			String[] arr = info[i].split(" ");
			int score = Integer.parseInt(arr[4]);

			for (int j = 0; j < 16; j++){
				String key = makeKey(arr, j);
				if (!map.containsKey(key)) map.put(key, new ArrayList<Integer>());

				map.get(key).add(score);
			}
		}

        int[] answer = new int[query.length];

		for (int i = 0; i < query.length; i++){
			String q = query[i].replaceAll(" and ", "");
			String[] qq = q.split(" ");

			if (!map.containsKey(qq[0])) continue;
			
			if (!isSorted.containsKey(qq[0])){
				isSorted.put(qq[0], true);
				map.get(qq[0]).sort(Comparator.naturalOrder());
			}

			List<Integer> list = map.get(qq[0]);
			answer[i] = list.size() - findIdx(list, Integer.parseInt(qq[1]));
		}

        return answer;
    }

	private String makeKey(String[] info, int n){
		String result = "";

		for (int i = 0; i < 4; i++) result += (((n >> i & 1) == 1)?info[i]:"-");

		return result;
	}

	private int findIdx(List<Integer> list, int n){
		int left = 0;
		int right = list.size() - 1;
		
		while (left <= right){
			int mid = (left + right) / 2;

			if (list.get(mid) >= n) right = mid - 1;
			else left = mid + 1;
		}

		return left;
	}
}