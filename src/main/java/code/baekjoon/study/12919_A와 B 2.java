package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/12919
 *
 * 제한)
 *  1 ≤ S의 길이 ≤ 49
 *  2 ≤ T의 길이 ≤ 50
 *  S의 길이 < T의 길이
 *
 * 문제)
 *  1. 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임이다.
 *   - 문자열 뒤에 A를 추가한다.
 *   - 문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
 *  2. 주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 확인하기
 *   - 바꿀 수 있으면 1, 바꿀 수 없으면 0을 출력한다.
 *
 * 풀이)
 *  1. 문자열 S -> T로 바꾸는 방법 (시간초과), (메모리초과)
 *  2.  문자열 T -> S로 바꾸는 방법으로 DFS를 이용해서 탐색 사용
 *   - 재귀
 *  3. 문자열 T의 길이가 S의 길이보다 크므로, T의 길이가 S의 길이와 같아질 때까지
 *   - 문자열 T의 끝이 A로 끝나면, A를 제거하고 재귀 호출
 *   - 문자열 T의 시작이 B로 시작하면, B를 제거하고 문자열을 뒤집은 후 재귀 호출
 *
 */
class Problem12919 {

    private static String S, T;
    private static int ANSWER = 0;

    public static void main(String args[]) throws IOException {
        input();
        dfs(T);
        System.out.println(ANSWER);
    }

    private static void dfs(String current) {
        if (current.length() == S.length()) {
            if (current.equals(S)) {
                ANSWER = 1;
                return;
            }
            return;
        }
        if (current.endsWith("A")) {
            dfs(current.substring(0, current.length() - 1));
        }
        if (current.startsWith("B")) {
            StringBuilder reversed = new StringBuilder(current.substring(1)).reverse();
            dfs(reversed.toString());
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        S = st.nextToken();
        st = new StringTokenizer(br.readLine());
        T = st.nextToken();
        br.close();
    }
}
/*
A
BABA

1

BAAAAABAA
BAABAAAAAB

1

 */
