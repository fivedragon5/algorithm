package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/count-the-number-of-powerful-integers/description/?envType=daily-question&envId=2025-04-10
 *
 * 제한)
 *  1 <= start <= finish <= 10^15
 *  1 <= limit <= 9
 *  1 <= s.length <= floor(log10(finish)) + 1
 *  s only consists of numeric digits which are at most limit.
 *  s does not have leading zeros.
 *
 * 문제)
 *  1. 주어진 start ~ finish 사이에서 powerful 한 수의 갯수를 반환하기
 *   powerful:
 *    - "s" 를 접미사로 가져야 함 s="11" ex)11, 111, 211, 311, 411, ....
 *    - 각 자리수가 limit 보다 작아야 함 limit = 3 ex) 11, 111, 211, 311, 411(x), 511(x)
 *
 * 풀이)
 *  SOLUTIONS 참고
 *   1. (0 ~ Finsh 까지 PowerfulCount) - (0 ~ start-1 PowerfulCount) 계산
 *    - 자릿수가 s.length 이상인 수들만 고려
 *   2. s를 제외한 수의 앞에 붙는 prefix 부분을 만들면서 조건에 맞는 숫자의 개수를 세기
 *   3. prefix의 모든 자릿수가 0 ~ limit 범위 안에 있어야 함
 *   4. 마지막에 prefix + s가 end 이하인지 확인
 */

public class Question_20250410 {
    public static void main(String args[]) throws IOException {
        int start = 1; long finish = 6000; int limit = 4; String s = "124";
        System.out.println(numberOfPowerfulInt(start, finish, limit, s));

        start = 15; finish = 215; limit = 6; s = "10";
        System.out.println(numberOfPowerfulInt(start, finish, limit, s));

        start = 1000; finish = 2000; limit = 4; s = "3000";
        System.out.println(numberOfPowerfulInt(start, finish, limit, s));
    }

    public static long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        return countPowerful(finish, limit, s) - countPowerful(start - 1, limit, s);
    }

    private static long countPowerful(long end, int limit, String s) {
        String endToString = Long.toString(end);
        int prefixLength = endToString.length() - s.length();

        // 접두사의 길이가 0일 경우 0 반환
        if (prefixLength < 0) return 0;
        // dp[i][0] = i번째 자리에서 상한 제한 없이 만들 수 있는 조합 수
        // dp[i][1] = i번째 자리에서 상한 제한을 따르면서 만들 수 있는 조합 수
        long[][] dp = new long[prefixLength + 1][2];
        dp[prefixLength][0] = 1;
        dp[prefixLength][1] = endToString.substring(prefixLength).compareTo(s) >= 0 ? 1 : 0;

        for (int i = prefixLength - 1; i >=0; i--) {
            int currentEnd = endToString.charAt(i) - '0';

            dp[i][0] = (limit + 1) * dp[i + 1][0]; // 전부 가능

            if (currentEnd <= limit) {
                dp[i][1] = (long) currentEnd * dp[i+1][0] + dp[i+1][1];
            } else {
                dp[i][1] = (long) dp[i+1][0] * (limit + 1);
            }
        }

        return dp[0][1];
    }
}
