package code.programmers.study;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/81302
 *
 * 제한)
 *  places의 행 길이(대기실 개수) = 5
 *      places의 각 행은 하나의 대기실 구조를 나타냅니다.
 *  places의 열 길이(대기실 세로 길이) = 5
 *  places의 원소는 P,O,X로 이루어진 문자열입니다.
 *      places 원소의 길이(대기실 가로 길이) = 5
 *      P는 응시자가 앉아있는 자리를 의미합니다.
 *      O는 빈 테이블을 의미합니다.
 *      X는 파티션을 의미합니다.
 * 입력으로 주어지는 5개 대기실의 크기는 모두 5x5 입니다.
 * return 값 형식
 *      1차원 정수 배열에 5개의 원소를 담아서 return 합니다.
 *      places에 담겨 있는 5개 대기실의 순서대로, 거리두기 준수 여부를 차례대로 배열에 담습니다.
 *      각 대기실 별로 모든 응시자가 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0을 담습니다.
 *
 * 문제)
 *  1. 대기실의 구조가 주어질 때, 응시자들이 거리두기를 지키고 있는지 확인하기
 *   - 거리두기 조건
 *   - 응시자 사이의 거리는 3이상 이어야 한다.
 *   - 단 응시자가 앉아있는 자리 사이가 파티션으로 막혀 있을 경우, 허용한다.
 *  2. 5개의 대기실 구조가 주어졌을 때, 각 대기실이 거리두기를 잘 지키고 있는지 반환하기
 *   - 1: 거리두기를 잘 지키고 있다.
 *   - 0: 거리두기를 지키지 않고 있다.
 *
 * 풀이)
 *  구현
 *  1. 각 대기실 구조를 2차원 배열로 변환한다.
 *  2. 각 응시자(P)의 위치를 찾아서, 거리두기 조건을 확인한다.
 *    - 상하좌우, 대각선 방향으로 응시자와의 거리를 확인한다.
 *  3. 거리두기 조건을 만족하지 않는 경우가 있으면 0을 반환하고, 모두 만족하면 1을 반환한다.
 *
 */
class Lesson81302 {
    public static void main(String[] args) {
        String[][] places = new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        System.out.println(solution(places));
    }

    private static final int[] dx = {-1, 0, 1, 0}; // 좌, 상, 우, 하
    private static final int[] dy = {0, -1, 0, 1}; // 좌, 상, 우, 하
    private static final int[] ddx = {-1, 1, 1, -1}; // 대각선 좌상, 우상, 우하, 좌하
    private static final int[] ddy = {-1, -1, 1, 1}; // 대각선 좌상, 우상, 우하, 좌하
    private static final int MAX_ROW = 5;
    private static final int MAX_COL = 5;

    public static int[] solution(String[][] places) {
        int[] result = new int[places.length];
        char[][][] grid = new char[places.length][5][5];
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[i].length; j++) {
                grid[i][j] = places[i][j].toCharArray();
            }
        }
        for (int i = 0; i < grid.length; i++) {
            result[i] = isValidPlace(grid[i]) ? 1 : 0;
        }
        return result;
    }

    private static boolean isValidPlace(char[][] place) {
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length; j++) {
                if (place[i][j] == 'P') {
                    if (!checkDistance(place, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean checkDistance(char[][] place, int x, int y) {
        for (int i = 0; i < 4; i ++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isOutBounds(nx, ny)) {
                continue; // 범위를 벗어남
            }

            if (place[nx][ny] == 'P') {
                return false; // 바로 옆에 P가 있음
            } else if (place[nx][ny] == 'O') {
                // 2칸 떨어진 곳 확인
                int nnx = nx + dx[i];
                int nny = ny + dy[i];

                if (isOutBounds(nnx, nny)) {
                    continue;
                }

                if (place[nnx][nny] == 'P') {
                    return false;
                }
            }
        }

        // 대각선 확인
        for (int i = 0; i < 4; i++) {
            int nx = x + ddx[i];
            int ny = y + ddy[i];

            if (isOutBounds(nx, ny)) {
                continue; // 범위를 벗어남
            }

            if (place[nx][ny] == 'P') {
                // 대각선에 P가 있는 경우, 그 사이에 O가 있는지 확인
                int nnx1 = x + dx[i];
                int nny1 = y + dy[i];
                int nnx2 = x + dx[(i + 1) % 4];
                int nny2 = y + dy[(i + 1) % 4];

                if (place[nnx1][nny1] != 'X' || place[nnx2][nny2] != 'X') {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isOutBounds(int x, int y) {
        return x < 0 || x >= MAX_ROW || y < 0 || y >= MAX_COL;
    }
}
