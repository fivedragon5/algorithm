package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 접근)
 *  1. stack
 *
 * 풀이)
 *  1. 문자열을 한글자씩 Character형 stack에 넣는다
 *  2. 넣으면서 PPAP가 되면 -> P만 남기고 pop
 *      -  정확히는 PPAP의 마지막P를 push하기전에 PPAP가 완성되면
 *         PPA인 상태에서 2번 pop를 해준다.
 *  3. stack에 마지막 남은 문자가 P일경우 PPAP 출력 아니라면 NP출력
 *
 * 제한)
 * 1 <= length <= 1,000,000
 */
class Problem16120 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String ppapStr = st.nextToken();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < ppapStr.length(); i++) {
            Character ch = ppapStr.charAt(i);

            if (stack.size() >= 3 && ch == 'P' && stack.peek() == 'A') {
                int size = stack.size();

                if (stack.get(size-2) == 'P' && stack.get(size-3) == 'P') {
                    stack.pop();
                    stack.pop();
                }
            }
            else {
                stack.push(ch);
            }
        }

        System.out.println((stack.size() == 1 && stack.peek() == 'P') ? "PPAP" : "NP");

    }
}
/*
PPPAPAP

PPAPAPP
 */

