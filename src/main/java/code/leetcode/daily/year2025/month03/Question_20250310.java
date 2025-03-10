package code.leetcode.daily.year2025.month03;

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
 *
 */

public class Question_20250310 {
    public static void main(String args[]) throws IOException {
        String word = "hoiuafoehh"; int k = 3;
        System.out.println(countOfSubstrings(word, k));

        word = "ieaouqqieaouqq"; k = 1;
        System.out.println(countOfSubstrings(word, k)); // 3

        word = "aeiou"; k = 0;
        System.out.println(countOfSubstrings(word, k)); // 1

        word = "ieaouqqieaouqq"; k = 1;
        System.out.println(countOfSubstrings(word, k)); // 3

        // iqeaouqi 2
        // iqeaouq qeaouq(x)
        // iqeaouqi qeaouqi eaouqi(x)

        // hoiuafoehh 3
        // hoiuafoeh oiuafoeh(x)
        // oiuafoehh iuafoehh uafoehh(x)

    }

    private static char[] VOWEL_LIST = new char[]{'a', 'e', 'i', 'o', 'u'};

    public static long countOfSubstrings(String word, int k) {
        long answer = 0;
        Map<Character, Integer> vowelMap = new HashMap<>();
        vowelMap.put('a', 0);
        vowelMap.put('e', 0);
        vowelMap.put('i', 0);
        vowelMap.put('o', 0);
        vowelMap.put('u', 0);
        int consonantCount = 0;
        int leftIndex = 0;
        boolean isCompleted = false;

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (isVowel(currentChar)) {
                vowelMap.put(currentChar, vowelMap.get(currentChar) + 1);
                if (consonantCount == k && checkVowelMap(vowelMap)) {
                    answer++;
                    isCompleted = true;
                }
                else {
                    isCompleted = false;
                }

                if (isCompleted) {
                    Map<Character, Integer> tempVowelMap = new HashMap<>(vowelMap);
                    int tempLeftIndex = leftIndex;
                    while (tempLeftIndex <= i - 5) {
                        char leftChar = word.charAt(tempLeftIndex++);
                        if (isVowel(leftChar)) {
                            tempVowelMap.put(leftChar, tempVowelMap.get(leftChar) - 1);
                            if (checkVowelMap(tempVowelMap)) {
                                answer++;
                                continue;
                            }
                        }
                        break;
                    }
                }
            }
            else {
                consonantCount++;
                if (consonantCount == k && checkVowelMap(vowelMap)) {
                    answer++;
                    Map<Character, Integer> tempVowelMap = new HashMap<>(vowelMap);
                    int tempLeftIndex = leftIndex;
                    while (tempLeftIndex <= i - 5) {
                        char leftChar = word.charAt(tempLeftIndex++);
                        if (isVowel(leftChar)) {
                            tempVowelMap.put(leftChar, tempVowelMap.get(leftChar) - 1);
                            if (checkVowelMap(tempVowelMap)) {
                                answer++;
                                continue;
                            }
                        }
                        break;
                    }
                } else {
                    while (consonantCount >= k) {
                        char leftChar = word.charAt(leftIndex++);
                        if (isVowel(leftChar)) {
                            vowelMap.put(leftChar, vowelMap.get(leftChar) - 1);
                            if (consonantCount == k && checkVowelMap(vowelMap)) {
                                answer++;
                            }
                        } else {
                            consonantCount--;
                            if (consonantCount == k && checkVowelMap(vowelMap)) {
                                answer++;
                            }
                        }
                    }
                }
            }
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
