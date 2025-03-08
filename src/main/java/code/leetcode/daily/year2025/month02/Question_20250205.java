package code.leetcode.daily.year2025.month02;

import java.io.IOException;

/**
 * https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/description/?envType=daily-question&envId=2025-02-05
 *
 * 제한)
 *  1 <= s1.length, s2.length <= 100
 * s1.length == s2.length
 * s1 and s2 consist of only lowercase English letters.
 *
 * 문제)
 *  1. 길이가 같은 문자열 s1, s2가 주어짐
 *  2. 문자열 스왑 : 문자열에서 두 인덱스를 선택하고 (반드시 다를 필요는 없음) 해당 인덱스에 대한 문자를 교환하는 작업
 *  3. 문자열 중 하나를 최대 한번 교환해서 두 문자열을 동일하게 만들기
 *   - 만들 수 있으면 true, 없으면 false
 *
 * 풀이)
 *  1. 주어진 문자열 길이만큼 반복
 *   - 문자열 2개를 앞에서 부터 비교
 *   - 첫번째로 다른 문자를 발견할 경우 해당 index 저장
 *   - 두번쨰로 다른 문자를 발견할 경우 첫번째로 저장한 index와 문자열을 바꿔서 비교
 *   - 비교한 문자가 다를경우 false 반환
 *  2. 문자가 3개이상 다르거나 1개만 다를 경우 false
 *  3. 위 조건을 전부 만족하거나 다른 문자가 없는 경우 true 반환
 */

public class Question_20250205 {
    public static void main(String args[]) throws IOException {

        String s1 = "bank"; String s2 = "kanb";
        System.out.println(areAlmostEqual(s1, s2));

        s1 = "attack"; s2 = "defend";
        System.out.println(areAlmostEqual(s1, s2));

        s1 = "kelb"; s2 = "kelb";
        System.out.println(areAlmostEqual(s1, s2));

        s1 = "npv"; s2 = "zpn";
        System.out.println(areAlmostEqual(s1, s2));
    }

    public static boolean areAlmostEqual(String s1, String s2) {
        int strLength = s1.length();
        int tempIndex = -1;
        for (int i = 0; i < strLength; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (tempIndex == -1) {
                    tempIndex = i;
                } else if (tempIndex == -2) {
                    return false;
                } else {
                    if (s1.charAt(tempIndex) != s2.charAt(i) || s1.charAt(i) != s2.charAt(tempIndex)) {
                        return false;
                    } else {
                        tempIndex = -2;
                    }
                }
            }
        }
        if (tempIndex >= 0) {
            return false;
        }
        return true;
    }
}
