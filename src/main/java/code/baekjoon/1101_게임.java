package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1103
 *
 * 제한)
 *  1 <= N, M <= 50
 *  1 <= board[n][m] <= 9 || 'H'
 *
 * 문제)
 *  1. 세로 N, 가로 M 이 주어진다.
 *  2. 각 칸에는 1 ~ 9, H 의 상태가 주어진다.
 *  3. 보드의 가장 왼쪽 위에 동전 하나를 올려 놓는다.
 *  4. 규칙에 따라 동전을 움직인다.
 *    - 동전이 있는 곳에 쓰여있는 숫자 X를 읽는다.
 *    - 위, 아래, 왼쪽, 오른쪽 방향 중에 한가지를 고른다.
 *    - 동전을 위에서 고른 방향으로 X만큼 움직인다. 이때, 중간에 있는 구멍은 무시한다.
 *    - 만약 동전이 구멍에 빠지거나, 보드의 바깥으로 나간다면 게임은 종료
 *  5. 최대 몇번 동전을 움직일 수 있는지 구하기 만약 동전을 무한번 움직일 경우 -1 반환
 *
 * 풀이)
 *  DFS + DP
 *  1. dfs(x, y) :x,y 에서 시작해서 최대로 움직일 수 있는 횟수
 *  2. 이미 계산한 칸은 dp[x][y]로 재사용 (중복 탐색 방지)
 *  3. DFS 중 현재 재귀 경로에서 다시 같은 칸을 만나면 무한 루프
 *
 */
class Problem1103 {

    private static int N, M;
    private static int[][] BOARD;
    private static int[][] DP; // 최장 이동 계산용
    private static boolean[][] visited;
    private static boolean isCycle = false;

    private static int[] DX = {-1, 1, 0, 0};
    private static int[] DY = {0, 0, -1, 1};

    public static void main(String args[]) throws IOException {
        input();
        DP = new int[N][M];
        visited = new boolean[N][M];

        int answer = dfs(0, 0);
        System.out.println(isCycle ? -1 : answer);
    }

    private static int dfs(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M || BOARD[x][y] == -1) {
            return 0; // 범위 밖 || 구멍
        }

        if (isCycle) {
            return 0; // 무한 루프
        }

        if (visited[x][y]) {
            isCycle = true;
            return 0;
        }

        if (DP[x][y] != 0) {
            return DP[x][y];
        }

        visited[x][y] = true;

        int k = BOARD[x][y];
        int best = 0;

        for (int d = 0; d < 4; d++) {
            int nx = x + DX[d] * k;
            int ny = y + DY[d] * k;

            int cand;
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || BOARD[nx][ny] == -1) {
                // 한번 움직인 뒤 게임 종료
                cand = 1;
            } else {
                cand = 1 + dfs(nx, ny);
            }

            best = Math.max(best, cand);
            if (isCycle) {
                break;
            }
        }

        visited[x][y] = false;
        DP[x][y] = best;
        return best;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        BOARD = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                BOARD[i][j] = (c == 'H') ? -1 : (c - '0');
            }
        }

        br.close();
    }
}
/*
3 7
3942178
1234567
9123532
 */
