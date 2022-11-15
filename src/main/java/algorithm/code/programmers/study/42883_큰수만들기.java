package algorithm.code.programmers.study;

import java.util.Arrays;

class Lesson42883 {
    static String solution(String number, int k) {
        String answer = "";
        int[] numberArr = new int[number.length()];

        for (int i = 0 ; i < number.length() ; i ++)
            numberArr[i] = Character.getNumericValue(number.charAt(i));

        Arrays.sort(numberArr);

        for (int i = numberArr.length  - 1; i >= k ; i --)
            answer += numberArr[i];

        return answer;
    }

    public static void main(String[] args) {
        String number = "1924";
        int k = 2;

        System.out.println("===START===");
        System.out.println(solution(number, k));
        System.out.println("===END===");
    }
}
