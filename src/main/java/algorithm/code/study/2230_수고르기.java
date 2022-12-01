package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 *
 * 제한)
 *     1 ≤ N ≤ 100,000
 *     0 ≤ M ≤ 2,000,000,000
 *     0 ≤ |A[i]| ≤ 1,000,000,000
 */

class Problem2230 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        if (M == 0) {
            System.out.println(0);
            return;
        }

        long[] numbers = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(numbers);

        long min = 2000000000;
        long calc = 0;
        int left = 0;
        int right = 1;

        while (right < N) {
            calc = numbers[right] - numbers[left];
            if (calc > M) {
                left++;
                min = Math.min(min, calc);
            }
            else if (calc < M || left == right) {
                right++;
            }
            else if (calc == M) {
                min = calc;
                break;
            }
        }
        System.out.println(min);
    }
}
/*
3 3
1
5
3

4
 */

