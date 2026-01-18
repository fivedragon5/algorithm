package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1025
 *
 * 제한)
 *  1 <= N, M <= 9
 *  0 <= 표의 숫자 <= 9
 *
 * 문제)
 *  1. 연두가 서로 다른 1개 이상의 칸을 선택하려고 한다.
 *  2. 행의 번호가 선택한 순서대로 등차수열을 이루고 있어야 하고, 열의 번호도 선택한 순서대로 등차수열을 이루고 있어야 한다.
 *  3. 이렇게 선택한 칸에 적힌 수를 순서대로 이어붙이면 정수를 하나 만들 수 있다.
 *  4. 연두가 만들 수 있는 정수 중 가장 큰 완전 제곱수를 구하기
 *   - 완전 제곱 수 : 어떤 정수 k에 대해 k * k 의 꼴로 나타낼 수 있는 수
 *
 * 풀이)
 *  BFS
 *  1. 모든 칸을 시작점으로 하여 BFS 수행
 *  2. 등차수열을 이루는 칸을 방문하여 숫자를 이어붙임
 *      - dx, dy : 등차수열의 공차
 *  3. 이어붙인 숫자가 완전 제곱수인지 확인
 *      - 제곱근을 구한 후, 제곱근의 제곱이 원래 숫자와 같은지 확인
 *  4. 완전 제곱수인 경우, 최대값 갱신, 만약 완전 제곱수가 하나도 없는 경우 -1 출력
 */
class Problem1025 {

    private static int N, M;
    private static int[][] board;

    public static void main(String args[]) throws IOException {
        input();
        int answer = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // dx, dy : 등차수열의 공차
                for (int dx = -N; dx <= N; dx++) {
                    for (int dy = -M; dy <= M; dy++) {
                        if (dx == 0 && dy == 0) {
                            continue;
                        }

                        int x = i;
                        int y = j;
                        int num = 0;

                        // 2. 등차수열을 이루는 칸을 방문하여 숫자를 이어붙임
                        while (x >= 0 && x < N && y >= 0 && y < M) {
                            num = num * 10 + board[x][y];

                            // 3. 이어붙인 숫자가 완전 제곱수인지 확인
                            if (isPerfectSquare(num)) {
                                answer = Math.max(answer, num);
                            }

                            x += dx;
                            y += dy;
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }
        br.close();
    }
}
/*
4
2 2 2 2
 */
