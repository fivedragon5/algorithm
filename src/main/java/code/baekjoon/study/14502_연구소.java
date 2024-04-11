package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 접근)
 *  구현문제라고 생각해서 시키는대로 차근 차근 풀었다.
 *   - 2차원 배열의 deep copy 주의
 *   - index 범위 주의
 *
 * 풀이)
 *  주어진 연구소의 지도를 바탕으로 빈공간(0)에 벽을 3개 설치한후 바이러스가 퍼지는 범위를 시뮬레이션 돌린다.
 *  시뮬레이션을 돌리기위해 연구소의 원 지도는 훼손 하지 않고 계속 새로운 지도로 만들고 벽을 설치한후 시뮬레이션을 돌렸다
 *   - 새로운 지도 (deep copy) array.clone() 2차원 배열 생각
 *  1. 벽을 3개씩 전부 설치해 보기위해 빈공간(0)을 좌표로 같은 arrayList 생성 후 담아 놓기
 *  2. 바이러스 시뮬레이션을 진행 시키기위해 바이러스(2)의 위치를 arrayList로 담아 놓기
 *  3. 벽은 3개 고정이기때문에 3중 for문으로 연구실 사본 지도에 빈공간에 벽을 설치
 *  4. 벽을 설치한 지도를 checkVirus()로 넘겨 바이러스의 수만큼 시뮬레이션 시작
 *  5. moveVirus()로 바이러스를 상,하,좌,우 전염 시키면서 바이러스가 퍼진 카운트를 담아둔다.
 *  6. 빈공간의 갯수 - 설치한 벽 - 퍼진 바이러스 수 = 안전지대
 *  7. 시뮬레이션이 끝날때마다 초기화를 시켜주고 안전지대 최대값을 갱신
 *
 * 제한)
 *  3 ≤ N, M <= 8
 *  2 <= 바이러스 수 <= 10
 *  3 <= 빈칸의 수
 */
class Problem14502 {

    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static int N, M;
    static ArrayList<int[]> virusPosition;
    static ArrayList<int[]> emptyPoint;
    static int safeZoneCount = 0;
    static int increaseVirusCount = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] lab = new int[N][M];
        virusPosition = new ArrayList<>();
        emptyPoint = new ArrayList<>();
        int room = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room = Integer.parseInt(st.nextToken());
                lab[i][j] = room;
                if (room == 2) {
                    virusPosition.add(new int[]{i, j});
                }
                else if (room == 0) {
                    emptyPoint.add(new int[]{i, j});
                }
            }
        }

        safeZoneCount = emptyPoint.size() - 3;

        int max = 0;

        //벽을뽑고 checkVirus 돌리기
        for (int i = 0; i < emptyPoint.size() - 2; i++) {
            for (int j = i + 1; j < emptyPoint.size() - 1; j++) {
                for (int k = j + 1; k < emptyPoint.size(); k++) {
                    int[][] map = new int[N][M];
                    for (int o = 0; o < lab.length; o++) {
                        map[o] = lab[o].clone();
                    }
                    map[emptyPoint.get(i)[0]][emptyPoint.get(i)[1]] = 1;
                    map[emptyPoint.get(j)[0]][emptyPoint.get(j)[1]] = 1;
                    map[emptyPoint.get(k)[0]][emptyPoint.get(k)[1]] = 1;
                    checkVirus(map);
                    max = Math.max(max, safeZoneCount - increaseVirusCount);
                }
            }
        }
        System.out.println(max);
    }

    static void checkVirus(int[][] map) {
        increaseVirusCount = 0;
        for (int[] virus : virusPosition) {
            int row = virus[0];
            int col = virus[1];
            moveVirus(map, row, col);
        }
    }

    static void moveVirus(int[][] map, int row, int col) {
        for (int i = 0; i < 4; i++) {
            int moveRow = row + dr[i];
            int moveCol = col + dc[i];
            if (moveRow < 0 || moveCol < 0 || moveRow >= N || moveCol >= M) {
                continue;
            }
            else if (map[moveRow][moveCol] != 0) {
                continue;
            }
            increaseVirusCount++;
            map[moveRow][moveCol] = 2;
            moveVirus(map, moveRow, moveCol);
        }
    }
}

/*
7 7
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0

27

4 6
0 0 0 0 0 0
1 0 0 0 0 2
1 1 1 0 0 2
0 0 0 0 0 2

9

8 8
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0

3

3 3
0 0 0
0 2 0
0 0 2
 */

