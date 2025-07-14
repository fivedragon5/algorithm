package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1
 *
 * 제한)
 *
 * 문제)
 *
 * 풀이)
 *
 */
class ProblemBaekjoonSmaple {

    private static int N, M;
    public static void main(String args[]) throws IOException {
        input();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }
}
/*
 */

