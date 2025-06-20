package code.programmers.study;

import java.util.ArrayList;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/86052
 *
 * 제한)
 *  1 ≤ grid의 길이 ≤ 500
 *      1 ≤ grid의 각 문자열의 길이 ≤ 500
 *      grid의 모든 문자열의 길이는 서로 같습니다.
 *      grid의 모든 문자열은 'L', 'R', 'S'로 이루어져 있습니다.
 *
 * 문제)
 *  1. 각 칸마다 'R', 'L', 'S' 중 하나가 적혀있는 격자가 있다.
 *   - S : 현재 위치에서 직진
 *   - L : 현재 위치에서 좌회전
 *   - R : 현재 위치에서 우회전
 *   - 빛이 격자의 끝을 넘어갈 경우, 반대쪽 끝으로 다시 돌아온다
 *  2. 이 격자 내에서 빛이 이동할 수 있는 격자 사이클에 몇 개 있고, 각 사이클의 길이를 구하기
 *
 * 풀이)
 *  DFS, 구현 ( 500 * 500 * 4 = 1,000,000 )
 *  1. 하나의 격자당 4방향(좌, 상, 우, 하)으로 이동할 수 있으므로, 각 격자에 대해 4방향을 모두 배열로 저장 gird[][][4]
 *  2. 각 격자에서 하나의 방향을 선택하여 이동하며 이동할때마다 방문여부 체크 및 count를 누적시켜준다.
 *  3. 이동중 이미 방문한 격자를 만나면 사이클이 형성된 것으로 판단하고 count를 결과 리스트에 추가한다.
 *  4. 모든 탐색이 끝나면 count 리스트를 오름차순으로 정렬하고, int[] 형태로 변환하여 반환한다.
 *
 */
class Lesson86052 {
    public static void main(String[] args) {
        String[] grid = new String[]{
                "SL", "LR"
        };
        System.out.println(solution(grid));

        String[] grid2 = new String[]{
                "S"
        };
        System.out.println(solution(grid2));

        String[] grid3 = new String[]{
                "R", "R"
        };
        System.out.println(solution(grid3));
    }

    private static final int[] dx = {-1, 0, 1, 0}; // 좌, 상, 우, 하
    private static final int[] dy = {0, 1, 0, -1}; // 좌, 상, 우, 하

    public static int[] solution(String[] grid) {
        int row = grid.length;
        int col = grid[0].length();
        char[][] gridArray = new char[row][col];
        boolean[][][] visited = new boolean[row][col][4]; // 4방향 (좌, 상, 우, 하)

        for (int i = 0; i < row; i++) {
            gridArray[i] = grid[i].toCharArray();
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[i][j][d]) {
                        int x = i, y = j, direction = d;
                        int count = 0;

                        while (true) {
                            if (visited[x][y][direction]) {
                                break;
                            }
                            visited[x][y][direction] = true;
                            count++;

                            direction = turn(direction, gridArray[x][y]);
                            x = wrap(x + dx[direction], row);
                            y = wrap(y + dy[direction], col);
                        }

                        if (count > 0) {
                            result.add(count);
                        }
                    }
                }
            }
        }
        result.sort(Integer::compareTo); // 오름차순 정렬
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int turn(int direction, char command) {
        if (command == 'L') {
            return (direction + 3) % 4; // 좌회전
        } else if (command == 'R') {
            return (direction + 1) % 4; // 우회전
        }
        return direction;
    }

    private static int wrap(int value, int limit) {
        if (value < 0) return limit - 1;
        else if (value >= limit) return 0;
        return value;
    }
}
