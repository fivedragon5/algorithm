package code.leetcode.daily.year2026;

import java.io.IOException;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-ii/description/?envType=daily-question&envId=2026-03-30
 *
 * 제한)
 *  n == s1.length == s2.length
 *  1 <= n <= 10^5
 *  s1, s2 소문자 알파벳으로 구성되어 있음
 *
 * 문제)
 *  1. 길이가 n인 소문자 알파벳으로 구성되어진 문자열 2개 s1, s2가 주어진다.
 *  2. 두 문자열 중 어느 것이든 다음과 같은 연산을 수행할 수 있다.
 *   - i < j 이고 j - i의 차이가 짝수인 두 인덱스 i,j를 선택
 *   - 해당 문자열에서 두 인덱스에 위치한 문자를 서로 바꿈
 *  3. 연산을 통해 s1, s2를 서로 같게 만들 수 있으면 true, 못하면 false 반환
 *
 * 풀이)
 * 짝수 번째 인덱스(0,2,4...)에 있는 문자들은 서로 위치를 바꿀 수 있지만 홀수 번째 인덱스로는 이동할 수 없다.
 * 마찬가지로 홀수번째 인덱스(1,3,5...)에 있는 문자들끼리만 서로 위치를 바꿀 수 없습니다.
 * -> s1, s2 가 같아지려면 s1의 짝수 번째 인덱스에 있는 문자들의 구성, s2의 짝수 번째 인덱스
 *
 */

public class Question_20260330 {
    public static void main(String args[]) throws IOException {
        String s1 = "abcdba";
        String s2 = "cabdab";
        System.out.println(checkStrings(s1, s2));

        s1 = "abe";
        s2 = "bea";
        System.out.println(checkStrings(s1, s2));

        s1 = "shvqocguj";
        s2 = "vqsghujco";
        System.out.println(checkStrings(s1, s2));
    }

    public static boolean checkStrings(String s1, String s2) {
        int n = s1.length();

        // 짝수, 홀수 인덱스의 문자 개수를 저장할 배열 (알파벳 26자)
        int[] evenCount1 = new int[26]; // 짝수
        int[] oddCount1 = new int[26]; // 홀수
        int[] evenCount2 = new int[26];
        int[] oddCount2 = new int[26];

        for (int i = 0 ; i < n ; i++) {
            if (i % 2 == 0) {
                evenCount1[s1.charAt(i) - 'a']++;
                evenCount2[s2.charAt(i) - 'a']++;
            } else {
                oddCount1[s1.charAt(i) - 'a']++;
                oddCount2[s2.charAt(i) - 'a']++;
            }
        }

        return Arrays.equals(evenCount1, evenCount2) && Arrays.equals(oddCount1, oddCount2);
    }
}
