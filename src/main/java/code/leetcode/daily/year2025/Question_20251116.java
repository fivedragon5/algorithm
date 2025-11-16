package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/number-of-substrings-with-only-1s/?envType=daily-question&envId=2025-11-16
 *
 * 제한)
 * 1 <= s.length <= 10^5
 * s[i] is either '0' or '1'.
 *
 * 문제)
 * Given a binary string s, return the number of substrings with all characters 1's. Since the answer may be too large, return it modulo 109 + 7.
 *
 * 한국어 번역)
 *  1. 이진 문자열 s가 주어진다.
 *  2. 모든 문자가 1인 부문 문자열의 개수를 반환한다.
 *  3. 답이 너무 클 수 있으므로, 10^9 + 7로 나눈 나머지를 반환한다.
 *  ex) s = "0110111"
 *      "1" : 5개
 *      "11" : 3개
 *      "111" : 1개
 *      총 9개
 *
 * 풀이)
 *  1. 문자열을 순회하면서 '1'의 연속된 길이를 센다.
 *  2. '0'을 만나면 지금까지 센 '1'의 길이로 만들 수 있는 부분 문자열의 개수를 계산하여 결과에 더한다.
 *  3. 문자열 끝까지 도달한 후에도 '1'의 길이가 남아있다면, 마지막으로 계산하여 결과에 더한다.
 *  4. 결과를 10^9 + 7로 나눈 나머지를 반환한다.
 *
 */

public class Question_20251116 {
    public static void main(String args[]) throws IOException {
        String s = "0110111";
        System.out.println(numSub(s));

        s = "101";
        System.out.println(numSub(s));
    }

    static final long MOD = 1_000_000_007;

    public static int numSub(String s) {
        long result = 0;
        long oneLength = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') {
                oneLength++;
            } else {
                if (oneLength > 0) {
                    result = (result + oneLength * (oneLength + 1) / 2) % MOD;
                    oneLength = 0;
                }
            }
        }

        if (oneLength > 0) {
            result = (result + oneLength * (oneLength + 1) / 2) % MOD;
        }

        return (int) result;
    }
}
