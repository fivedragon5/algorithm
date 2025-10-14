package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/description/?envType=daily-question&envId=2025-03-10
 *
 * 제한)
 *  5 <= word.length <= 2 * 10^5
 *  word consists only of lowercase English letters.
 *  0 <= k <= word.length - 5
 *
 * 문제)
 *  1. 문자열 word 에서 모음 (a,e,i,o,u)을 최소 한번 포함하고 k개의 자음을 포합하는 문열의 수를 반환하기
 *  2. k = 1 일 경우, ioaeuq
 *
 * 풀이)
 *  1. left, right 를 사용한 sliding window 사용
 *  2. vowelMap Character, Integer (모음 Map, count)
 *  3. vowelCount 누적 가능 카운트
 *   - 자음 수 = k 질때 answer 누적된 수를 더해주기 (중요)
 *  4. consonantsCount : 자음 수
 *  5. right < word.length 반복문 실행
 *   - 모음 일 경우 vowelMap 에 카운트 추가
 *   - 자음 일 경우
 *      - consonantsCount > k
 *          - k랑 같아 질 때까지 leftIndex++ 시키며 왼쪽 문자열을 하나씩 제거 관련 카운트도 갱신
 *          - vowelCount 초기화
 *      - consonantsCount == k, 모든 모음 1개 이상 존재
 *          - 조건을 만족하지 않을때까지 반복 문 실행
 *          - 반복할때마다 leftIndex 증가, vowelCount 증가
 *
 */

public class Question_20250310 {
    public static void main(String args[]) throws IOException {
        String word = "ieaouqqieaouqq"; int k = 1;
        System.out.println(countOfSubstrings(word, k));

        word = "ieaouqqieaouqq"; k = 1;
        System.out.println(countOfSubstrings(word, k)); // 3

        word = "aeiou"; k = 0;
        System.out.println(countOfSubstrings(word, k)); // 1

        // ieaouqqieaouqq 1
        // ieaouq(o)
        // ieaouqq(x) ... q
        // qieaou(o) qieaouq(x) ieaouq(o)
    }

    private static char[] VOWEL_LIST = new char[]{'a', 'e', 'i', 'o', 'u'};

    public static long countOfSubstrings(String word, int k) {
        long answer = 0;
        int length = word.length();
        Map<Character, Integer> vowelMap = new HashMap<>();
        vowelMap.put('a', 0);
        vowelMap.put('e', 0);
        vowelMap.put('i', 0);
        vowelMap.put('o', 0);
        vowelMap.put('u', 0);
        int consonantsCount = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        int vowelCount = 0;

        while (rightIndex < length) {
            char currentChar = word.charAt(rightIndex);
            if (isVowel(currentChar)) {
                vowelMap.put(currentChar, vowelMap.get(currentChar) + 1);
            } else {
                consonantsCount++;
            }

            while (consonantsCount > k) {
                vowelCount = 0;
                char leftChar = word.charAt(leftIndex++);
                if (isVowel(leftChar)) {
                    vowelMap.put(leftChar, vowelMap.get(leftChar) - 1);
                } else {
                    consonantsCount--;
                }
            }

            if (consonantsCount == k) {
                if (checkVowelMap(vowelMap)) {
                    while (leftIndex <= rightIndex) {
                        char leftChar = word.charAt(leftIndex);
                        if (isVowel(leftChar)) {
                            int count = vowelMap.get(leftChar);
                            if (count > 1) {
                                vowelMap.put(leftChar, count - 1);
                                vowelCount++;
                                leftIndex++;
                                continue;
                            }
                        }
                        break;
                    }
                    answer += vowelCount + 1;
                }
            }
            rightIndex++;
        }

        return answer;
    }

    private static boolean checkVowelMap(Map<Character, Integer> vowelMap) {
        for (char c : VOWEL_LIST) {
            if(vowelMap.get(c) <= 0) return false;
        }
        return true;
    }

    private static boolean isVowel(char input) {
        for (char c : VOWEL_LIST) {
            if (c == input) {
                return true;
            }
        }
        return false;
    }
}
