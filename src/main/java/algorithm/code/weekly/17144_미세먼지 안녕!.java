package algorithm.code.weekly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 제한)
 *  6 ≤ R, C ≤ 50
 *  1 ≤ T ≤ 1,000
 *  -1 ≤ Ar,c ≤ 1,000
 */
class Problem17144 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int cleanerFirst = -1;
        int cleanerSecond = -1;

        int[][] map = new int[R][C];
        int[][] initMap = new int[R][C];

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

        // 1. init에 확산된 먼지만 쌓기 && 확산되고 남은 먼지는 map에 남겨두기
        // 2. 종료 후 map + initMap 을 더해서 map 으로 옮기기 + initMap은 같이 초기화 진행
        // 3. 공기청정기를 바람이 끝나는 지점 -> 바람이 시작되는 지점 순으로 먼지를 한칸씩 이동 시키기
        // 위의 1~3 과정을 T초 동안 반복 / 먼지의 총 량을 구하는 것이기 때문에 총량을 계속 갱신 시켜주는게 좋을듯? 어디서할지는 고민해보자.
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

