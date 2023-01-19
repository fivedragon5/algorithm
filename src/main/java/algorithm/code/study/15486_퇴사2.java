package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 접근)
 * 1. dp
 * 2. 뒤에서 부터 계산
 *
 * 풀이)
 * 1. 최대로 나올 수 있는 dp 배열 생성 1500000 + 50
 *  - dp[i]: i일에 낼수 있는 최대 수익
 * 2. 퇴사 날짜부터 현재일까지 탐색
 *  1. i일에 상담을 하는 경우
 *      - dp[i] = Math.max(dp[i+1], P[i] + dp[i + T[i]])
 *      - i+1일 일때의 최대 수익, i일 일때 최대수익 + 현재일에 잡혀있는 상담의 금액
 *  2. i일에 상담을 하지 않는 경우
 *      - dp[i] = dp[i+1]
 * 
 * 제한)
 * 1 ≤ N ≤ 1,500,000
 * 1 ≤ Ti ≤ 50
 * 1 ≤ Pi ≤ 1,000
 */
class Problem15486 {

    static int N;
    static int[][] schedule;
    static long[] dp;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        schedule = new int[N][2];
        dp = new long[1500051];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = N-1; i >= 0; i--) {
            if (i + schedule[i][0] > N) {
                dp[i] = dp[i+1];
            }
            else {
                dp[i] = Math.max(dp[i+1], schedule[i][1] + dp[i + schedule[i][0]]);
            }
        }
        System.out.println(dp[0]);
    }
}
/*
7
3 10
5 20
1 10
1 20
2 15
4 40
2 200

45

10
1 1
1 2
1 3
1 4
1 5
1 6
1 7
1 8
1 9
1 10

55
 */

