package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem2133 {
    public static void main(String args[]) throws IOException {
        // 3*n 크기의 벽을 2*1, 1*2크기의 타일로 채우는 경우의 수를 구해보자.
        // 1 <= N <= 30

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[N+1];

        if(N%2 != 0) {
            System.out.println(0);
            return;
        }

        dp[2] = 3;

        //1.최초 풀이
        // - 이전 경우의 수에 *3(N(2)경우의 수 곱) *2(좌우반전) +2(특이케이스) 만 생각해서 오답
//        for (int i = 6; i <= dp.length; i = i+2) {
//            dp[i] = dp[i-2] * 3 * 2 + 2;
//        }

        //2.최초풀이생각 + (N-4)의 경우의 수에 *2(좌우반전까지 생각해주어야 함)
//        for (int i = 6; i <= dp.length; i = i+2) {
//            dp[i] = dp[i-2] * 3 + (dpsum*2) + 2;
//            dpsum = dpsum + dp[i];
//        }

        //3.d[i] = d[i-2] * 3 + (d[i-4] + d[i-6] ......) + 2
        for (int i = 4; i < dp.length; i+=2) {
            dp[i] = dp[i-2] * 3;
            for (int j = 4; j < i; j+=2) {
                dp[i] += dp[i-j] * 2;
            }
            dp[i] += 2;
        }

        System.out.println(dp[N]);
    }
}

