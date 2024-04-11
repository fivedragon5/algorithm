package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 접근)
 * 1. 일반적인 구현 문제
 * 2. 문제가 애매하고 불친절함 x,y값 순서 주의
 *  - a b c d e => c가 y고 d가 x임
 * 
 * 제한)
 * 1 ≤ N, M ≤ 20
 * 0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1
 * 1 ≤ K ≤ 1,000
 * 동:1, 서:2, 북:3, 남:4
 */
class Problem14499 {

    static int[][] map;
    static int[] dice = new int[6];
    static int N,M,x,y;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        y = Integer.parseInt(st.nextToken()); // 좌표x
        x = Integer.parseInt(st.nextToken()); // 좌표y
        int K = Integer.parseInt(st.nextToken()); // 명령의 갯수

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        //커맨드
        for (int i = 0; i < K; i++) {
            roll(Integer.parseInt(st.nextToken()));
        }
    }

    static void roll(int command) {

        //동
        if (command == 1) {
            int moveX = x + 1;
            if (moveX < 0 || moveX >= M) return;
            x = moveX;
            int temp = dice[5];
            dice[5] = dice[2];
            dice[2] = dice[4];
            dice[4] = dice[0];
            dice[0] = temp;
        }
        //서
        else if (command == 2) {
            int moveX = x - 1;
            if (moveX < 0 || moveX >= M) return;
            x = moveX;
            int temp = dice[4];
            dice[4] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[0];
            dice[0] = temp;
        }
        //북
        else if (command == 3) {
            int moveY = y - 1;
            if (moveY < 0 || moveY >= N) return;
            y = moveY;
            int temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = dice[0];
            dice[0] = temp;
        }
        //남
        else {
            int moveY = y + 1;
            if (moveY < 0 || moveY >= N) return;
            y = moveY;
            int temp = dice[3];
            dice[3] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[0];
            dice[0] = temp;
        }

        if (map[y][x] == 0) map[y][x] = dice[2]; //주사위 바닥에 있는 숫자가 맵에 복사
        else {
            dice[2] = map[y][x]; //맵에 있는 숫자가 주사위 바닥에 복사
            map[y][x] = 0; //맵에 있는 숫자가 0으로 변경
        }

        System.out.println(dice[0]);
    }
}
/*
4 2 0 0 8
0 2
3 4
5 6
7 8
4 4 4 1 3 3 3 2

0
0
3
0
0
8
6
3

3 3 1 0 14
1 0 0
0 0 0
0 0 0
3 3 1 1 4 2 4 2 3 3 1 1 4 4

0
0
1
0
0
0
0
0
0
0
0
0
0
 */

