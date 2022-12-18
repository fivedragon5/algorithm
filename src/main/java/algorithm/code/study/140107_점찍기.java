package algorithm.code.study;

import java.util.ArrayList;

/**
 * TODO : 22 12.16 이중 for문 시간초과 다시풀어보기, 반으로 줄여도 시간초과 TestCase 11, 13, 14 조건에 만족하는 수 부분을 줄여야 할꺼같다.
 *
 * 제한)
 * 1 ≤ k ≤ 1,000,000
 * 1 ≤ d ≤ 1,000,000
 */
class Lesson140107 {
    static long solution2(int k, int d) {
        ArrayList<Integer> numberList = new ArrayList<>();
        for (int i = 0; i <= d; i += k) {
            numberList.add(i);
        }
        long maxLength = (long) d * d;

        long count = 0;

        for (int i = 0; i < numberList.size(); i++) {
            long pow1 = (long) Math.pow(numberList.get(i),2);
            long sum = pow1;
            while (maxLength > sum) {

            }
        }
        return count;
    }
    static long solution(int k, int d) {
        ArrayList<Integer> numberList = new ArrayList<>();

        for (int i = 0; i <= d; i += k) {
            numberList.add(i);
        }
        long maxLength = (long) d * d;

        long count = 0;

        for (int i = 0; i < numberList.size(); i++) {
            long pow1 = (long) Math.pow(numberList.get(i),2);
            for (int j = i; j < numberList.size(); j++) {
                long pow2 = (long) Math.pow(numberList.get(j),2);
                if (maxLength >= pow1 + pow2) {
                    if (pow1 == pow2) count++;
                    else count += 2;
                }
                else {
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int k = 2;
        int d = 4;
        System.out.println("===START===");
        System.out.println(solution(k, d));
        System.out.println("===END===");
    }
}
