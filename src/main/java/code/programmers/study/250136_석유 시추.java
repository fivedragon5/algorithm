package code.programmers.study;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/250136
 *
 * 제한)
 *  1 ≤ land의 길이 = 땅의 세로길이 = n ≤ 500
 *      1 ≤ land[i]의 길이 = 땅의 가로길이 = m ≤ 500
 *      land[i][j]는 i+1행 j+1열 땅의 정보를 나타냅니다.
 *      land[i][j] = 0, 1
 *      land[i][j] = 0(빈 땅), = 1(석유가 있는 땅)
 *
 * 문제)
 *  1. 세로 길이 n, 가로 길이 m인 땅이 있습니다.
 *  2. 시추관을 수직으로 단 하나만 뚫을 수 있을 때, 가장 많은 석유를 뽑을 수 있는 석유량 구하기
 *
 * 풀이)
 *  BFS, 메모제이션
 *   - 메모제이션 사용 X -> 시간초과
 *   - DFS -> 메모리 초과(런타임 오류 발생)
 *      - 생각보다 메모리가 작음
 *  1. 열마다 시추관을 꽂았을 때, 석유량을 계산합니다.
 *  2. 각 열에서 시추관을 꽂았을 때, 석유가 있는 땅을 BFS로 탐색하여 석유 그룹을 만들어서 해당 그룹에 대한 석유 양을 저장
 *  3. 탐색 진행시 해당 석유가 이미 탐색한 그룹인지 확인
 *   - 이미 탐색 한 석유 : 해당 그룹의 석유량을 가져와서 현재 시추관을 꽂았을 때 석유량에 추가
 *   - 탐색하지 않은 석유 : BFS 진행후 그룹에 ID 부여
 */
class Lesson250136 {
    public static void main(String[] args) {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        System.out.println(solution(land));

        int[][] land2 = {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0},{1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0},{1, 0, 0, 1, 0, 1},{1, 0, 0, 0, 0, 0},{1, 1, 1, 1, 1, 1}};
        System.out.println(solution(land2));
    }

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int ROW, COL;

    public static int solution(int[][] land) {
        ROW = land.length; // 행의 수
        COL = land[0].length; // 열의 수
        int maxOilAmount = 0; // 최대 석유량

        int oilGroupIndex = 1; // 석유 그룹 Index
        int[][] oilGroupMap = new int[ROW][COL]; // 석유 그룹 MAP

        List<Integer> oilGroupAmount = new ArrayList<>(); // 석유 그룹 : 석유 량 저장
        oilGroupAmount.add(0); // 초기값

        // 0 ~ row 까지 시추관 을 꽂았을 경우
        for (int x = 0; x < COL; x++) {
            int currentOilAmount = 0; // 현재 시추관을 꽂았을 때 석유량 초기화
            Set<Integer> visited = new HashSet<>();
            for (int y = 0; y < ROW; y++) {
                // 석유가 있고, 처음 발견하는 석유 그룹인 경우
                if (land[y][x] == 1 && oilGroupMap[y][x] == 0) {
                    // 시추관을 꽂았을 때 석유를 채취할 수 있는 양 계산
                    visited.add(oilGroupIndex); // 현재 석유 그룹 방문 처리
                    int groupOil = bfs(land, oilGroupMap, x, y, oilGroupIndex++);
                    currentOilAmount += groupOil; // 현재 시추관을 꽂았을 때 석유량 증가
                    oilGroupAmount.add(groupOil); // 석유량 저장
                }
                // 이미 채취한 석유 그룹인 경우
                else if (oilGroupMap[y][x] != 0 && !visited.contains(oilGroupMap[y][x])) {
                    int oilGroupId = oilGroupMap[y][x];
                    currentOilAmount += oilGroupAmount.get(oilGroupId); // 이미 채취한 석유량 추가
                    visited.add(oilGroupId); // 현재 석유 그룹 방문 처리
                }
                else {

                }
            }
            maxOilAmount = Math.max(maxOilAmount, currentOilAmount); // 최대 석유량 갱신
        }
        return maxOilAmount;
    }

    //
    private static int bfs(int[][] land, int[][] oilGroupMap, int x, int y, int oilGroupIndex) {
        int oilAmount = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        oilGroupMap[y][x] = oilGroupIndex; // 현재 위치에 석유 그룹 인덱스 저장

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                // 범위 체크
                if (nextX < 0 || nextX >= COL || nextY < 0 || nextY >= ROW || oilGroupMap[nextY][nextX] != 0) continue;

                // 석유가 있는 땅이고, 아직 그룹이 지정되지 않은 경우
                if (land[nextY][nextX] == 1) {
                    oilGroupMap[nextY][nextX] = oilGroupIndex; // 석유 그룹 인덱스 저장
                    queue.offer(new int[]{nextX, nextY}); // 다음 위치를 큐에 추가
                    oilAmount++; // 석유량 증가
                }
            }
        }
        return oilAmount; // 현재 석유 그룹의 석유량 반환
    }
}
