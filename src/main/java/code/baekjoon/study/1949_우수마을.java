package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1949
 *
 * 제한)
 *   1 ≤ N ≤ 10,000
 *   1 ≤ 마을 주민 수 ≤ 10,000
 *
 * 문제)
 *  이 나라는 트리구조로 되어있다 각 마을은 마을 사이를 직접 잇는 길 N-1개의 길이 있으며 각 길은 방향성은 없다.
 *  두 마을 사이에 직접 잇는 길이 있을 경우 각 마을은 인접해 있다고 표현한다.
 *  다음 세가지 조건을 만족하면서 N개의 마을중 몇개의 마을을 '우수 마을'로 선정하려고 한다.
 *   1. 우수 마을로 선정된 마을 주민 수의 총 합을 최대로 해야 한다.
 *   2. 마을 사이의 충돌을 방지하기 위해서, 만일 두 마을이 인접해 있으면 두 마을을 모두 '우수 마을'로 선정할 수는 없다. 즉 '우수 마을'끼리는 서로 인접해 있을 수 없다.
 *   3. 선정되지 못한 마을에 경각심을 불러일으키기 위해서, '우수 마을'로 선정되지 못한 마을은 적어도 하나의 '우수 마을'과는 인접해 있어야 한다.
 *  각 마을 주민 수와 길에대한 정보가 주어졌을 때, 주어진 조건을 만족하는 우수마을 주민의 총 합을 출력하기
 *
 * 풀이)
 *  DP
 *   dp[i][0] = i가 우수마을로 선정되지 않았을 때
 *   dp[i][1] = i가 우수마을로 선정되었을 경우
 *  1. 재귀를 사용하여 Root 노드부터 호출 해서 가장 하단의 노드부터 값을 채우는 방식이다 (Bottom-up)
 *  2. 현재 마을이 '우수마을'일 경우, '우수마을'이 아닐 경우로 나눠서 계산
 *   - 현재 마을이 '우수마을' 일 경우 : 자식 마을이 우수마을 이 아닐 경우에 대한 값을 dp[current][1]에 더해준다
 *   - 현재 마을이 '우수마을' 이 아닐 경우 : 자식 마을이 우수마을, 우수마을이 아닐 경우 중 Max 값을 dp[current][0] 에 더해준다
 *
 */

class Problem1949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 마을의 수
        int[] countryPopulations = new int[N + 1]; // 각 마을의 인구 수
        st = new StringTokenizer(br.readLine());
        List<List<Integer>> countries = new ArrayList<>(); // 각 마을은 연결한 정보
        countries.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            countryPopulations[i] = Integer.parseInt(st.nextToken());
            countries.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());
            countries.get(city1).add(city2);
            countries.get(city2).add(city1);
        }
        // dp[i][0] = i가 우수마을로 선정되지 않았을 때
        // dp[i][1] = i가 우수마을로 선정되었을 경우

        int[][] dp = new int[N+1][2];

        solveDp(-1, 1, countries, countryPopulations, dp);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void solveDp(int parentCountry, int currentCountry, List<List<Integer>> countries, int[] countryPopulations, int[][] dp) {
        dp[currentCountry][0] = 0;
        dp[currentCountry][1] = countryPopulations[currentCountry];

        for (int c : countries.get(currentCountry)) {
            if (parentCountry != c) {
                solveDp(currentCountry, c, countries, countryPopulations, dp);
                // 현재 마을이 우수 마을이 아닐 경우
                dp[currentCountry][0] += Math.max(dp[c][0], dp[c][1]);
                // 현재 마을이 우수 마을일 경우
                dp[currentCountry][1] += dp[c][0];
            }
        }
    }
}
/*
7
1000 3000 4000 1000 2000 2000 7000
1 2
2 3
4 3
4 5
6 2
6 7
 */
