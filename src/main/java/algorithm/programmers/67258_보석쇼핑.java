package algorithm.programmers;

import java.util.HashMap;
import java.util.HashSet;

class Lesson67258 {
    static int[] solution(String[] gems) {
        int[] answer = new int[2];
        int min = gems.length;
        HashMap<String, Integer> gemsList = new HashMap<>(); //보석 종류

        for(String gem : gems)
            gemsList.put(gem, gemsList.getOrDefault(gem, 1));

        int gemCount = gemsList.size(); //보석 종류 갯수

        for(int i = 0 ; i <= gems.length - gemCount ; i ++) {
            HashMap<String, Integer> tempList = new HashMap<>(); //보석 종류
            for(int j = i ; j < gems.length ; j ++) {
                tempList.put(gems[j], tempList.getOrDefault(gems[j], 1));
                if(tempList.size() >= gemCount) {
                    //System.out.println((i+1) + ":" + (j+1));
                    int[] result = {i+1, j+1};
                    if(min > (result[1] - result[0])) {
                        answer[0] = result[0];
                        answer[1] = result[1];
                        min = result[1] - result[0];
                    }
                    break;
                }
            }
        }
        //System.out.println("[" + answer[0] + "," + answer[1] + "]");
        return answer;
    }

    static int[] solution2(String[] gems) {
        int[] answer = new int[2];
        int min = gems.length;
        HashSet<String> gemList = new HashSet<>();
        HashMap<String, Integer> gemsList = new HashMap<>(); //보석 종류
        HashSet<String> set = new HashSet<>();

        for(String gem : gems)
            gemList.add(gem);

        int gemCount = gemsList.size(); //보석 종류 갯수

        for(int i = 0 ; i <= gems.length - gemCount ; i ++) {
            for(int j = i ; j < gems.length ; j ++) {
                set.add(gems[j]);
                if(set.size() >= gemCount) {
                    int[] result = {i+1, j+1};
                    if(min > (result[1] - result[0])) {
                        answer[0] = result[0];
                        answer[1] = result[1];
                        min = result[1] - result[0];
                    }
                    set.clear();
                    break;
                }
                else if(min <= (j - i)) break; // 최소거리보다 커지는경우 멈춤
            }
        }
        return answer;
    }

    static int[] solution3(String[] gems) {
        int[] answer = new int[2];
        int min = gems.length;
        HashSet<String> gemList = new HashSet<>();
        HashMap<String, Integer> temp = new HashMap<>(); //보석 종류

        for(String gem : gems)
            gemList.add(gem);

        int gemCount = gemList.size(); //보석 종류 갯수
        
        //초기값 세팅
        int start = 0;
        int end = -1;
        answer[0] = 1;
        answer[1] = gems.length;
        temp.put(gems[0],0);

        if(gemCount == 1) {
            answer[0] = 1;
            answer[1] = 1;
            return answer;
        }

        while(true) {

            if(start >= gems.length - gemCount) break;

            if(gemCount > temp.size()) {
                end++;
                if(end >= gems.length) break;
                temp.put(gems[end], temp.getOrDefault(gems[end], 0) + 1);
            }
            else {
                temp.put(gems[start], temp.getOrDefault(gems[start], 0) - 1);
                if(temp.get(gems[start]) == 0) temp.remove(gems[start]);
                start++;
            }

            //System.out.println("start : " + start + " end :" + end + "");
            //System.out.println(temp.toString());

            if(temp.size() >= gemCount) {
                System.out.println((start+1) + "////" + (end+1));
                if(min > end-start) {
                    min = end-start;
                    answer[0] = start+1;
                    answer[1] = end+1;
                }
            }
        }

        //System.out.println(answer[0] + "/" + answer[1]);

        return answer;
    }



    public static void main(String[] args) {
        //String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        String[] gems = {"AA", "AB", "AC", "AA", "AC"};
        //String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};

        System.out.println("===START1===");
        long start = System.currentTimeMillis();
        System.out.println(solution3(gems));
        long end = System.currentTimeMillis();
        System.out.println("===END1===" + (end - start));

//        System.out.println("===START1===");
//        long start2 = System.currentTimeMillis();
//        System.out.println(solution2(gems));
//        long end2 = System.currentTimeMillis();
//        System.out.println("===END1===" + (end2 - start2));

    }
}

