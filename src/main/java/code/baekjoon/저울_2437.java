package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2437
 * 제한)
 * 1 <= N <= 1,000
 * 1 <= 추 의무게 <= 1,000,000
 *
 * 문제)
 * 1. 주어진 추 들로 측정할 수 없는 양의 정수 무게 중 최소값을 구하기
 *
 * 풀이)
 * 1. 부터 모든 무게의 합을 확인하면서 연속합이 끊기는 부분을 구하기 - 시간초과
 * 2. 풀이 참고
 *  - W만큼의 무게를 측정 할 수 있다고 가정하고
 *  - 무게가 X인 저울 추가 추가될 경우 측정할 수 있는 무게는 (1 ~ W) + X
 *      - 1 + X, 2 + X ... W + X
 *      - 가정1 1, 2, 3 의 무게추가 있을 경우 측정할 수 있는 무게는 1, 2, 3, 1+3, 2+5 ( 1 ~ 6 ) W = 6
 *          - ex1) 가정1 에서 무게가 2인 무게추가 추가될 경우 1+2, 2+2, 3+2 ... 6+2 ( 3 ~ 7 ) W = 8
 *          - ex2) 가정1 에서 무게가 8인 무게추가 추가될 경유 8, 1+8, 2+8 ... 6+8 ( 8 ~ 14 ) W = 14 | (1 ~ 6) (8 ~ 14)
 *              - 공백 발생
 */
class Problem2437 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 추의 수

        int[] weights = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights);

        int sum = 1;
        for (int i = 0 ; i < N; i++) {
            if (sum < weights[i]) {
                break;
            }
            sum += weights[i];
        }
        System.out.println(sum);
    }
}
/*
7
3 1 6 2 7 30 1
 */
