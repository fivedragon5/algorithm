package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제)
 * N : 구멍의 갯수
 * M : 부피
 * 
 * 접근)
 * 막는 구멍이 연속되어야 한다 라는 문구를 보고 투포인터를 떠올렸다.
 * 1. 투포인터
 * 2. 최대 부피 갱신
 *
 * 제한)
 * 1 <= N <= 500000
 * 1 <= M <= 10^9
 * 1 <= Ai <= 10^9
 */
class Problem25916 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] holes = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            holes[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = holes[0];
        int max = 0;

        while (right < N) {
            if (sum > M) {
                sum -= holes[left];
                left++;
            }
            else {
                right++;
                if (right >= N) break;
                sum += holes[right];
            }

            if (sum <= M) {
                max = Math.max(max , sum);
            }
        }
        System.out.println(max);
    }
}
/*
8 10
2 2 2 2 11 2 5 2

9
 */

