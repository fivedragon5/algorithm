package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-number-of-operations-to-move-ones-to-the-end/?envType=daily-question&envId=2025-11-13
 *
 * 제한)
 *  1 <= s.length <= 10^5
 *  s[i] is either '0' or '1'.
 *
 * 문제)
 *  1. 이진법 문자열 s가 주어진다.
 *  2. 다음 연산을 원하는 만큼 수행할 수 있다.
 *   - 연산)
 *      - s[i] == '1'이고 s[i + 1] == '0'인 인덱스 i를 선택한다. (단, i + 1 < s.length)
 *      - 문자 s[i]를 문자열의 끝이나 다른 '1'에 도달할 때까지 오른쪽으로 이동시킨다.
 *      - 예를 들어, s = "010010"이고 i = 1을 선택하면 결과 문자열은 s = "000110"이 된다.
 *  4. 수행할 수 있는 최대 연산 횟수를 반환한다
 *
 * 풀이)
 *  1. 오른쪽에서 왼쪽으로 탐색하면서 0이 나온 이후의 연속된 1 개수들을 기록한다.
 *  2. 각 그룹의 기여도를 가중합으로 계산한다.
 *
 */

public class Question_20251113 {
    public static void main(String args[]) throws IOException {
        String s = "1001101";
        System.out.println(maxOperations(s));

        s = "00111";
        System.out.println(maxOperations(s));
    }

    public static int maxOperations(String s) {
        if (s == null || s.length() <= 1) return 0;

        int n = s.length();
        boolean seenZero = false;
        int onesCount = 0;
        List<Integer> groups = new ArrayList<>();

        // 오른쪽에서 왼쪽으로 탐색하여 0이 나온 이후의 연속된 1 개수들을 기록
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '1') {
                if (seenZero) {
                    onesCount++;
                }
            } else { // c == '0'
                seenZero = true;
                if (onesCount > 0) {
                    groups.add(onesCount);
                    onesCount = 0;
                }
            }
        }

        if (onesCount > 0) {
            groups.add(onesCount);
        }

        // 각 그룹의 기여도를 가중합으로 계산
        int result = 0;
        int m = groups.size();
        for (int i = 0 ; i < m; i++) {
            result += groups.get(i) * (i + 1);
        }

        return result;
    }
}
