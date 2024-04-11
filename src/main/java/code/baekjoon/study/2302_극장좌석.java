package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 조건)
 * 1 <= N <= 40
 * 0 <= M <= N
 * 고정석의 번호 오름차순
 * 방법의 가짓수는 2,000,000,000을 넘지 않는다. (2,000,000,000 < 2^31-1)
 *
 * ex) N(1) = 1
 * N=2 {1,2}, {2,1} 2^1 N(2) = 2
 * N=3 {1,2,3}, {2,1,3}, {1,3,2} N(3) = 3
 * N=4 {1,2,3,4}, {2,1,3,4}, {1,3,2,4}, {2,1,4,3}, {1,2,4,3} N(4) = 5
 * N=5 {1,2,3,4,5}, {1,2,4,3,5}, {1,2,3,5,4}, {1,3,2,4,5}, {1,3,2,5,4}, {2,1,3,4,5}, {2,1,3,5,4}, {2,1,4,3,5}, {2,1,3,5,4} N(5) = 8
 */

class Problem2302 {

    static int N, M;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());

        int[] vipSeats = new int[M];
        int[] dp = new int[41];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            vipSeats[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }

        int answer = 1;

        if (M == 0) {
            System.out.println(dp[N]);
        }

        else {

            int before = 0;

            for (int i = 0; i < M; i++) {
                int nextVipSeat = vipSeats[i];
                answer *= dp[nextVipSeat - before - 1];
                before = nextVipSeat;
            }

            answer *= dp[N-before];

            System.out.println(answer);

        }
    }
}

/*
9
2
4
7

12

40
3
10
20
30

3
3
1
2
3

9
0
 */
