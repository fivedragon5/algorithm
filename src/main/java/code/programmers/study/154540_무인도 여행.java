package code.programmers.study;

import java.util.ArrayList;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/154540
 *
 * 제한)
 *  3 ≤ maps의 길이 ≤ 100
 *      3 ≤ maps[i]의 길이 ≤ 100
 *      maps[i]는 'X' 또는 1 과 9 사이의 자연수로 이루어진 문자열입니다.
 *      지도는 직사각형 형태입니다.
 *
 * 문제)
 *  1. 지도는 1x1 크기의 사각형들로 이루어진 직사각형 격자 형태이다.
 *  2. 각 칸은 X, 1 ~ 9 까지의 자연수가 적혀 있다.
 *  3. 이 때, 상하좌우 로 연결된 칸은 무인도로 연결 될 수 있다.
 *  4. 각 칸의 숫자는 식량을 나타낸다.
 *  5. 각각의 무인도들의 식량을 합한 값을 오름차순으로 정렬해서 반환하기
 *
 * 풀이)
 *  DFS
 *      1. String[] -> char[][] 로 변환
 *      2. DFS 재귀를 통해 하나의 무인도에 대해 식량 총 합 반환
 *       - 반환한 식량을 List에 add
 *      3. List에 값이 없을 경우 -1 반환
 *       - 값이 있을 경우 정렬한뒤 배열로 변환해서 반환
 *
 */
class Lesson154540 {
    public static void main(String[] args) {
        String[] maps = new String[]{"X591X","X1X5X","X231X", "1XXX1"};
        System.out.println(solution(maps));

        String[] maps2 = new String[]{"XXX","XXX","XXX"};
        System.out.println(solution(maps2));
    }

    private static int[] dx = new int[]{0,0,-1,1};
    private static int[] dy = new int[]{1,-1,0,0};
    private static int ROW;
    private static int COL;

    public static int[] solution(String[] maps) {
        ROW = maps.length;
        COL = maps[0].length();

        // 1. maps를 char[][] 로 변환
        char[][] foodMap = new char[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                foodMap[i][j] = maps[i].charAt(j);
            }
        }
        // 2. 각 식량칸에 대한 방문처리
        boolean[][] visited = new boolean[ROW][COL];

        List<Integer> foodAmounts = new ArrayList<>();

        // 3. DFS 처리
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (!visited[i][j] && isFood(i, j, foodMap)) {
                    foodAmounts.add(dfs(i,j,foodMap,visited));
                }
            }
        }
        if (foodAmounts.size() == 0) {
            return new int[]{-1};
        }
        return foodAmounts.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    private static int dfs(int r, int c, char[][] foodMap, boolean[][] visited) {
        int foodAmount = 0;
        visited[r][c] = true;
        // 식량일 경우
        if (isFood(r,c,foodMap)) {
            foodAmount += Character.getNumericValue(foodMap[r][c]);
            for (int i = 0; i < 4; i++) {
                int move_r = r + dx[i];
                int move_c = c + dy[i];
                if (move_r >= 0 && move_r < ROW && move_c >= 0 && move_c < COL && !visited[move_r][move_c]) {
                    foodAmount += dfs(move_r, move_c, foodMap, visited);
                }
            }
        }
        return foodAmount;
    }

    private static boolean isFood(int r, int c, char[][] foodMap) {
        return foodMap[r][c] != 'X';
    }
}
