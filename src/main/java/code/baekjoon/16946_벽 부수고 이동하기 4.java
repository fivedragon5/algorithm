package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16946
 *
 * 제한)
 *  1 ≤ N ≤ 1,000
 *  1 ≤ M ≤ 1,000
 *
 * 문제)
 *  1.  N * M 형태의 맵이 있다.
 *  2. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다.
 *  3. 한 칸에 다른 칸으로 이동하려면, 두 칸이 인접해야 한다.
 *   - 벽을 부수고 이동할 수 있는 곳으로 변경한다.
 *   - 그 위치에서 이동할 수 있는 칸의 개수를 세어본다.
 *
 *
 * 풀이)
 *  1. 맵을 탐색하면서 0인 곳을 찾는다.
 *  2. 그 곳에서 DFS를 통해 연결된 0의 개수를 세고, 그룹을 나눈다.
 *  3. 각 그룹의 개수를 저장한다.
 *  4. 다시 맵을 탐색하면서 1인 곳을 찾는다.
 *  5. 그 곳에서 상하좌우로 이동할 수 있는 곳을 확인한다.
 *  6. 이동할 수 있는 곳이 0인 곳이라면, 해당 그룹의 개수를 더한다.
 *   - 단, 이미 방문한 그룹은 중복으로 더하지 않도록 Set을 사용한다.
 *  7. 최종적으로 각 1인 곳에 대해 이동할 수 있는 칸의 개수를 계산한다.
 *  8. 결과를 출력한다.
 *
 */
class Problem16946 {

    private static int N, M;
    private static int[][] MAP;
    private static int[] dx = {1, -1, 0, 0}; // 우 좌 상 하
    private static int[] dy = {0, 0, 1, -1}; // 우 좌 상 하
    private static int EMPTY_COUNT;

    public static void main(String args[]) throws IOException {
        input();
        int[][] result = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(MAP[i], 0, result[i], 0, M);
        }

        Map<Integer, Integer> groupIdAndCount = new HashMap<>();
        int groupId = 1;
        int[][] group = new int[N][M];

        for (int i = 0; i < N; i++)  {
            for (int j = 0; j < M; j++) {
                int current = MAP[i][j];
                if (current != 0)  {
                    int count = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int moveX = j + dx[k];
                        int moveY = i + dy[k];
                        if (moveX < 0 || moveX >= M || moveY < 0 || moveY >= N) continue;
                        if (MAP[moveY][moveX] == 0) {
                            int groupIdOfNeighbor = group[moveY][moveX];
                            if (set.contains(groupIdOfNeighbor)) {
                                continue;
                            }

                            if (groupIdOfNeighbor == 0) {
                                EMPTY_COUNT = 1;
                                dfs(groupId, group, moveY, moveX);
                                groupIdAndCount.put(groupId, EMPTY_COUNT);
                                groupIdOfNeighbor = groupId;
                                groupId++;
                            }

                            set.add(groupIdOfNeighbor);
                            count += groupIdAndCount.get(groupIdOfNeighbor);
                        }
                    }
                    result[i][j] = count % 10;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1); // 마지막 줄의 개행 문자 제거
        System.out.print(sb);
    }

    private static void dfs(int id, int[][] group, int row, int col) {
        group[row][col] = id;
        for (int i = 0; i < 4; i++) {
            int moveX = col + dx[i];
            int moveY = row + dy[i];
            if (moveX < 0 || moveX >= M || moveY < 0 || moveY >= N) continue;
            if (MAP[moveY][moveX] == 0 && group[moveY][moveX] == 0) {
                EMPTY_COUNT++;
                dfs(id, group, moveY, moveX);
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAP = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < M; j++) {
                MAP[i][j] = line.charAt(j) - '0';
            }
        }
    }
}
/*
3 3
101
010
101

303
050
303

 */

