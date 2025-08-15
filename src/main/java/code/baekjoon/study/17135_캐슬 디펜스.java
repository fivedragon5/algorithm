package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17135
 *
 * 제한)
 *  3 ≤ N, M ≤ 15
 *  1 ≤ D ≤ 10
 *
 * 문제)
 *  1. 캐슬 디펜스틑 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다.
 *  2. 성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다.
 *  3. 궁수는 성이 있는 칸에 배칠수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다.
 *  4. 각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다.
 *   - 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 갖아 왼쪽에 있는 적을 공격한다.
 *   - 같은 적이 여러 궁수에게 공격당할 수 있다.
 *   - 공격받은 적은 게임에서 제외된다.
 *   - 궁수의 공격이 끝나면, 적이 이동한다.
 *   - 적은 아래로 한칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다.
 *   - 모든 적이 격자판에서 제외되면 게임이 끝난다.
 *  5. 격자판의 상태가 주어졌을 떄, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해 보자.
 *
 * 풀이)
 *  1. 궁수 3명을 배치할 모든 조합을 구하여 시뮬레이션을 수행한다.
 *  2. 각 조합마다 턴 단위로 궁수의 공격을 처리한다.
 *  3. 각 턴의 공격에서 모든 궁수가 동시에 거리가 D 이하인 적 중 가장 가까우며, 여러 명일 경우 가장 왼쪽에 있는 적을 공격한다.
 *  4. 공격받은 적은 한 번에 제거되고, 이후 몬스터가 아래로 이동한다.
 *  5. 모든 적이 제거되거나 맵 밖으로 벗어나면 시뮬레이션 종료하고 최대 제거 수를 갱신한다.
 */
class Problem17135 {

    private static int N, M, D;
    private static int[][] MAP;

    public static void main(String args[]) throws IOException {
        input();
        int maxKill = 0;
        // 궁수 위치 선택: M칸 중 3개 선택
        List<int[]> archerPositions = new ArrayList<>();
        comb(new int[3], 0, 0, archerPositions);

        // 모든 궁수 조합에 대해 시뮬레이션 진행
        for (int[] archers : archerPositions) {
            int killCount = simulate(archers);
            maxKill = Math.max(maxKill, killCount);
        }

        System.out.println(maxKill);
    }

    // 궁수 위치 조합 구하기 (조합)
    private static void comb(int[] arr, int depth, int start, List<int[]> result) {
        if (depth == 3) {
            result.add(arr.clone());
            return;
        }
        for (int i = start; i < M; i++) {
            arr[depth] = i;
            comb(arr, depth + 1, i + 1, result);
        }
    }

    // 조합된 궁수 위치로 시뮬레이션 실행
    private static int simulate(int[] archers) {
        // 원본 맵 복사
        int[][] mapCopy = new int[N][M];
        for (int i = 0; i < N; i++) {
            mapCopy[i] = MAP[i].clone();
        }
        int totalKills = 0;

        // 최대 N 턴 진행 (적이 아래로 내려가면서 끝남)
        for (int turn = 0; turn < N; turn++) {
            Set<Point> targets = new HashSet<>();

            // 1. 궁수별 공격할 적 찾기
            for (int archer : archers) {
                Point target = findTarget(mapCopy, archer);
                if (target != null) targets.add(target);
            }

            // 2. 공격: 적 제거 및 카운트
            for (Point t : targets) {
                if (mapCopy[t.x][t.y] == 1) {
                    mapCopy[t.x][t.y] = 0;
                    totalKills++;
                }
            }

            // 3. 적 이동 (아래로 한 칸씩)
            moveMonsters(mapCopy);
        }

        return totalKills;
    }

    // 궁수가 공격할 적 찾기 (거리 D 이내 가장 가까운 적, 왼쪽 우선)
    private static Point findTarget(int[][] map, int archerCol) {
        Point target = null;
        int minDist = Integer.MAX_VALUE;

        // 궁수 위치는 (N, archerCol) 성이 있는 행 바로 위에 궁수 배치
        for (int r = N - 1; r >= 0; r--) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) {
                    int dist = Math.abs(N - r) + Math.abs(archerCol - c);
                    if (dist <= D) {
                        if (dist < minDist || (dist == minDist && c < (target != null ? target.y : M))) {
                            minDist = dist;
                            target = new Point(r, c);
                        }
                    }
                }
            }
        }
        return target;
    }

    // 몬스터 한 칸 아래로 이동
    private static void moveMonsters(int[][] map) {
        for (int r = N - 1; r > 0; r--) {
            for (int c = 0; c < M; c++) {
                map[r][c] = map[r - 1][c];
            }
        }
        // 맨 위 행은 모두 0으로 초기화 (새로운 적 없음)
        for (int c = 0; c < M; c++) {
            map[0][c] = 0;
        }
    }

    // 위치를 표현할 클래스
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Point)) return false;
            Point p = (Point) obj;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // N + 1 은 성 위치를 뜻함
        MAP = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
/*
4 3
0
2 1 2
1 3
3 2 3 4

 */
