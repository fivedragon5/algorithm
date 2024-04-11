package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem3190 {

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String args[]) throws IOException {
        /**
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] snake = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                snake[i][j] = -1;
            }
        }
        snake[0][0] = 0;

        st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());

        boolean[][] apples = new boolean[N][N];
        int tempX = 0;
        int tempY = 0;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            tempX = Integer.parseInt(st.nextToken()) - 1;
            tempY = Integer.parseInt(st.nextToken()) - 1;
            apples[tempX][tempY] = true;
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());

        int[] seconds = new int[L];
        String[] directions = new String[L];


        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            seconds[i] = Integer.parseInt(st.nextToken());
            directions[i] = st.nextToken();
        }

        int second = 0;
        int step = 0;
        int currentDirection = 0; //0:우, 1:하, 2:좌, 3:상
        int currentSnakeHead_x = 0;
        int currentSnakeHead_y = 0;
        int currentSnakeTail_x = 0;
        int currentSnakeTail_y = 0;


        while (true) {

            second++;

            currentSnakeHead_x += dx[currentDirection];
            currentSnakeHead_y += dy[currentDirection];

            //벽
            if (currentSnakeHead_x >= N || currentSnakeHead_y >= N || currentSnakeHead_x < 0 || currentSnakeHead_y < 0) {
                break;
            }
            
            //사과 있을경우
            if (apples[currentSnakeHead_y][currentSnakeHead_x]) {
                apples[currentSnakeHead_y][currentSnakeHead_x] = false;
            }
            //몸에 부딪혔을경우
            else if (snake[currentSnakeHead_y][currentSnakeHead_x] >= 0){
                break;
            }
            else {
                int tailDirection = snake[currentSnakeTail_y][currentSnakeTail_x];
                snake[currentSnakeTail_y][currentSnakeTail_x] = -1;

                currentSnakeTail_x += dx[tailDirection];
                currentSnakeTail_y += dy[tailDirection];
            }

            //방향설정 남기기
            if (step < L) {
                if (second == seconds[step]) {
                    if (directions[step].equals("D")) {
                        currentDirection = (currentDirection + 1) % 4;
                    }
                    else {
                        currentDirection = (currentDirection + 3) % 4;
                    }
                    step++;
                }
            }

            snake[currentSnakeHead_y][currentSnakeHead_x] = currentDirection;

        }

        System.out.println(second);
    }
}

/**
 *
 ex)
 6
 3
 3 4
 2 5
 5 3
 3
 3 D
 15 L
 17 D

 */
