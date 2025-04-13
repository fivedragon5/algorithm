package code.leetcode.daily.year2025.month04;

import java.io.IOException;

/**
 * https://leetcode.com/problems/count-good-numbers/description/?envType=daily-question&envId=2025-04-13
 *
 * 제한)
 *  1 <= n <= 10^15
 *
 * 문제)
 *  1. 정수 n이 주어졌을 때, 길이가 n인 "좋은 숫자" 문자열의 총 개수를 구하기
 *   좋은 숫자:
 *    - 짝수 인덱스 : 짝수
 *    - 홀수 인덱스 : 소수(2,3,5,7,...)
 *  2. 숫자 문자열은 0 ~ 9 로 이루어진 문자열
 *  3. 앞자리에 0 허용
 *  4. 답이 클 수 있기에 답에 10^9 + 7 로 나눈 나머지를 반환하기
 *
 * 풀이)
 *  1. n의 수에 따라 5,4,5,4 씩 반복해서 곱하는 형태
 *  2. 값이 계속 커지지 않도록 MOD로 나누면서 계산
 *   - 빠른 거듭 제곱
 *     - 지수를 2진수로 생각 13 = 1101 -> base^13 = base^8 * base^4 * base^1
 *
 */

public class Question_20250413 {
    public static void main(String args[]) throws IOException {
        int n = 1;
        System.out.println(countGoodNumbers(n));

        int n2 = 4;
        System.out.println(countGoodNumbers(n2));

        int n3 = 50;
        System.out.println(countGoodNumbers(n3));

    }

    static final long MOD = 1000000007;

    public static int countGoodNumbers(long n) {
        long evenCount = (n + 1) / 2;  // 짝수 인덱스
        long oddCount = n / 2;         // 홀수 인덱스

        long pow5 = calc(5, evenCount);
        long pow4 = calc(4, oddCount);

        return (int) ((pow5 * pow4) % MOD);
    }

    // 빠른 거듭 제곱
    private static long calc(long base, long power) {
        long result = 1;
        while (power > 0) {
            if (power % 2 == 1) {
                result = (result * base) % MOD; // 값이 너무 커지지 않게 하기 위해 MOD wjrdyd
            }
            base = base * base % MOD;
            power /= 2;
        }
        return result;
    }
}
