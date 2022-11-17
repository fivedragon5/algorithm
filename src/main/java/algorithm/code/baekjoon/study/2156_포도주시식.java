package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem2156 {

    /**
     1 <= n <= 10,000
     1 <= c <= 1000

     6 10 13 9 8 1
     */

    //DP[i] : i개의 잔중 최대로 마실 수 있는 값
    //DP[i] = DP[1] + DP[2] + ... + DP[i-1]
    //DP[1] = wines[0]
    //DP[2] = wines[0] + wines[1]
    //DP[3] = Math.max(wines[0] + wines[1], wines[1] + windes[2])
    //DP[4] = Math.max(wines[0] + wines[1] + wines[3], wines[1] + wines[2])
    //DP[5] = Math.max(wines[0] + wines[1] + wines[3] + wines[4], wines[1] + wines[2] + wines[4])
    // 1 3 1 1

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] wines = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            wines[i] = Integer.parseInt(st.nextToken());
        }
    }
}

/**
 6
 6
 10
 13
 9
 8
 1

 33
*/