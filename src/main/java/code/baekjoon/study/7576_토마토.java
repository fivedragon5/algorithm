package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/7576
 *
 * 제한)
 *  2 ≤ M,N ≤ 1,000
 *
 * 문제)
 *  1. M x N 상자에 토마토가 들어있음
 *  2. 1 : 익은 토마토, 0 : 익지 않은 토마토, -1 : 토마토 없음
 *  3. 익은 토마토는 상하좌우에 있는 익지 않은 토마토를 익게 만듬
 *  4. 며칠이 지나면 토마토가 모두 익는지, 모두 익지 못하는 상황인지 구하기
 *
 * 풀이)
 *  BFS
 *  1. 익은 토마토의 위치를 모두 큐에 넣고 시작
 *  2. 큐에서 하나씩 꺼내 상하좌우를 확인하며 익지 않은 토마토를 익게 만들고 큐에 넣음
 *  3. 큐가 빌 때까지 반복
 *  4. 모든 토마토가 익었는지 확인하며, 익지 않은 토마토가 있으면 -1 반환, 모두 익었으면 최대 일수 반환
 *
 */
class Problem7576 {

    private static int N, M;
    private static int[][] BOX;
    private static int[] DX = {1, -1, 0, 0};
    private static int[] DY = {0, 0, 1, -1};
    private static Queue<int[]> QUEUE = new LinkedList<>();

    public static void main(String args[]) throws IOException {
        input();
        System.out.println(bfs());
    }

    private static int bfs() {
        while (!QUEUE.isEmpty()) {
            int[] curr = QUEUE.poll();
            int x = curr[0];
            int y = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + DX[d], ny = y + DY[d];
                // 범위 체크 및 익지 않은 토마토만 처리
                if (nx >= 0 && nx < M && ny >= 0 && ny < N && BOX[nx][ny] == 0) {
                    BOX[nx][ny] = BOX[x][y] + 1;
                    QUEUE.add(new int[]{nx, ny});
                }
            }
        }

        // 일수와 덜 익은 토마토 판정
        int maxDay = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (BOX[i][j] == 0) return -1; // 덜 익은 토마토 존재
                maxDay = Math.max(maxDay, BOX[i][j]);
            }
        }
        return maxDay > 1 ? maxDay - 1 : 0; // 시작값 1 빼기
    }


    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        BOX = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int t = Integer.parseInt(st.nextToken());
                if (t == 1) { {
                    QUEUE.add(new int[]{i, j});
                }}
                BOX[i][j] = t;
            }
        }
    }
}
