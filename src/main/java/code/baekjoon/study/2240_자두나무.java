package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * https://www.acmicpc.net/problem/2240
 * 1 <= T <= 1,000
 * 1 <= W <= 30
 *
 * 1. 1번 나무에서 시작
 * 2. 최대 30번만 움직일 수 있음
 * 3. 최대 받을수 있는 자두의 최대 개수
 */

class Problem2240 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] plumTree = new int[T+1];
        // [T][W][position]([시간][움직인 횟수][위치]) = 최대 자두의 개수
        int[][][] dp = new int[T + 1][W + 1][2];

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            plumTree[i] = Integer.parseInt(st.nextToken());
        }

        // 초기값(1초) 셋팅
        if (plumTree[1] == 1) {
            dp[1][0][0] = 1;
            dp[1][1][1] = 0;
        } else {
            dp[1][0][0] = 0;
            dp[1][1][1] = 1;
        }

        // 2초부터 시작
        for (int i = 2; i <= T; i++) {
            int plum = plumTree[i];

            // 처음 부터 움직이지 X 예외 처리
            if (plum == 1) {
                dp[i][0][0] = dp[i-1][0][0] + 1;
                dp[i][0][1] = dp[i-1][0][1];
            }

            for (int j = 1; j <= W; j++) {
                if (plum == 1) {
                    dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j-1][1]) + 1; // 자두를 받은 경우
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0]); // 자두를 받지 않는 경우
                } else {
                    dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j-1][1]);
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0]) + 1;
                }
            }
        }

        int max = 0;
        // 움직이지 않았을 경우 최대값이 나올 수 있음
        for (int i = 0; i <= W; i++) {
            max = Math.max(max, Math.max(dp[T][i][0], dp[T][i][1]));
        }
        System.out.println(max);
    }
}

/*
1 1
1

7 3
2
1
1
2
2
1
1

 */

