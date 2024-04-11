package code.programmers.study;

import java.util.*;

class Lesson72411 {

    static HashMap<String, Integer> temp = new HashMap<>();
    static List<String> tempList = new ArrayList<>();
    static int max = 2;

    static String[] solution(String[] orders, int[] course) {
        List<String> answerList = new ArrayList<>();

        List<String[]> sortedOrdersList = new ArrayList<>();
        for (String order : orders) {
            String[] temp = order.split("");
            Arrays.sort(temp);
            sortedOrdersList.add(temp);
        }
        
        // 정렬 확인
        for (String[] str : sortedOrdersList) {
            System.out.println(Arrays.toString(str));
        }

        for (int count : course) {
            corseResult(sortedOrdersList, count);
            System.out.println("=== \n " + temp.toString());
            for (String key : temp.keySet()) {
                if (temp.get(key) == max) {
                    answerList.add(key);
                }
            }
            temp.clear();
            max = 2;
        }

        Collections.sort(answerList);

        return answerList.toArray(new String[answerList.size()]);
    }

    static HashMap<String, Integer> corseResult(List<String[]> list, int corseMenuCount) {
        HashMap<String, Integer> result = new HashMap<>();
        for(String[] menu : list) {
            boolean[] visitied = new boolean[menu.length];
            combi(menu, visitied, 0, corseMenuCount);
        }

        return result;
    }

    static void combi(String[] str, boolean[] visited, int start, int r) {
        if(r == 0) {
            tempAdd(str, visited);
            return;
        }
        for(int i = start ; i < str.length ; i ++) {
            visited[i] = true;
            combi(str, visited, i+1, r-1);
            visited[i] = false;
        }

    }

    static void tempAdd(String[] str, boolean[] visited) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < visited.length ; i ++) {
            if (visited[i]) sb.append(str[i]);
        }
        temp.put(sb.toString(), temp.getOrDefault(sb.toString(), 0) + 1);
        Integer value = temp.get(sb.toString());
        if (value > max) {
            max = value;
        }
    }

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2,3,4}; // answer : {"AC", "ACDE", "BCFG", "CDE"}

        //String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        //int[] course = {2,3,5}; // answer : {"AC", "ACDE", "BCFG", "CDE"}

        //String[] orders = {"XYZ", "XWY", "WXA"};
        //int[] course = {2,3,4}; // answer : {"AC", "ACDE", "BCFG", "CDE"}

        System.out.println("===START===");
        String[] print = solution(orders, course);
        System.out.println(Arrays.toString(print));
        //System.out.println(solution(orders, course));
        System.out.println("===END===");
    }
}
