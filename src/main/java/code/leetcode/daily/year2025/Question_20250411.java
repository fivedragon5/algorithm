package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/count-symmetric-integers/description/?envType=daily-question&envId=2025-04-11
 *
 * 제한)
 *  1 <= low <= high <= 10^4
 *
 * 문제)
 *  1. low <= x <= high 를 만족하는 대칭 정수 찾기
 *   대칭 정수 :
 *    - 숫자의 자릿수는 짝수
 *    - 가운대를 기준으로 좌,우 각각의 자릿수의 합이 동일
 *
 * 풀이)
 *  1. low ~ high 까지 탐색 BFS
 *  2. 숫자를 String 으로 변환해 자릿수로 홀,짝수 판별
 *   - 자릿수 홀수 : 자릿수를 점프해서 continue
 *   - 자릿수 짝수 : 가운대를 기준으로 대칭정수 판별
 */

public class Question_20250411 {
    public static void main(String args[]) throws IOException {
        int low = 1; int high = 100;
        System.out.println(countSymmetricIntegers(low, high));

        int low2 = 1200; int high2 = 1230;
        System.out.println(countSymmetricIntegers(low2, high2));
    }

    public static int countSymmetricIntegers(int low, int high) {
        int count = 0;

        for (int i = low; i <= high; i++) {
            int len = Integer.toString(i).length();
            // 자릿수 홀수
            if (len % 2 != 0) {
                i = (int) Math.pow(10, len);
                i--;
                continue;
            }
            String numberStr = Integer.toString(i);
            int leftSum = 0;
            int rightSum = 0;
            int n = len / 2;
            for (int j = 0; j < n; j++) {
                leftSum += numberStr.charAt(j) - '0';
                rightSum += numberStr.charAt(j + n) - '0';
            }
            if (leftSum == rightSum) {
                count++;
            }
        }

        return count;
    }
}
