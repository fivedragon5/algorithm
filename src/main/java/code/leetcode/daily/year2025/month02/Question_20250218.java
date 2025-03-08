package code.leetcode.daily.year2025.month02;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode.com/problems/construct-smallest-number-from-di-string/description/?envType=daily-question&envId=2025-02-18
 *
 * 제한)
 * 1 <= pattern.length <= 8
 * pattern consists of only the letters 'I' and 'D'.
 *
 * 문제)
 *  1. 'I', 'D' 로 이루어진 문자열 pattern 이 주어진다.
 *  2. 1~9를 중복없이 원소를 사용하는 배열 int[pattern.length + 1] 를 조건에 만족하여 만들기
 *   - I : num[i] < num[i+1]
 *   - D : num[i] > num[i+1]
 *
 * 풀이)
 *  1. 주어진 문자열의 길이 + 1 만큼 반복문을 실행
 *  2. index를 1부터 증가시키며 Stack에 넣는다
 *    - "I"를 만날 경우 Stack을 pop을 사용해서 비우고 차례대로 result에 담는다
 *    - "D"를 만날 경우 Stack에 쌓기만 진행
 *    - 반복문의 마지막 일 경우 쌓아둔 Stack을 result에 담기
 */


public class Question_20250218 {
    public static void main(String args[]) throws IOException {
        String pattern = "IIIDIDDD";
        System.out.println(smallestNumber2(pattern));

        pattern = "DDD";
        System.out.println(smallestNumber2(pattern));
    }

    public static String smallestNumber2(String pattern) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        // 숫자를 1부터 집어 넣기
        for (int i = 0; i <= pattern.length(); i++) {
            stack.push(i + 1);
            // 마지막 OR "I" 일떄 실행
            if (i == pattern.length() || pattern.charAt(i) == 'I') {
                // stack 에 값들을 result 순차적으로 담아준다.
                while (!stack.isEmpty()) {
                    int number = stack.pop();
                    result.append(number);
                }
            }
        }
        return result.toString();
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
