package algorithm.programmers;

import java.util.HashMap;
import java.util.Iterator;

class Lesson42889 {
    static int[] solution(int N, int[] stages) {
        int[] answer = {};
        HashMap<Integer, Integer> stageStayPlayer = new HashMap<>();
        HashMap<Integer, Double>  failRateMap = new HashMap<>();
        int stagePlayer = stages.length;

        for(int i = 1 ; i <= N ; i++) {
            failRateMap.put(i, (double) 0);
        }

        for(int stage : stages)
            stageStayPlayer.put(stage, stageStayPlayer.getOrDefault(stage, 0) + 1);

        for(int stage : stageStayPlayer.keySet()) {

        }

        return answer;
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
