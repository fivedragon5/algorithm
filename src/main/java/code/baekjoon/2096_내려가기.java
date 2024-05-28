package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * https://www.acmicpc.net/problem/2096
 * 제한)
 * 0 <= N <= 100,000
 * DP
 */

class Problem2096 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] dp_max = new int[3];
        int[] dp_min = new int[3];

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            int firstNumber = Integer.parseInt(st.nextToken());
            int secondNumber = Integer.parseInt(st.nextToken());
            int thirdNumber = Integer.parseInt(st.nextToken());

            int a = Math.max(dp_max[0], dp_max[1]) + firstNumber;
            int b = getMax(dp_max[0], dp_max[1], dp_max[2]) + secondNumber;
            int c = Math.max(dp_max[1], dp_max[2]) + thirdNumber;

            dp_max[0] = a;
            dp_max[1] = b;
            dp_max[2] = c;

            a = Math.min(dp_min[0], dp_min[1]) + firstNumber;
            b = getMin(dp_min[0], dp_min[1], dp_min[2]) + secondNumber;
            c = Math.min(dp_min[1], dp_min[2]) + thirdNumber;

            dp_min[0] = a;
            dp_min[1] = b;
            dp_min[2] = c;
        }

        int max = Math.max(dp_max[0], dp_max[1]);
        max = Math.max(max, dp_max[2]);

        int min = Math.min(dp_min[0], dp_min[1]);
        min = Math.min(min, dp_min[2]);

        System.out.println(max + " " + min);
    }

    private static int getMax(int a, int b, int c) {
        int max = Math.max(a, b);
        return Math.max(max, c);
    }

    private static int getMin(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }
}

