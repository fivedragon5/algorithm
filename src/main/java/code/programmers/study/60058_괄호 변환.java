package code.programmers.study;

import java.util.Stack;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/60058
 *
 * 제한)
 *  p는 '(' 와 ')' 로만 이루어진 문자열이며 길이는 2 이상 1,000 이하인 짝수입니다.
 *  문자열 p를 이루는 '(' 와 ')' 의 개수는 항상 같습니다.
 *  만약 p가 이미 "올바른 괄호 문자열"이라면 그대로 return 하면 됩니다.
 *
 * 문제)
 *  1. ")", "(" 로 이루어진 문자열 w가 주어질 경우 올바른 괄호 분자열로 변환하기
 *   - 군형잡힌 괄호 문자열 : ")", "(" 의 개수가 같을 경우
 *   - 올바른 괄호 문자열 : 모든 괄호가 올바르게 짝지어져 있는 문자열
 *  올바른 괄호 문자열로 변환하는 방법
 *      1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
 *      2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
 *      3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
 *          3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
 *      4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
 *          4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
 *          4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
 *          4-3. ')'를 다시 붙입니다.
 *          4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
 *          4-5. 생성된 문자열을 반환합니다.
 *
 * 풀이)
 *  1. 문자열 p를 균형잡힌 괄호 문자열 u, v로 분리합니다.
 *  2. u가 올바른 괄호 문자열인지 확인합니다.
 *  3. u가 올바른 괄호 문자열이라면 v에 대해 재귀적으로 solution을 호출하고, u와 그 결과를 이어붙입니다.
 *  4. u가 올바른 괄호 문자열이 아니라면, 올바른 괄호 문자열로 변환하는 과정 수행
 *
 */
class Lesson60058 {
    public static void main(String[] args) {
        String p = "(()())()";
        System.out.println(solution(p));

        p = ")(";
        System.out.println(solution(p));

        p = "()))((()";
        System.out.println(solution(p));
    }

    private static int CUT_INDEX;

    public static String solution(String p) {
        if (p.isEmpty()) {
            return p;
        }

        boolean isBalanced = checkBalanced(p);
        String u = p.substring(0, CUT_INDEX);
        String v = p.substring(CUT_INDEX);
        if (isBalanced) {
            return u + solution(v);
        } else {
            // 올바른 괄호 문자열이 아닐 경우
            StringBuilder sb = new StringBuilder();
            sb.append('('); // 1. '(' 추가
            sb.append(solution(v)); // 2. v에 대해 재귀 호출
            sb.append(')'); // 3. ')' 추가

            // 4. u의 괄호를 뒤집어서 추가
            for (int i = 1; i < u.length() - 1; i++) {
                char c = u.charAt(i);
                if (c == '(') {
                    sb.append(')');
                } else {
                    sb.append('(');
                }
            }
            return sb.toString();
        }
    }

    private static boolean checkBalanced(String str) {
        int left = 0;
        int right = 0;
        boolean isBalanced = true;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')  {
                left++;
                stack.push('(');
            } else {
                right++;
                if (stack.isEmpty()) {
                    isBalanced = false;
                } else {
                    stack.pop();
                }
            }

            // 균형 잡힌 괄호 | u, v 나누는 지점
            if (left == right) {
                CUT_INDEX = i + 1; // 다음 인덱스부터 v 시작
                return isBalanced;
            }
        }
        return true;
    }
}
