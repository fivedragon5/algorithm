package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1033
 *
 * 제한)
 *   1 <= N <= 10
 *   0 <= 재료 <= N-1
 *   0 <= a,b <= N-1
 *   1 <= p,q <= 9
 *
 * 문제)
 *  1. august14는 세상에서 가장 맛있는 칵테일이다. 이 칵테일을 만드는 방법의 재료 N개는 공개되어 있다.
 *  2. 총 재료 쌍 N-1개의 비율이 입력으로 주어진다. 이때, 칵테일을 만드는데 필요한 각 재료의 양을 구하기
 *   - 필요한 재료의 질량을 모두 더한 값이 최소가 되어야 함
 *   - 칵테일을 만드는 재료의 양은 정수
 *   - 총 질량을 0보다 커야함
 *  3. 비율은 "a b p q"로 주어지며, a번 재료의 질량을 b번 재료의 질량으로 나눈 값이 p/q임을 의미
 *
 * 풀이)
 *  1. 그래프를 인접 리스트로 표현
 *  2. 모든 재료의 비율을 구하기 위해 DFS 사용
 *  3. 모든 재료의 비율을 구한 후, 최대공약수를 이용해 비율을 가장 간단한 형태로 변환
 *  4. 각 재료의 비율을 출력
 *
 */
class Problem1033 {

    private static int N;
    private static List<int[]>[] graph;
    private static long[] ratios;
    private static long totalLcm = 1;

    public static void main(String args[]) throws IOException {
        input();
        ratios[0] = totalLcm;
        boolean[] visited = new boolean[N];

        dfs(0, visited);

        long finalGcd = ratios[0];
        for (int i = 1; i < N; i++) {
            finalGcd = gcd(finalGcd, ratios[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ratios[i] / finalGcd).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(int current, boolean[] visited) {
        visited[current] = true;
        for (int[] neighbor : graph[current]) {
            int next = neighbor[0];
            int p = neighbor[1];
            int q = neighbor[2];

            if (!visited[next]) {
                ratios[next] = ratios[current] * q / p;
                dfs(next, visited);
            }
        }
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        graph = new List[N];
        ratios = new long[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, p, q});  // a:b = p:q
            graph[b].add(new int[]{a, q, p});  // b:a = q:p

            totalLcm *= (p * q) / gcd(p, q);
        }
        br.close();
    }
}
/*
4
2 2 2 2
 */
