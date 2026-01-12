package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1012
 *
 * 제한)
 *  1 <= M <= 50
 *  1 <= N <= 50
 *  1 <= K <= 2500
 *  0 <= X <= M-1
 *  0 <= Y <= N-1
 *
 * 문제)
 *  1. 배추밭의 가로길이 M, 세로길이 N, 배추가 심어져 있는 위치의 개수 K가 주어진다.
 *  2. 배추를 재배하기 위해 해충으로부터 보호하기 위한 해충 방지에 효과적인 배추흰지렁이의 최소 마리 수 구하기
 *   - 배추흰지렁이는 인접한 다른 배추가 있을 경우, 그 배추들도 모두 보호할 수 있다.
 *
 * 풀이)
 *  DFS
 *  1. 배추밭의 정보를 2차원 배열로 표현
 *  2. 배추밭을 순회하면서 배추가 있는 위치를 발견할 때마다 DFS로 인접한 배추들을 모두 방문 처리
 *  3. DFS가 호출된 횟수 = 배추흰지렁이의 마리 수
 *
 */
class Problem1012 {

    // 배추밭 정보 리스트
    private static List<int[]> CABBAGE_FIELD_INFO = new ArrayList<>();
    // 배추밭 맵 리스트
    // 0: empty, 1: cabbage 2: visited
    private static List<int[][]> CABBAGE_FIELD_MAP = new ArrayList<>();
    // 방향 벡터
    private final static int[] DX = {-1, 1, 0, 0};
    private final static int[] DY = {0, 0, -1, 1};

    public static void main(String args[]) throws IOException {
        input();
        int testCaseCount = CABBAGE_FIELD_INFO.size();
        for (int i = 0; i < testCaseCount; i++) {
            int count = countWorms(
                    CABBAGE_FIELD_MAP.get(i),
                    CABBAGE_FIELD_INFO.get(i)[0],
                    CABBAGE_FIELD_INFO.get(i)[1]
            );
            System.out.println(count);
        }
    }

    // 배추흰지렁이 마리수 계산
    private static int countWorms(int[][] fieldMap, int m, int n) {
        int wormCount = 0;
        // 배추밭 순회
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                // 배추가 있는 위치 발견 시
                if (fieldMap[x][y] == 1) {
                    wormCount++;
                    // 인접한 배추들 모두 방문 처리
                    dfs(fieldMap, x, y, m, n);
                }
            }
        }
        return wormCount;
    }

    private static void dfs(int[][] fieldMap, int x, int y, int m, int n) {
        fieldMap[x][y] = 2; // 방문 처리

        for (int direction = 0; direction < 4; direction++) {
            int nextX = x + DX[direction];
            int nextY = y + DY[direction];
            // 배추밭 범위 내에 있고, 배추가 있는 위치인 경우
            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && fieldMap[nextX][nextY] == 1) {
                dfs(fieldMap, nextX, nextY, m, n);
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int testCaseCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < testCaseCount; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 가로 길이
            int n = Integer.parseInt(st.nextToken()); // 세로 길이
            int k = Integer.parseInt(st.nextToken()); // 배추가 심어져 있는 위치의 개수
            CABBAGE_FIELD_INFO.add(new int[] {m, n});

            int[][] fieldMap = new int[m][n];

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                fieldMap[x][y] = 1;
            }

            CABBAGE_FIELD_MAP.add(fieldMap);
        }

        br.close();
    }
}
/*
3
2 2
1 5
13 29

 */
