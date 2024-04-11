package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem14503 {

    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //세로:N, 가로:M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        //로봇청소기 좌표 (c,r)
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        //바라보는 방향 d (0:북, 1:동, 2:남, 3:서)
        int d = Integer.parseInt(st.nextToken());

        int[][] room = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cleanCount = 0;
        int currentRobot_x = c;
        int currentRobot_y = r;
        int currentRobot_d = d;
        int turnCount = 0;

        while (true) {

            //1.현재 위치를 청소
            if (room[currentRobot_y][currentRobot_x] < 1) {
                room[currentRobot_y][currentRobot_x] = 2;
                cleanCount++;
                turnCount = 0;
            }

            int temp_d = (currentRobot_d + 3) % 4;
            int temp_x = currentRobot_x + dx[temp_d];
            int temp_y = currentRobot_y + dy[temp_d];

            if (turnCount > 3) {
                temp_d = (currentRobot_d + 2) % 4;
                temp_x = currentRobot_x + dx[temp_d];
                temp_y = currentRobot_y + dy[temp_d];

                if (room[temp_y][temp_x] == 1) {
                    break;
                }

                turnCount = 0;
                currentRobot_x = temp_x;
                currentRobot_y = temp_y;
                continue;
            }

            if (room[temp_y][temp_x] > 0) {
                turnCount++;
                currentRobot_d = temp_d;
            }
            else {
                currentRobot_x = temp_x;
                currentRobot_y = temp_y;
                currentRobot_d = temp_d;
            }
        }

        System.out.println(cleanCount);

    }
}
