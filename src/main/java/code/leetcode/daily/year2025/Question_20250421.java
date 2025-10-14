package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/count-the-hidden-sequences/description/?envType=daily-question&envId=2025-04-21
 *
 * 제한)
 *  n == differences.length
 *  1 <= n <= 10^5
 *  -10^5 <= differences[i] <= 10^5
 *  -10^5 <= lower <= upper <= 10^5
 *
 *  10000000000
 *
 * 문제)
 *  differences[i] = hidden[i+1] - hidden[i]
 *  위 식을 만족하고 원소가 lower ~ upper 사이에 있는 가능한 배열의 수를 반환
 *
 * 풀이)
 *  1. differences 배열에 누적합을 통해 가장 큰, 작은 값을 구한다
 *  2. 주어진 범위 (upper, lower)내에서 가능한 값을 연산
 *   - 0보다 작을경우 존재 X
 *
 */

public class Question_20250421 {
    public static void main(String args[]) throws IOException {
        int[] differences6 = new int[]{100000, 100000, 100000}; int lower6 = -100000; int upper6 = 100000;
        System.out.println(numberOfArrays(differences6, lower6, upper6));

        int[] differences5 = new int[]{83702,-5216}; int lower5 = -82788; int upper5 = 14602;
        System.out.println(numberOfArrays(differences5, lower5, upper5));

        int[] differences4 = new int[]{-40}; int lower4 = -46; int upper4 = 53;
        System.out.println(numberOfArrays(differences4, lower4, upper4));

        int[] differences2 = new int[]{3,-4,5,1,-2}; int lower2 = -4; int upper2 = 5;
        System.out.println(numberOfArrays(differences2, lower2, upper2));

        int[] differences3 = new int[]{4,-7,2}; int lower3 = 3; int upper3 = 6;
        System.out.println(numberOfArrays(differences3, lower3, upper3));

        int[] differences = new int[]{1,-3,4}; int lower = 1; int upper = 6;
        System.out.println(numberOfArrays(differences, lower, upper));
    }

    public static int numberOfArrays(int[] differences, int lower, int upper) {
        long sum = 0;
        long min = 0;
        long max = 0;

        for (int diff : differences) {
            sum += diff;
            min = Math.min(min, sum);
            max = Math.max(max, sum);
        }

        long start = lower - min;
        long end = upper - max;

        return Math.max(0, (int) (end - start + 1));
    }
}
