package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 접근)
 * DFS
 *
 * 풀이)
 * 1. 주어진 그림을 int형 2차원 배열에 입력
 * 2. DFS탐색시 방문 체크를 위해 boolean형 2차원 배열 추가
 * 3. 그림탐색을 위해 2중 for문
 * 4. 2중 for을 돌면서 1을 만나면 상하좌우 재귀 탐색 이때 index를 넘김
 * 5. 전역 선언된 countMap에 index값을 key값으로 갱신
 * 6. 탐색이 종료된뒤 countMap의 size로 그림의 갯수를 구하고 map을 순회하며 max값 최신화
 *
 * 제한)
 * 1 <= n <= 500
 * 1 <= m <= 500
 */
class Problem1926 {

    static int n,m;
    static int[][] map;
    static boolean[][] visited;
    static HashMap<Integer, Integer> countMap = new HashMap<>();

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1 ,0};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n  = Integer.parseInt(st.nextToken());
        m  = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || map[i][j] == 0) continue;
                countMap.put(++index, 1);
                check(i, j, index);
            }
        }

        int max = 0;
        for (int key : countMap.keySet()) {
            max = Math.max(max , countMap.get(key));
        }

        System.out.println(countMap.size());
        System.out.println(max);

    }

    static void check(int row, int col, int index) {
        visited[row][col] = true;
        int moveRow = 0;
        int moveCol = 0;
        for (int i = 0; i < 4; i++) {
            moveRow = row + dr[i];
            moveCol = col + dc[i];
            if (moveRow < 0 || moveCol < 0 || moveRow >= n || moveCol >= m) {
                continue;
            }
            else if (map[moveRow][moveCol] == 0 || visited[moveRow][moveCol]) {
                continue;
            }
            else {
                countMap.put(index, countMap.getOrDefault(index, 0) + 1);
                check(moveRow, moveCol, index);
            }
        }
    }
}

/*
6 5
1 1 0 1 1
0 1 1 0 0
0 0 0 0 0
1 0 1 1 1
0 0 1 1 1
0 0 1 1 1

4
9

1 1
1

1 1
0
 */

