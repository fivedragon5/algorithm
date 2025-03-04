package code.leetcode.daily;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/description/?envType=daily-question&envId=2025-03-04
 *
 * 제한)
 * 1 <= n <= 10^7
 *
 * 문제)
 *  1. integer n 이 주어집니다.
 *  2. n이 3의 거듭제곱 합으로 나타낼 수 있으면 true, 없으면 false를 반환.
 *
 * 풀이)
 *  1. 제한사항이 10^7 이기에 10^7 보다 작은 3의 1부터 제곱값을 array 선언
 *  2. Array에 가장 큰값부터 빼 면서 0으로 만들 수 있는지 확인
 *   - 만들 수 있다면 true 없다면 false
 */

public class Question_20250304 {
    public static void main(String args[]) throws IOException {
        System.out.println(4782969 * 3);


        int n = 12;
        System.out.println(checkPowersOfThree(n));

        n = 91;
        System.out.println(checkPowersOfThree(n));

        n = 21;
        System.out.println(checkPowersOfThree(n));
    }

    private static final int[] THREES_ARRAY = new int[]{1, 3, 9, 27 ,81, 243, 729, 2187, 6561, 19683, 59049, 177147, 1594323, 4782969};

    public static boolean checkPowersOfThree(int n) {
        for (int i = THREES_ARRAY.length - 1; i >= 0; i--) {
            if (n == 0) {
                return true;
            }
            if (n - THREES_ARRAY[i] >= 0) {
                n -= THREES_ARRAY[i];
            }
        }
        return n == 0;
    }

    public static boolean checkPowersOfThree2(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
