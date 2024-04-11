package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 접근)
 * 1. K(Q) Q에 어떤 숫자가 들어가 있는지는 중요하지 않다.
 *  - Q의 자리수와 괄호의 depth가 중요
 * 2. stack를 활용하여 depth를 구분
 *
 * 제한)
 * S의 길이는 최대 50이다. 문자열은 (, ), 0-9사이의 숫자로만 들어온다.
 */

class Problem1662 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String text = st.nextToken();

        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        int textCount = 0;
        char ch;

        for (int i = text.length() - 1; i >= 0; i--) {
            ch = text.charAt(i);
            if (ch == ')') {
                stack.add(textCount);
                textCount = 0;
            }
            else if (ch == '(') {
                i--;
                int repeat = Character.getNumericValue(text.charAt(i));
                textCount = textCount * repeat + stack.pop();
            }
            else {
                if (stack.size() == 0) {
                    answer++;
                }
                else {
                    textCount++;
                }
            }
        }
        System.out.println(answer + textCount);
    }
}
/*
33(562(71(9)))
19

123
3

10342(76)
8

0(0)
0

1(1(1(1(1(1(1(0(1234567890))))))))
0

1()66(5)
7

 */

