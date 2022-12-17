package algorithm.code.study;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * TODO : 22 12.16 이중 for문 시간초과 다시풀어보기
 * 
 * 제한)
 * 1 ≤ k ≤ 1,000,000
 * 1 ≤ d ≤ 1,000,000
 */
class Lesson140107 {
    static long solution(int k, int d) {

        ArrayList<Integer> numberList = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i <= d; i += k) {
            numberList.add(i);
        }

        System.out.println(k + "," + d);

        long maxLength = (long) d * d;

        long count = 0;

        for (int number1 : numberList) {
            long pow1 = (long) number1 * number1;
            for (int number2 : numberList) {
                long pow2 = (long) number2 * number2;
                if (maxLength >= pow1 + pow2) {
                    count++;
                }
                else {
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int k = 1;
        int d = 5;
        System.out.println("===START===");
        System.out.println(solution(k, d));
        System.out.println("===END===");
    }
}
