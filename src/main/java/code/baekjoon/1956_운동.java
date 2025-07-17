package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1956
 *
 * 제한)
 *  2 ≤ V ≤ 400
 *  0 ≤ E ≤ V (V-1)
 *
 * 문제)
 *  1. V개의 마을과 E개의 도로로 구성되어 있는 도시가 있다.
 *  2. 도로는 마을과 마을 사이에 놓여 있으면 일방 통행 도로 이다.
 *  3. 도로의 정보가 주어졌을 때, 도로의 합이 가장 작은 사이클을 찾기
 *
 * 풀이)
 *  플로이드와샬 알고리즘 적용 : 모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구하기
 *      D(ab) = Math.min(D(ab), D(ai) + D(ib))
 *      a->b  = Math.min(a->b,   a->i->b)
 *      작은 값으로 갱신
 *  정답을 MAX_VALUE로 놓고 모든 경로에 대해 탐색시 MAX_VALUE 값이 갱신되지 않았을 경우,
 *  싸이클이 연결되어 있지 않다고 판단 -1을 반환한다.
 */
class Problem1956 {

    private static int V, E;
    private static int[][] GRAPH;
    private static final int MAX_VALUE = 400 * 10000 * 2;

    public static void main(String args[]) throws IOException {
        input();

        for (int i = 1; i <= V; i++) {
            for (int a = 1; a <= V; a++) {
                if (i == a) continue;
                for (int b = 1; b <= V; b++) {
                    if (b == i || a == b) continue;
                    GRAPH[a][b] = Math.min(GRAPH[a][b], GRAPH[a][i] + GRAPH[i][b]);
                }
            }
        }

        int answer = MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            for (int j = i + 1; j <= V; j++) {
                if (GRAPH[i][j] != MAX_VALUE && GRAPH[j][i] != MAX_VALUE) {
                    answer = Math.min(answer, GRAPH[i][j] + GRAPH[j][i]);
                }
            }
        }
        if (answer == MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        GRAPH = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            Arrays.fill(GRAPH[i], MAX_VALUE);
            GRAPH[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            GRAPH[start][end] = cost;
        }
    }
}
/*
3 4
1 2 1
3 2 1
1 3 5
2 3 2

 */
