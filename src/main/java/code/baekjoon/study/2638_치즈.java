package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2638
 * 제한)
 * 5 <= N, M <= 100
 * 문제)
 * 1. 1시간마다 치즈가 녹음
 * 2. 치즈의 4개의 변중 2개이상의 변이 실내온도의 공기와 접촉해 있을 경우 1시간만에 녹음
 * 3. 치즈가 모두 녹아 없어지는대까지 걸리는 시간을 구하기
 * 4. 가장자리는 치즈가 놓이지 않는것으로 간주한다.
 *
 * 풀이)
 * 1,2,3 반복
 *  1. 외부 공기 좌표를 DFS로 갱신
 *  2. 외부공기와 2면이상 맞닿은 치즈 녹이기
 *  3. 남아있는 치즈의 수가 0개일경우 지난 시간 return
 */
class Problem2638 {

    static int N,M;
    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {1, 0, -1, 0};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행 수
        M = Integer.parseInt(st.nextToken()); // 열 수
        int[][] cheese = new int[N][M];
        int cheeseCount = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int parseInt = Integer.parseInt(st.nextToken());
                if (parseInt == 1) cheeseCount++;
                cheese[i][j] = parseInt;
            }
        }

        int hour = 0;
        while (cheeseCount > 0) {
            checkOutSideAir(cheese, new boolean[N][M], 0, 0);
            cheeseCount -= meltCheese(cheese);
            hour++;
        }
        System.out.println(hour);
    }

    private static void checkOutSideAir(int[][] cheese, boolean[][] visited, int row, int col) {
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if (cheese[row][col] == 1) {
            return;
        }
        cheese[row][col] = -1;
        for (int i = 0; i < 4; i++) {
            int moveRow = row + dy[i];
            int moveCol = col + dx[i];
            if (moveRow >= 0 && moveRow < N && moveCol >= 0 && moveCol < M) {
                checkOutSideAir(cheese, visited, moveRow, moveCol);
            }
        }
    }

    private static int meltCheese(int[][] cheese) {
        int meltCheeseCount = 0;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (cheese[i][j] == 1 && countOutsideAir(cheese, i, j) >= 2) {
                    meltCheeseCount++;
                    cheese[i][j] = 0;
                }
            }
        }
        return meltCheeseCount;
    }

    private static int countOutsideAir(int[][] cheese, int row, int col) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int newRow = row + dy[i];
            int newCol = col + dx[i];
            if (cheese[newRow][newCol] == -1) {
                count++;
            }
        }
        return count;
    }
}
/*
8 9
0 0 0 0 0 0 0 0 0
0 0 0 1 1 0 0 0 0
0 0 0 1 1 0 1 1 0
0 0 1 1 1 1 1 1 0
0 0 1 1 1 1 1 0 0
0 0 1 1 0 1 1 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
 */
