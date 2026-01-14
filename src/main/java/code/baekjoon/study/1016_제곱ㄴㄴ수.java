package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1016
 *
 * 제한)
 *  1 <= min <= 1,000,000,000,000
 *  min <= max <= min + 1,000,000
 *
 * 문제)
 *  1. 어떤 정수 X가 1보다 큰 제곱수로 나누어 떨어지지 않을 때, 그 수를 제곱ㄴㄴ수라고 한다.
 *  2. min, max가 주어지면, min보다 크거나 같고, max보다 작거나 같은 제곱ㄴㄴ수의 개수를 구하기
 *
 * 풀이)
 *  에라토스테네스의 체
 *  1. MIN ~ MAX 까지 제곱수를 구하기엔 범위가 너무 크므로 시간초과
 *  2. MIN ~ MAX 범위 내의 수들 중에서 제곱수의 배수를 제외하는 방식으로 제곱ㄴㄴ수를 구함
 *
 */
class Problem1016 {

    private static long MIN, MAX;

    public static void main(String args[]) throws IOException {
        input();

        // 총 제곱ㄴㄴ수 개수
        long nnCount = (MAX - MIN + 1);
        // 제곱수 시작 값
        long i = 2;
        // 제곱ㄴㄴ수가 아닌 수 체크 배열
        boolean[] isNotNN = new boolean[(int) (MAX - MIN + 1)];

        // 에라토스테네스의 체
        while (i * i <= MAX) {
            long square = (long) i * i;
            // 제곱수의 최소 배수 구하기
            long start = MIN / square;

            if (MIN % square != 0) {
                start++;
            }

            while (start * square <= MAX) {
                long value = start * square;
                if (!isNotNN[(int) (value - MIN)]) {
                    isNotNN[(int) (value - MIN)] = true;
                    nnCount--;
                }
                start++;
            }

            i++;
        }
        System.out.println(nnCount);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        MIN = Long.parseLong(st.nextToken());
        MAX = Long.parseLong(st.nextToken());
        br.close();
    }
}
/*
1 1000
608
 */
