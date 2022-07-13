package algorithm.code.programmers.easy;

import java.util.Arrays;

class Lesson77484 {
    static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int correctCount = 0;
        int zeroCount = 0;
        Arrays.sort(lottos);
        Arrays.sort(win_nums);

        for(int i = 0 ; i < lottos.length ; i++) {
            if(lottos[i] == 0) {
                zeroCount++;
                continue;
            }
            for(int j = 0 ; j < lottos.length ; j++) {
                if(lottos[i] == win_nums[j]) {
                    correctCount++;
                    break;
                }
            }
        }

        answer[0] = 7-correctCount-zeroCount;
        answer[1] = 7-correctCount;

//        if(correctCount == 0) {
//            answer[0] = 6;
//            answer[1] = 6;
//            if(zeroCount != 0) answer[0] = 7-zeroCount;
//        }

        answer[0] = answer[0] > 6 ? 6 : answer[0];
        answer[1] = answer[1] > 6 ? 6 : answer[1];



        return answer;
    }

    public static void main(String[] args) {
        int[] lottos = {11, 12, 13, 14, 15, 16};
        int[] win_nums = {1, 2, 3, 4, 5, 6};
        System.out.println("===START===");
        System.out.println(solution(lottos, win_nums));
        System.out.println("===END===");
    }
}
