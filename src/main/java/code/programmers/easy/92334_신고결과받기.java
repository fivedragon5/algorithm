package code.programmers.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

class Lesson92334 {
    static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, Integer> reportTable = new LinkedHashMap<>(); // 신고테이블
        HashMap<String, HashSet<String>> reportLog = new HashMap<>(); // 신고로그

        for (String id : id_list) reportTable.put(id ,0);

        for (String id : report) {
            String[] log = id.split(" ");
            HashSet<String> tempSet = reportLog.getOrDefault(log[1], new HashSet<>());
            tempSet.add(log[0]);
            reportLog.put(log[1], tempSet);
        }

        for (String key : reportLog.keySet()) {
            if(k <= reportLog.get(key).size()) {
                for(String id : reportLog.get(key)) {
                    reportTable.put(id, reportTable.get(id) + 1);
                }
            }
        }

        int[] a = reportTable.values().stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(a));
        int index = 0;
        for (String id : reportTable.keySet())
            answer[index++] = reportTable.get(id);

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2; // 2 1 1 0
        System.out.println("===START===");
        System.out.println(solution(id_list, report, k));
        System.out.println("===END===");
    }
}
