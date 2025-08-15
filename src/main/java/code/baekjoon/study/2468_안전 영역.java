package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2468
 *
 * 제한)
 * 2 <= N <= 100
 * 1 <= 높이 <= 100
 *
 * 문제)
 *  1. N*N 크기의 지도에 높이가 주어진다.
 *  2. 비가 오면 높이가 주어진 만큼 물에 잠긴다.
 *  3. 물에 잠기지 않는 안전한 영역의 개수를 구하라.
 *   - 안전한 영역 : 물에 잠기지 않는 영역
 *  4. 비가 오지 않는 경우를 포함하여, 물에 잠기지 않는 안전한 영역의 최대 개수를 구하라.
 *
 * 풀이)
 *  1. 입력으로 주어진 높이 중에서 중복을 제거한 후, 우선순위 큐에 넣는다.
 *  2. 우선순위 큐에서 높이를 하나씩 꺼내면서 해당 높이보다 높은 영역을 탐색한다.
 *  3. 탐색을 위해 DFS를 사용한다.
 *  4. 탐색을 통해 안전한 영역의 개수를 구하고, 그 중 최대값을 구한다.
 *
 */
class Problem2468 {

    private static int N;
    private static int[][] MAP;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int ANSWER = 1;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String args[]) throws IOException {
        input();

        while (!pq.isEmpty()) {
            int rainHeight = pq.poll();
            int safeAreaCount = getSafeArea(rainHeight);
            ANSWER = Math.max(ANSWER, safeAreaCount);
        }

        System.out.println(ANSWER);
    }

    private static int getSafeArea(int rainHeight) {
        boolean[][] visited = new boolean[N][N];
        int safeAreaCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (MAP[i][j] > rainHeight && !visited[i][j]) {
                    dfs(i, j, rainHeight, visited);
                    safeAreaCount++;
                }
            }
        }
        return safeAreaCount;
    }

    private static void dfs(int x, int y, int rainHeight, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && MAP[nx][ny] > rainHeight) {
                dfs(nx, ny, rainHeight, visited);
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        MAP = new int[N][N];
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int height = Integer.parseInt(st.nextToken());
                MAP[i][j] = height;
                if (set.add(height)) {
                    pq.add(height);
                }
            }
        }
    }
}
/*
4 3
0
2 1 2
1 3
3 2 3 4

 */
