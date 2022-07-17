package algorithm.code.programmers.stackNQueue;

import java.util.*;
import java.util.stream.Collectors;

class Lesson42586 {
    static int[] solution(int[] progresses, int[] speeds) {
        int finshedJob = 0;
        Queue<Integer> jobPublishList = new LinkedList<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0 ; i < progresses.length ; i++) {
            jobPublishList.add((int) Math.ceil((100-progresses[i])/(double) speeds[i]));
        }

        while (!jobPublishList.isEmpty()) {
            int day = jobPublishList.remove();
            finshedJob++;
            while (!jobPublishList.isEmpty()) {
                if(day >= jobPublishList.peek()) {
                    jobPublishList.poll();
                    finshedJob++;
                }
                else break;
            }
            answerList.add(finshedJob);
            finshedJob = 0;
        }
        int[] answer = new int[answerList.size()];

        for(int i = 0 ; i < answerList.size() ; i ++)
            answer[i] = answerList.get(i);

        //stream 오래걸림
        //int[] test = answerList.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }

    public static void main(String[] args) {
        int[] progresses = {93, 30, 5};
        int[] speeds = {2, 30, 5};
        System.out.println("===START===");
        System.out.println(Arrays.toString(solution(progresses, speeds)));
        System.out.println("===END===");
    }
}
