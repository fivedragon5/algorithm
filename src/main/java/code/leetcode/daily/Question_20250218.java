package code.leetcode.daily;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/construct-smallest-number-from-di-string/description/?envType=daily-question&envId=2025-02-18
 *
 * 제한)
 * 1 <= pattern.length <= 8
 * pattern consists of only the letters 'I' and 'D'.
 *
 * 문제)
 *
 * 풀이)
 */

public class Question_20250218 {
    public static void main(String args[]) throws IOException {
        String pattern = "IIIDIDDD";
        System.out.println(smallestNumber(pattern));

        pattern = "DDD";
        System.out.println(smallestNumber(pattern));
    }
    public static String smallestNumber(String pattern) {
        int start = 1; int min = 9;
        int[] numbers = new int[pattern.length() + 1];
        numbers[0] = start;
        for (int i = 0; i < pattern.length(); i++) {
            Character character = pattern.charAt(i);
            if (character == 'I') {
                start++;
            } else {
                min = Math.min(min, start--);
            }
            numbers[i+1] = start;
        }
        int sum = 0;
        if (min <= 0) {
            sum = (min * -1) + 2;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            sb.append(numbers[i] + sum);
        }
        return sb.toString();
    }
}
