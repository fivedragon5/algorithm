package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1600
 *
 * 제한)
 *  1 <= W, H <= 200
 *  0 <= K <= 30
 *  0 : 평지, 1 : 장애물
 *
 * 문제)
 *  1. 말이 되고 싶은 원숭이가 있다.
 *  2. 격자판 맨 왼쪽 위에서 출발 -> 맨 오른쪽 아래까지 이동하는데에 최소한 몇번 이동해야 하는지 구하기
 *   - 이동방법
 *      1. 현재 위치에서 상,하,좌,우
 *      2. 체스상 말 이동방법 으로 이동 (단, K번만 이동 가능)
 *  3. 만약 갈 수 없는 경우 -1을 반환
 *
 * 풀이)
 *  풀이방법 : 구현 + BFS
 *
 *  1. 원숭이가 이동한 정보를 담는 Class(Monkey) 선언
 *   - x,y 좌표
 *   - 말의 형태로 이동할 수 있는 기회 수
 *   - 원숭이가 이동한 횟수
 *  2. 이미 이동한 방법을 반복하지 않기 위해 Set<String> 선언
 *   - x,y,k 를 String 형태로 저장
 *   - Monkey class에 toString @override
 *  3. BFS 탐색을 이용하여 원숭이가 이동할 수 있는 방법을 queue에 저장
 *   - set을 활용하여 중복된 이동제거
 *  4. 0,0 -> H,W 에 도착시 moveCount 반환
 *   - BFS 탐색이기에 가장먼저 도달할 경우 즉시 종료(가장 최소한의 이동)
 *  5. 만약 더이상 이동할 수 없을 경우 -1 반환 (while 문 정상 종료 시)
 *
 */
class Problem1600 {

    private static int K, W, H;
    private static int[][] BOARD;
    private static final int[] DX_HORSE = new int[]{1, 2,  2,  1, -1, -2, -2, -1};
    private static final int[] DY_HORSE = new int[]{2, 1, -1, -2, -2, -1,  1,  2};
    private static final int[] DX_MONKEY = new int[]{0, 1,  0, -1};
    private static final int[] DY_MONKEY = new int[]{1, 0, -1 , 0};

    public static void main(String args[]) throws IOException {
        input();

        Set<String> set = new HashSet<>();
        Queue<Monkey> queue = new LinkedList<>();
        queue.add(new Monkey(0,0, K, 0));

        while (!queue.isEmpty()) {
            Monkey currentMonkey = queue.poll();

            if (!set.add(currentMonkey.toString())) {
                continue;
            }

            if (currentMonkey.x == W - 1 && currentMonkey.y == H - 1) {
                System.out.println(currentMonkey.moveCount);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int moveX = currentMonkey.x + DX_MONKEY[i];
                int moveY = currentMonkey.y + DY_MONKEY[i];
                if (moveX < 0 || moveY < 0 || moveX >= W || moveY >= H || BOARD[moveY][moveX] == 1) {
                    continue;
                }
                queue.add(new Monkey(moveX, moveY, currentMonkey.horseChanceCount, currentMonkey.moveCount + 1));
            }

            if (currentMonkey.horseChanceCount > 0) {
                for (int i = 0; i < 8; i++) {
                    int moveX = currentMonkey.x + DX_HORSE[i];
                    int moveY = currentMonkey.y + DY_HORSE[i];
                    if (moveX < 0 || moveY < 0 || moveX >= W || moveY >= H || BOARD[moveY][moveX] == 1) {
                        continue;
                    }
                    queue.add(new Monkey(moveX, moveY, currentMonkey.horseChanceCount - 1, currentMonkey.moveCount + 1));
                }
            }
        }

        System.out.println(-1);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        BOARD = new int[H][W];

        for (int i = 0 ; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                BOARD[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
    }

    private static class Monkey {
        private int x;
        private int y;
        private int horseChanceCount;
        private int moveCount;

        Monkey(int x, int y, int horseChanceCount, int moveCount) {
            this.x = x;
            this.y = y;
            this.horseChanceCount = horseChanceCount;
            this.moveCount = moveCount;
        }

        @Override
        public String toString() {
            return this.x + "," + this.y + "," + this.horseChanceCount;
        }
    }
}
/*
1
4 4
0 0 0 0
1 0 0 0
0 0 1 0
0 1 0 0

4

 */
