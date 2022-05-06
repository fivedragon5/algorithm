package algorithm.programmers.easy;

import java.util.ArrayList;
import java.util.List;

class Lesson60057 {
    static int solution(String s) {
        int answer = s.length();
        for(int i = 1 ; i <= s.length()/2 ; i++) {
            List<String> tempList = new ArrayList<>();
            for(int j = 0 ; j < Math.ceil(s.length()/(double) i) ; j++) {
                if(j == (int) Math.ceil(s.length()/i)) {
                    if(j*i == s.length()) break;
                    tempList.add(s.substring(j*i, s.length()));
                }
                else  tempList.add(s.substring(j*i, j*i+i));
            }
            int stack = 1;
            String c = "";
            for(int k = 0 ; k < tempList.size()-1 ; k++) {
                if(tempList.get(k).equals(tempList.get(k+1))) {
                    stack++;
                }
                else {
                    if(stack > 1) c += stack + tempList.get(k);
                    else c += tempList.get(k);
                    stack = 1;
                }
                if(k == (tempList.size()-2)) {
                    if(stack > 1) c += stack + tempList.get(k+1);
                    else c += tempList.get(k+1);
                }
            }
            if(answer > c.length()) answer = c.length();
        }
        return answer;
    }

    static int solution2(String s) {
        int answer = s.length();
        for(int i = 1 ; i <= s.length()/2 ; i++) {
            List<String> tempList = new ArrayList<>();
            //length : 13
            // 1 : 13
            // 2 : 7
            // 3 : 5
//            for(int j = 0; j <  ; j++) {
//                System.out.println(s.substring(i*j,i*j+i));
//            }
        }
        return answer;
    }

    public static void main(String[] args) {

        String a = "xxxxxxxxxxyyy";
        String b = "abcabcdede";

        //2
        System.out.println("===START===시작s가낟랜다래날나하이하이 ㄴㅁㅇㄴㅁㅇ");
        System.out.println(solution2(a));
        System.out.println("===END===");
    }
}
