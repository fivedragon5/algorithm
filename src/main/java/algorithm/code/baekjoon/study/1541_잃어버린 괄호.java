package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 접근)
 *  1. 문자열을 한 글자씩 순회 후 문자, 연산자 분리후 계산
 *
 * 풀이)
 *  1. 문자열을 순회 하면서 한글자씩 저장하다가 연산자를 만나면 저장했던 문자를
 *      int로 cast후 저장 numbers
 *      - 이 때 연산자도 저장 (마이너스 일 경우 첫번째로 찾은 마이너스의 index도 저장)
 *  2. 숫자들을 순회 하면서 마이너스 연산자가 시작되는 시점부터는 전부 -
 *      그 이전에는 +시켜준 값이 최소값
 *
 * 제한)
 *  가장 처음과 마지막 문자는 숫자
 *  연속해서 2개 이상의 연산자가 나타나지 않음
 *  5자리보다 많이 연속되지 않은 숫자는 없다
 *  수는 0으로 시작 할 수 있다
 */
class Problem1541 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> numbers = new ArrayList();
        ArrayList<Character> operator = new ArrayList<>();
        int start = 100;
        boolean isFlag = false;

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '+' || ch == '-') {
                numbers.add(Integer.parseInt(sb.toString()));
                operator.add(ch);
                sb = new StringBuilder();
                if (!isFlag && ch == '-') {
                    start = operator.size();
                    isFlag = true;
                }
            }
            else {
                sb.append(ch);
            }
        }

        numbers.add(Integer.parseInt(sb.toString()));
        int answer = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            if (start <= i) {
                answer -= numbers.get(i);
            }
            else {
                answer += numbers.get(i);
            }
        }
        System.out.println(answer);
    }
}
/*
55-50+40

-35

10+20+30+40

100

00009-00009

0
 */

