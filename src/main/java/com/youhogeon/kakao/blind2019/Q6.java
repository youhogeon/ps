package com.youhogeon.kakao.blind2019;

import java.util.*;
import java.util.regex.*;

public class Q6 {
    class Solution {
        String word;
    
        public int solution(String word, String[] pages) {
            Map<String, Site> sites = new HashMap<>();
            List<Site> sitesList = new ArrayList<>();
            this.word = word.toLowerCase();
    
            for (String p : pages) {
                Site s = new Site(p);
                sitesList.add(s);
                sites.put(s.url, s);
            }
    
            for (String url : sites.keySet()) {
                for (String s : sites.get(url).links) {
                    if (sites.containsKey(s)) sites.get(s).linkScore += (double)sites.get(url).basicScore / (double)sites.get(url).links.size();
                }
            }
    
            double max = -1;
            int maxId = -1;
            for (int i = 0; i < sitesList.size(); i++) {
                double score = sitesList.get(i).basicScore + sitesList.get(i).linkScore;
        
                if (score > max) {
                    max = score;
                    maxId = i;
                }
            }
    
            return maxId;
        }
    
        class Site {
            String html, url;
            int basicScore;
            Set<String> links = new HashSet<>();
            double linkScore = 0;
    
            public Site(String html){
                this.html = html;
    
                calcScore();
                calcLinks();
                calcURL();
            }
    
            void calcScore() {
                String str = html.toLowerCase().replaceAll("([^a-z])", "◐◐");
                String str2 = str.replaceAll("◐" + word + "◐", word);
    
                this.basicScore = (str.length() - str2.length()) / 2;
            }
    
            void calcLinks() {
                Pattern p = Pattern.compile("<meta property=\"og:url\" content=\"(https://([^\"]*))\"");
                Matcher m = p.matcher(html);
    
                while (m.find()) {
                    url = m.group(1);
                }
            }
    
            void calcURL() {
                Pattern p = Pattern.compile("<a href=\"(https://([^\"]*))\">");
                Matcher m = p.matcher(html);
    
                while (m.find()) {
                    links.add(m.group(1));
                }
            }
        }
    }

    public void solve() {
        Solution s = new Solution();

        List<Object> solution = new ArrayList<>();

        solution.add(s.solution("blind", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"}));
        solution.add(s.solution("Muzi", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}));

        for (Object o : solution) {
            System.out.println(o.toString());
        }
    }
}