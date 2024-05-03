/**
 *
 * https://www.acmicpc.net/problem/2565
 * 문제)
 * N : 구멍의 갯수
 * M : 부피
 * 제한)
 * 1 <= 전깃줄 <= 100
 * 1 <= 위치 <= 500
 * 접근)
 * 최장 부분 증가 수열(LIS)알고리즘
 */

package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.max;

class Problem2565 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;

        int telephonePoleCount = Integer.parseInt(st.nextToken());

        int[][] list = new int[telephonePoleCount][2];
        int[] dp = new int[101];

        for (int i = 0; i < telephonePoleCount; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            list[i][0] = first;
            list[i][1] = second;
        }

        Arrays.sort(list, (a1, a2) -> {return a1[0] - a2[0];});

        for (int i = 0; i < telephonePoleCount; i++) {
            dp[i] = 1;
            for (int j = 0; j < telephonePoleCount; j++) {
                if (list[j][1] < list[i][1]) {
                    dp[i] = max(dp[i], dp[j] + 1);
                }
            }
            answer = max(answer, dp[i]);
        }

        System.out.println(telephonePoleCount - answer);
    }
}
/*
8
1 8
3 9
2 2
4 1
6 4
10 10
9 7
7 6

3

4
1 4
3 2
2 1
4 3
 */



