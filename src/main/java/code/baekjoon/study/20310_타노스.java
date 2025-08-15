package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/20310
 *
 * 제한)
 * 2 <= S.length <= 500
 * S는 짝수개의 0과 짝수개의 1로 이루어져 있다.
 *
 * 문제)
 *  1. 주어진 문자열 S에서 0과 1의 개수가 짝수개로 주어진다.
 *  2. 타노스가 S를 구성하는 문자 중 절반의 0과 절반의 1을 제거하여 새로운 문자열 S1을 만든다.
 *  3. S1으로 가능한 문자열 중 사전순으로 가장 빠른 것을 구하기
 *
 * 풀이)
 *  1. 문자열 S에서 0과 1의 개수를 세어 각각의 절반을 제거할 개수를 계산한다.
 *  2. 문자열 S를 순회하면서 앞에서부터 1을 제거할 개수만큼 제거하고, 뒤에서부터 0을 제거할 개수만큼 제거한다.
 *  3. 제거되지 않은 문자들만 모아서 새로운 문자열 S1을 만든다.
 *  4. 최종적으로 만들어진 문자열 S1을 출력한다.
 *
 */
class Problem20310 {

    private static String S;

    public static void main(String args[]) throws IOException {
        input();
        StringBuilder sb = new StringBuilder();
        int zeroCount = 0;
        int oneCount = 0;

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '0') {
                zeroCount++;
            } else {
                oneCount++;
            }
        }

        int removeZeroCount = zeroCount / 2;
        int removeOneCount = oneCount / 2;

        boolean[] removed = new boolean[S.length()];

        // 앞에서 부터 1을 removeOneCount 만큼 제거
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '1' && removeOneCount > 0) {
                removed[i] = true;
                removeOneCount--;
            }
        }

        // 뒤에서 부터 0을 removeZeroCount 만큼 제거
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) == '0' && removeZeroCount > 0) {
                removed[i] = true;
                removeZeroCount--;
            }
        }

        // 제거되지 않은 문자만 sb에 추가
        for (int i = 0; i < S.length(); i++) {
            if (!removed[i]) {
                sb.append(S.charAt(i));
            }
        }

        System.out.println(sb.toString());
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        S = st.nextToken();
    }
}
/*
1010
01

000011
001

 */
