package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2573
 * 제한)
 * 3 <= N, M <= 300
 * 0 <= 빙산의 크기 <= 10
 * [0][x] = 0, [x][0] = 0 고정
 * 문제)
 * 1. 1년마다 빙하는 동서남북 네 방향으로 붙어있는 바닷물에 따라 빙하가 녹음
 * 2. 빙산이 2덩어리 이상 분리되는 시점이 몇년후 인지 구하기
 *
 * 풀이)
 * 1. 반복
 *  -  빙하와 인접한 바닷물의 면 만큼 빙하의 높이를 뺴줌
 *  -  빙하가 남아있을경우 BFS돌려 이어진 빙하를 체크
 * 2. 덩어리가 2덩어리보다 클 경우 종료 후 년 수 출력
 * 3. 덩어리가 0일경우 0출력
 * 4. 덩어리리가 1 일경우 반복
 *
 */
class Problem2573 {

    private static int N, M;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] glaciers = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                glaciers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= 10000000; i++) {
            glaciers = meltGlaciers(glaciers);
            int icebergCount = checkGlacier(glaciers);
            if (icebergCount >= 2) {
                System.out.println(i);
                return;
            } else if (icebergCount == 0) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(0);
    }

    private static int[][] meltGlaciers(int[][] glaciers) {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (glaciers[i][j] > 0) { // 빙하가 있는 곳만 탐색
                    int count = 0; // 주변 0의 개수
                    for (int k = 0; k < 4; k++) { // 상하좌우 확인
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        // 범위 내에 있고 주변이 0인 경우
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && glaciers[nx][ny] == 0) {
                            count++;
                        }
                    }
                    // 현재 위치의 높이에서 감소
                    temp[i][j] = Math.max(glaciers[i][j] - count, 0);
                }
            }
        }
        return temp;
    }

    private static int checkGlacier(int[][] glaciers) {
        boolean[][] visited = new boolean[N][M]; // 방문 여부 저장
        int icebergCount = 0; // 빙산 덩어리 수

        // 모든 빙산 좌표를 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (glaciers[i][j] > 0 && !visited[i][j]) { // 빙산이고 방문하지 않은 경우
                    icebergCount++;
                    if (icebergCount > 1) { // 빙산이 두 덩어리 이상이면 true 반환
                        return icebergCount;
                    }
                    dfs(i, j, glaciers, visited, dx, dy); // DFS로 탐색
                }
            }
        }
        return icebergCount; // 빙산이 한 덩어리라면 false 반환
    }

    // DFS 함수
    private static void dfs(int x, int y, int[][] glaciers, boolean[][] visited, int[] dx, int[] dy) {
        visited[x][y] = true; // 현재 위치 방문 처리

        for (int k = 0; k < 4; k++) { // 상하좌우 탐색
            int nx = x + dx[k];
            int ny = y + dy[k];

            // 범위 내에 있고 빙산이며 아직 방문하지 않은 경우
            if (nx >= 0 && nx < N && ny >= 0 && ny < M && glaciers[nx][ny] > 0 && !visited[nx][ny]) {
                dfs(nx, ny, glaciers, visited, dx, dy);
            }
        }
    }
}
/*
5 5
0 0 0 0 0
0 10 10 10 0
0 9 9 9 0
0 10 10 10 0
0 0 0 0 0
 */
