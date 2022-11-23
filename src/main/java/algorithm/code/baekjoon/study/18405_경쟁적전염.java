package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * NxN크기의 시험관
 * 모든 바이러스의 종류 1 ~ K
 * 모든 바이러스는 1초마다 상,하,좌,우 방향으로 증식
 * 번호가 낮은 종류의 바이러스부터 먼저 증식
 * 바이러스 끼리 시험관 침투 불가능
 * S초 후에 (X, Y)에 존재하는 바이러스의 종류를 출력 없다면 0 출력
 * X:행, Y:열 [X-1][Y-1]
 * 
 *
 * 조건)
 * 1 <= N <= 200
 * 1 <= K <= 1,000
 * 0 <= S <= 10,000
 * 1 <= X, Y <= N
 */

class Problem18405 {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] testTubes = new int[N][N];
        ArrayList<LinkedList<int[]>> viruses = new ArrayList<>();
        for (int i = 0; i <= K; i++) {
            viruses.add(new LinkedList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int virus = Integer.parseInt(st.nextToken());
                if (virus > 0) {
                    testTubes[i][j] = virus;
                    viruses.get(virus).add(new int[]{i, j});
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int second = 0;

        while (second < S) {
            second++;
            for (int i = 1; i <= K; i++) {
                LinkedList<int[]> pointList = new LinkedList<>();
                for (int[] point : viruses.get(i)) {
                    for (int p = 0 ; p < 4; p++) {
                        int x = point[0] + dx[p];
                        int y = point[1] + dy[p];
                        if (x < 0 || x >= N || y < 0 || y >= N) {
                            continue;
                        }
                        if (testTubes[x][y] == 0) {
                            pointList.add(new int[]{x, y});
                            testTubes[x][y] = i;
                        }
                    }
                }
                viruses.get(i).clear();
                for (int[] point : pointList) {
                    viruses.get(i).add(new int[]{point[0], point[1]});
                }
            }
        }
        System.out.println(testTubes[X-1][Y-1]);
    }

}
/*
3 3
1 0 2
0 0 0
3 0 0
2 3 2

3

3 3
1 0 2
0 0 0
3 0 0
1 2 2

0
 */

