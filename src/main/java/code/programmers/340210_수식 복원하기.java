package code.programmers;

import java.util.ArrayList;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/340210
 *
 * 제한)
 *  2 ≤ expressions의 길이 ≤ 100
 *      - expressions의 원소는 "A + B = C" 혹은 "A - B = C" 형태의 문자열입니다. A, B, C와 연산 기호들은 공백 하나로 구분되어 있습니다.
 *      - A, B는 음이 아닌 두 자릿수 이하의 정수입니다.
 *      - C는 알파벳 X 혹은 음이 아닌 세 자릿수 이하의 정수입니다. C가 알파벳 X인 수식은 결괏값이 지워진 수식을 의미하며, 이러한 수식은 한 번 이상 등장합니다.
 *      - 결괏값이 음수가 되거나 서로 모순되는 수식은 주어지지 않습니다.
 *
 * 문제)
 *  1. 주어진 수식은 2 ~ 9 진법중 하나
 *  2. 덧셈 혹은 뺄셈 수식들이 담긴 1차원 문자열 배열이 주어질 때, 결괏값이 지워진 수식들의 결괏값을 구하기
 *
 * 풀이)
 *
 */

class Lesson340210 {
    public static void main(String[] args) {
        String[] expressions = new String[]{"14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"};
        System.out.println(solution(expressions));

        String[] expressions2 = new String[]{"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"};
        System.out.println(solution(expressions));
    }

    public static int solution(String[] expressions) {
        int minBase = 2;
        List<Integer> xList = new ArrayList<>();
        boolean[] possible = new boolean[10];
        for (int i = 2; i <= 9; i++) {
            possible[i] = true;
        }

        for (int i = 0; i < expressions.length; i++) {
            String[] parts = expressions[i].split(" ");
            String A = parts[0];
            String B = parts[2];
            String C = parts[4];

            if (C.equals("X")) {
                xList.add(i);
                continue;
            }

            int maxDigit = Math.max(Math.max(getMaxDigit(A), getMaxDigit(B)), getMaxDigit(C));
            minBase = Math.max(minBase, maxDigit + 1);

            for (int j = minBase; j <= 9; j++) {
                int aBase = Integer.parseInt(A, j);
                int bBase = Integer.parseInt(B, j);
                int cBase = Integer.parseInt(C, j);
                if (parts[1].equals("+")) {
                    if (aBase + bBase != cBase) {
                        possible[j] = false;
                    }
                } else {
                    if (aBase - bBase != cBase) {
                        possible[j] = false;
                    }
                }
            }
        }

        return 0;
    }

    private static int getMaxDigit(String s) {
        int maxDigit = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                maxDigit = Math.max(maxDigit, c - '0');
            } else {
                maxDigit = Math.max(maxDigit, c - 'A' + 10);
            }
        }
        return maxDigit;
    }
}
