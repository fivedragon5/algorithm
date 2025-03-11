package code.leetcode.daily.year2025.month03;

import java.io.IOException;

/**
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/?envType=daily-question&envId=2025-03-11
 *
 * 제한)
 *  3 <= s.length <= 5 x 10^4
 *  s only consists of a, b or c characters.
 *
 * 문제)
 *  1. 'a', 'b', 'c' 로 이루어진 문자열 s가 주어진다.
 *  2. s의 문자열을 잘라서 각 문자들이 1개이상 존재하는 새로운 문자열의 수를 반환
 *
 * 풀이)
 *  - Sliding Window, 누적합
 *  1. a,b,c 3개만 사용하지만 배열에 이용하기위해 배열의 크기를 100 까지 선언 abcArray
 *  2. right Index를 증가 시키면서 a,b,c가 각각 최소1번 이상 존재하는 지점부터 누적합 로직 추가
 *  3. 누적합 로직
 *   - leftIndex를 증가시켜 기존 a,b,c가 존재하지 않는 지점까지 반복
 *   - 이때 가능한 모든 배열을 sum에 누적 합
 *   - right가 증가하더라고 이 누적합은 항상 가능하기에 answer = 누적합 + 1(0 ~ right 까지의 문자) 을 적용시켜준다.
 */

public class Question_20250311 {
    public static void main(String args[]) throws IOException {
        String s = "acbbcac";
        System.out.println(numberOfSubstrings(s));

        s = "aaacb";
        System.out.println(numberOfSubstrings(s));

        s = "abc";
        System.out.println(numberOfSubstrings(s));
    }

    public static int numberOfSubstrings(String s) {
        int answer = 0;
        int[] abcArray = new int[100];
        int sLength = s.length();
        int left = 0;
        int right = 0;
        int sum = 0;
        boolean isFlag = false;

        while (right < sLength) {
            abcArray[s.charAt(right++)]++;
            if (!isFlag) {
                if (checkArrayABC(abcArray)) {
                    isFlag = true;
                } else {
                    continue;
                }
            }

            while (left < right - 3) {
                if (abcArray[s.charAt(left)] < 2) {
                    break;
                }
                abcArray[s.charAt(left)]--;
                sum++;
                left++;
            }
            answer += sum + 1;
        }
        return answer;
    }

    private static boolean checkArrayABC(int[] array) {
        return array[97] > 0 && array[98] > 0 && array[99] > 0;
    }
}
