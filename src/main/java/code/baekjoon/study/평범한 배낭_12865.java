package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/12865
 * 제한)
 *  1 ≤ N ≤ 100
 *  1 ≤ K ≤ 100,000
 *  1 ≤ W ≤ 100,000
 *  1 ≤ V ≤ 1,000
 *
 * 문제)
 *  1. 각 물건은 무게 W와 가치 V를 가진다.
 *  2. 준서는 최대 K만큼에 무게만을 넣을 수 있는 배낭을 들 고 다닐 수 있다.
 *  3. 준서가 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력하기
 *
 * 풀이)
 *  DP
 *   1. DP[i][j] : i번째 물건 까지중 최대 무게가 j일 경우 가치의 최대값
 *   2. j >= weight : 현재 배낭 용량 j가 현재 물건의 무게 weight 보다 작으면, 물건을 담을 수 없음
 *    - else : 이전 물건까지의 최대 가치 유지
 *    - DP[i - 1][j - weight] + value, DP[i - 1][j] 중 더 큰 가치를 선택해서 DP[i][j] 에 저장
 */
class Problem12865 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물품의 수
        int K = Integer.parseInt(st.nextToken()); // 준서가 버틸 수 있는 무게

        int[][] goods = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            goods[i][0] = weight;
            goods[i][1] = value;
        }
        int[][] DP = new int[N+1][K+1]; // dp[i][j] : 물건 i, 배낭의 최대무게 j

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                int weight = goods[i-1][0];
                int value = goods[i-1][1];
                // 현재 물건을 넣는 경우
                if (j >= weight) {
                    DP[i][j] = Math.max(DP[i - 1][j - weight] + value, DP[i - 1][j]);
                } else {
                    DP[i][j] = DP[i - 1][j];
                }
            }
        }
        System.out.println(DP[N][K]);
    }
}
/*
4 7
6 13
4 8
3 6
5 12


 */
