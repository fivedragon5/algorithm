package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/smallest-integer-divisible-by-k/description/?envType=daily-question&envId=2025-11-25
 *
 * 제한)
 *  1 <= k <= 10^5
 *
 * 문제)
 *  1. 양의 정수 k가 주어진다.
 *  2. 양의 정수 n은 k로 나누어 떨어지고, n은 오직 숫자 1만 포함하는 가장 작은 양의 정수 n의 길이를 찾아라.
 *  3. 만약 그런 n이 없다면 -1을 반환하라.
 *
 * 풀이)
 *  1. 숫자 1로만 이루어진 수를 생성하는데, 매번 k로 나눈 나머지를 계산하여 메모리 사용을 줄인다.
 *  2. 길이를 1부터 k까지 증가시키면서 나머지가 0이 되는지 확인한다.
 *  3. 나머지가 0이 되는 경우 해당 길이를 반환하고, 그렇지 않으면 -1을 반환한다.
 */

public class Question_20251125 {
    public static void main(String args[]) throws IOException {
        int k = 1;
        System.out.println(smallestRepunitDivByK(k));
    }

    public static int smallestRepunitDivByK(int k) {
        long number = 1;
        for (int length = 1; length <= k; length++) {
            if (number % k == 0) {
                return length;
            }
            number = (number * 10 + 1) % k;
        }
        return -1;
    }
}
