package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/count-and-say/description/?envType=daily-question&envId=2025-04-18
 *
 * 제한)
 *  1 <= n <= 30
 *
 * 문제)
 *  countAndSay(n) = countAndSay(countAndSay(n-1))
 *   1. n이 주어젔을때 countAndSay(n) 결과를 출력
 *    - example : Run-length encoding (RLE) "1112233" -> "312223" 문자열 압축
 *    - example : "3322251" -> "23321511"
 *
 * 풀이)
 *  1. 주어진 n만큼 반복문 실행
 *   - 결과값을 반복해서 RLE 함수에 넣기
 *  2. RLE
 *   - 주어진 문자열 길이만큼 반복 1 ~ n
 *   - 이전 문자열과 다른 문자열이 나왔을 경우 이전까지 index, 문자를 쓰기
 *   - 같은 문자열일 경우 index + 1
 *   - 반복문 종료시 마지막 끝나는 부분만 확인하기
 *
 */

public class Question_20250418 {
    public static void main(String args[]) throws IOException {

        int n = 4;
        System.out.println(countAndSay(n));

        int n3 = 2;
        System.out.println(countAndSay(n3));

        int n2 = 1;
        System.out.println(countAndSay(n2));
    }

    public static String countAndSay(int n) {
        String answer = "1";
        for (int i = 2; i <= n; i++) {
            answer = getRLE(answer);
        }
        return answer;
    }

    private static String getRLE(String numberStr) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char prevCh = numberStr.charAt(0);
        int n = numberStr.length();
        int total = 0;

        for (int i = 1; i < n; i++) {
            char currentCh = numberStr.charAt(i);
            if (prevCh == currentCh) {
                count++;
            } else {
                sb.append(count);
                sb.append(prevCh);
                total += count;
                count = 1;
                prevCh = currentCh;
            }
        }

        if (total != n) {
            sb.append(count);
            sb.append(prevCh);
        }

        return sb.toString();
    }
}
