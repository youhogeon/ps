package com.youhogeon.kakao;

class Q5 {
	long[] cnt = new long[360000];

    public String solution(String play_time, String adv_time, String[] logs) {
		for (int i = 0; i < logs.length; i++){
			String[] s = logs[i].split("-");
			cnt[str2int(s[0])]++;
			cnt[str2int(s[1])]--;
		} //해당시간 시청자 증감

		for (int i = 1; i < 360000; i++) cnt[i] += cnt[i - 1]; //해당시간 실시간시청자수
		for (int i = 1; i < 360000; i++) cnt[i] += cnt[i - 1]; //해당시간 누적시청자수

		int play = str2int(play_time);
		int adv = str2int(adv_time);
		long max = -1;
		int maxTime = 0;
		for (int i = adv - 1; i <= play; i++){
			long begin = (i < adv)?0:cnt[i - adv];
			long end = cnt[i];
			if (end - begin > max){
				max = end - begin;
				maxTime = i - adv + 1;
			}
		}

        String answer = int2str(maxTime);
        return answer;
    }

	private int str2int(String s){
		String[] ss = s.split(":");

		return Integer.parseInt(ss[0]) * 3600 + Integer.parseInt(ss[1]) * 60 + Integer.parseInt(ss[2]);
	}

	private String int2str(int n){
		int h = n / 3600;
		int m = (n - h * 3600) / 60;
		int s = n - h * 3600 - m * 60;

		String H = String.valueOf(h);
		String M = String.valueOf(m);
		String S = String.valueOf(s);

		return (((H.length() == 1)?"0":"") + H) + ":" + (((M.length() == 1)?"0":"") + M) + ":" + (((S.length() == 1)?"0":"") + S);
	}
}