package algorithm.programmers;

import java.util.*;

class Lesson42889 {
    static int[] solution(int N, int[] stages) {
        HashMap<Integer, Integer> stageStayPlayer = new HashMap<>();
        HashMap<Integer, Float>  failRateMap = new HashMap<>(); //들어온대로 저장
        int participation = stages.length;

        for(int i = 1 ; i <= N ; i++) {
            stageStayPlayer.put(i, 0);
            failRateMap.put(i, (float) 0);
        }

        for(int stage : stages)
            stageStayPlayer.put(stage, stageStayPlayer.getOrDefault(stage, 0) + 1);

        for(int stage : failRateMap.keySet()) {
            if(stageStayPlayer.get(stage) != 0) {
                failRateMap.put(stage, (float) stageStayPlayer.get(stage) / participation);
                participation -= stageStayPlayer.get(stage);
            }
        }

        List<Integer> sortMap = sortMapByValue(failRateMap);
        int[] arr = sortMap.stream().mapToInt(i -> i).toArray();

        System.out.println(Arrays.toString(arr));
        return arr;
    }

    static List<Integer> sortMapByValue(HashMap<Integer, Float> map) {
        List<Map.Entry<Integer, Float>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Float> entry : entries) {
            result.add(entry.getKey());
        }

        return result;
    }

    public static void main(String[] args) {
        int N = 5;
        int[] stage = {2, 1, 2, 6, 2, 4, 3, 3}; //{3,4,2,1,5}

        int N1 = 4;
        int[] stage1 = {4,4,4,4,4}; //{4,1,2,3}

        System.out.println("===START===");
        System.out.println(solution(N, stage));
        System.out.println(solution(N1, stage1));
        System.out.println("===END===");
    }
}
