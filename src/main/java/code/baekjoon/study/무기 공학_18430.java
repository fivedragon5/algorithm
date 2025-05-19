package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/18430
 *
 * 제한)
 *  1 ≤ N, M ≤ 5
 *  1 ≤ K ≤ 100
 *
 * 문제)
 *  1. N * M 크기의 직사각형 형태가 주어진다.
 *  2. 각 칸은 고급 나무의 재료이며 재료 부위마다 강도가 조금씩 다르다.
 *  3. 각 칸을 나눠 'ㄱ', 'ㄴ' 모양의 3칸으로 부메랑을 만들 수 있다.
 *   - 부메랑의 중심이 되는 칸은 강도의 영향을 2배로 받는다.
 *  4. 만들 수 있는 부메랑의 강도 합의 최 값을 찾기
 *   단 나무의 크기가 너무 작아 부메랑을 만들 수 없을 경우 0 출력
 *
 * 풀이)
 *   DFS O(N*M*4) M*N/3 : 3칸 고려
 *      1. 0,0 에서부터 DFS진행(백트레킹)
 *       - 각 칸에서 부메랑을 만들 수 있는지 확인
 *       - 부메랑을 만들 수 있을 경우 부메랑의 강도 합을 누적해서 더해주고 다음 칸으로 이동
 *       - 다음칸으로 이동할때 1차원 배열로 변환해서 사용
 *
 */
class Problem18430 {

    private static int N;
    private static int M;
    private static int[][] trees;
    private static int[][][] boomerangType = new int[][][]{
            {{1,0},{0,1}}, // ㄱ
            {{1,0},{0,-1}}, // ㄱ(뒤집기)
            {{-1,0},{0,1}}, // ㄴ
            {{-1,0},{0,-1}}, // ㄴ(뒤집기)
    };
    private static int MAX = 0;

    public static void main(String args[]) throws IOException {
        input();
        boolean[][] visited = new boolean[N][M];
        dfs(0,0, visited);
        System.out.println(MAX);
    }

    private static void dfs(int point, int sum, boolean[][] visited) {
        MAX = Math.max(MAX, sum);
        for (int i = point; i < M * N; i++) {
            int x = i / M;
            int y = i % M;

            if (visited[x][y]) {
                continue;
            }
            for (int k = 0; k < 4; k++) {
                int moveX1 = x + boomerangType[k][0][0];
                int moveY1 = y + boomerangType[k][0][1];
                int moveX2 = x + boomerangType[k][1][0];
                int moveY2 = y + boomerangType[k][1][1];
                if (canPlaceBoomerang(moveX1, moveY1, moveX2, moveY2, visited)) {
                    visited[x][y] = true;
                    visited[moveX1][moveY1] = true;
                    visited[moveX2][moveY2] = true;
                    dfs(i + 1 ,sum + trees[x][y] * 2 + trees[moveX1][moveY1] + trees[moveX2][moveY2], visited);
                    visited[x][y] = false;
                    visited[moveX1][moveY1] = false;
                    visited[moveX2][moveY2] = false;
                }
            }

        }
    }

    // 부메랑을 만들 수 있는지 확인하는 메소드
    private static boolean canPlaceBoomerang(int x1, int y1, int x2, int y2, boolean[][] visited) {
        // 불가능
        if (x1 < 0 || y1 < 0 || x1 >= N || y1 >= M || visited[x1][y1]) {
            return false;
        }
        // 불가능
        if (x2 < 0 || y2 < 0 || x2 >= N || y2 >= M || visited[x2][y2]) {
            return false;
        }
        return true;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                trees[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
/*
3 3
32 83 75
24 96 56
71 88 12

2 3
7 5 4
3 2 9


 */

