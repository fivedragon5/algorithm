package algorithm.code.weekly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 접근)
 *  구현문제
 *  
 * 풀이)
 *  제시한 문제대로 구현
 *  
 * 제한)
 *  6 ≤ R, C ≤ 50
 *  1 ≤ T ≤ 1,000
 *  -1 ≤ Ar,c ≤ 1,000
 */
class Problem17144 {

    static int R,C,T;
    static int[][] map;
    static int[][] initMap;
    static int cleanerFirst = -1;
    static int cleanerSecond = -1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        initMap = new int[R][C];

        // 값 입력
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 공기 청정기 위치
        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                if (cleanerFirst == -1) cleanerFirst = i;
                else cleanerSecond = i;
                initMap[i][0] = -1;
            }
        }

        for (int t = 0; t < T; t++) {
            // 1. init에 확산된 먼지만 쌓기 && 확산되고 남은 먼지는 map에 남겨두기
            spreadOut();

            // 2. 종료 후 map + initMap 을 더해서 map 으로 옮기기 + initMap은 같이 초기화 진행
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (j == 0 && (i == cleanerFirst || i == cleanerSecond)) {
                    }
                    else {
                        map[i][j] += initMap[i][j];
                        initMap[i][j] = 0;
                    }
                }
            }

            // 3. 공기청정기를 바람이 끝나는 지점 -> 바람이 시작되는 지점 순으로 먼지를 한칸씩 이동 시키기
            for (int i = cleanerFirst - 1; i > 0; i--) {
                map[i][0] = map[i-1][0];
            }

            for (int i = 0; i < C - 1; i++) {
                map[0][i] = map[0][i+1];
            }

            for (int i = 0; i < cleanerFirst; i++) {
                map[i][C-1] = map[i+1][C-1];
            }

            for (int i = C - 1; i > 1; i--) {
                map[cleanerFirst][i] = map[cleanerFirst][i-1];
            }

            map[cleanerFirst][1] = 0;


            // 아래쪽 공기청정기는 시계 방향
            for (int i = cleanerSecond + 1; i < R - 1; i++) {
                map[i][0] = map[i+1][0];
            }

            for (int i = 0; i < C - 1; i++) {
                map[R-1][i] = map[R-1][i+1];
            }

            for (int i = R - 1; i > cleanerSecond; i--) {
                map[i][C-1] = map[i-1][C-1];
            }

            for (int i = C - 1; i > 1; i--) {
                map[cleanerSecond][i] = map[cleanerSecond][i-1];
            }

            map[cleanerSecond][1] = 0;
        }

        int sum = 2;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += map[i][j];
            }
        }

        System.out.println(sum);
    }

    static private void spreadOut() {
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int p = map[i][j];
                int count = 0;
                int m = p/5;
                for (int d = 0; d < 4; d++) {
                    int moveX = j + dx[d];
                    int moveY = i + dy[d];
                    if (moveX < 0 || moveY < 0 || moveX >= C || moveY >= R) {
                        continue;
                    }
                    else if(moveX == 0 && (moveY == cleanerFirst || moveY == cleanerSecond)) {
                        continue;
                    }
                    count++;
                    initMap[moveY][moveX] += m;
                }
                map[i][j] -= m * count;
            }
        }
    }
}
/*
7 8 1
0 0 0 0 0 0 0 9
0 0 0 0 3 0 0 8
-1 0 5 0 0 0 22 0
-1 8 0 0 0 0 0 0
0 0 0 0 0 10 43 0
0 0 5 0 15 0 0 0
0 0 40 0 0 0 20 0

188
 */

