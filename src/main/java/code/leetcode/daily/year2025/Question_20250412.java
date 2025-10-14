package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-the-count-of-good-integers/description/?envType=daily-question&envId=2025-04-12
 *
 * 제한)
 *  1 <= n <= 10
 *  1 <= k <= 9
 *
 * 문제)
 *  1. 양의 정수 n,k가 주어짐 | n : 정수 x의 자릿 수
 *  2. 정수 x가 k-palindromic 수로 불려지기 위한 조건
 *   - x는 palindrome 수 이다 (앞,뒤 에서 읽든 같은 수)
 *   - x는 k로 나누어 떨어짐
 *  3. 또한, 정수는 좋은 수 로 불릴 수 있다
 *   - 좋은 수 : 해당 정수의 자릿수를 재배열해서 k-palindromic를 만들 수 있는 경우
 *  4. 자릿수가 n개인 좋은 수의 개수를 구하기
 *  ex) k = 2 : 2020은 2002로 재배열 할 수 있고, 2002는 palindrome수 임과 동시에 2로 나누어 떨어짐
 *   - 따라서 2020은 좋은 수 이다.
 *
 * 풀이)
 *  1. 길이가 n인 팰린드롬 수를 모두 찾기
 *  2. 팬림드롬 수 중 k로 나누어 떨어지는 수만 List에 담기
 *  3. List에 담은 팬림드롬 수를 만들수 있는 모든 조합 계산
 *   - 이때, 중복, 맨 앞자리에 0 이 온 경우를 제외
 *   - 팬림드롬 수를 오름차순으로 정렬시켜 문자로 만들어 Set에 저장(이미 탐색한 수 중복 제거)
 */

public class Question_20250412 {
    public static void main(String args[]) throws IOException {
        int n = 3; int k = 5;
        System.out.println(countGoodIntegers(n, k));

        int n2 = 1; int k2 = 4;
        System.out.println(countGoodIntegers(n2, k2));

        int n3 = 5; int k3 = 6;
        System.out.println(countGoodIntegers(n3, k3));
    }

    public static long countGoodIntegers(int n, int k) {

        long answer = 0;

        List<Long> k_palindromic_list = new ArrayList<>();

        int half = n / 2;
        int start = (int) Math.pow(10, half - 1);
        int end = (int) Math.pow(10, half);
        if (n == 1) {
            for (int i = 1; i < 10; i++) {
                if (i % k == 0) {
                    answer++;
                }
            }
            return answer;
        }

        for (int i = start; i < end; i++) {
            String numStr = String.valueOf(i);
            // 짝수
            if (n % 2 == 0) {
                String reversed = new StringBuilder(numStr).reverse().toString();
                long number = Long.parseLong(numStr + reversed);
                if (number % k == 0) {
                    k_palindromic_list.add(number);
                }
            }
            // 홀수
            else {
                String reversed = new StringBuilder(numStr).reverse().toString();
                for (int j = 0; j < 10; j++) {
                    long number = Long.parseLong(numStr + j + reversed);
                    if (number % k == 0) {
                        k_palindromic_list.add(number);
                    }
                }
            }
        }

        HashSet<String> visited = new HashSet<>();

        for (long number : k_palindromic_list) {
            String valueStr = Long.toString(number);
            char[] charArrays = valueStr.toCharArray();
            Arrays.sort(charArrays);
            String sortedNumber = new String(charArrays);

            // 이미 판별한 문자인 경우 SKIP
            if (!visited.add(sortedNumber)) {
                continue;
            }

            answer += count(charArrays, n);
        }

        return answer;
    }

    private static long count(char[] array, int n) {
        long total = factorial(n);
        Map<Character, Integer> map = new HashMap<>();
        for (char c : array) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int count : map.values()) {
            total /= factorial(count);
        }
        // 0이 맨 앞에 오는 경우의 수 제거
        if (map.containsKey('0')) {
            // 0을 하나 앞에 고정한 상태로 남은 자리 순열 계산
            map.put('0', map.get('0') - 1);
            long invalid = factorial(n - 1);
            for (int count : map.values()) {
                invalid /= factorial(count);
            }
            total -= invalid;
        }
        return total;
    }

    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
