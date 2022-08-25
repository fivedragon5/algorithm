package algorithm.code.programmers.easy;

class Lesson70129 {
    static int[] solution(String s) {

        int removeZeroCount = 0;
        int round = 1;

        int strLength = s.length();
        String regex = "[0]";
        boolean isFlage = true;

        while (isFlage) {
            s = s.replaceAll(regex, "");
            int newStrLength = s.length();
            removeZeroCount += strLength - newStrLength;

            s = Integer.toBinaryString(newStrLength);
            if ("1".equals(s)) break;

            strLength = s.length();
            round++;
        }

        int[] answer = {round, removeZeroCount};

        return answer;
    }

    public static void main(String[] args) {
        /*
            1. x의 모든 0 제거
            2. x의 길이를 c라고 하면, x를 "c를 2진법으로 표현한 문자열"로 바꿈
            * 1 <= s.length <= 150,000
            * s에는 최소 1개 의상의 1이 포함
         */

        //String s = "110010101001"; // {3,8}
        //String s = "01110"; // {3,3}
        String s = "1111111"; // {4,1}
        System.out.println("===START===");
        System.out.println(solution(s));
        System.out.println("===END===");
    }
}
