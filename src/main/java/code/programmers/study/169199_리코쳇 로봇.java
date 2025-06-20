package code.programmers.study;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/169199
 *
 * 제한)
 *  3 ≤ board.length ≤ 100
 *      3 ≤ board[i] ≤ 100
 *      board의 원소의 길이는 모두 동일합니다.
 *      문자열은 ".", "D", "R", "G"로만 구성되어 있으며 각각 빈 공간, 장애물, 로봇의 처음 위치, 목표 지점을 나타냅니다.
 *      "R"과 "G"는 한 번씩 등장합니다.
 *
 * 문제)
 *  1. 게임판의 상태를 나타내는 문자열 배열 board가 주어짐
 *  2. 말이 목표위치에 도달하는데 최소 몇 번 이동해야 하는지 구하기
 *   -  말의 이동은 상,하,좌,우 방향으로 장애물 또는 벽에 부딪힐 때, 멈출 수 있다.
 *  3. 만약 목표 위치에 도달 할 수 없을 경우, -1 반환
 *   - "." : 빈 공간
 *   - "D" : 장애물
 *   - "R" : 시작 지점
 *   - "G" : 목표 위치
 *
 * 풀이)
 *  BFS : 최악의 경우가 있을 수 있어 BFS가 좀더 적합하다고 판단
 *      1. 주어진 board[]이 한칸씩 확인하기에 불편해서 char[][] map 으로 변환
 *       - 이 과정에서 로봇의 출발지점, 목적지점 을 저장해둠
 *      2. BFS 탐색에 필요한 방문 지점 제거 boolean[][] 추가
 *      3. BFS 탐색 진행
 *       - 시작지점을 Queue에 추가
 *       - queue에 있는 값을 poll() 하면서 상,하,좌,우 탐색 진행
 *       - 목적지에 도달하는 경우, 그 즉시 종료
 */
class Lesson169199 {
    public static void main(String[] args) {
        String[] board = new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        System.out.println(solution(board));

        String[] board2 = new String[]{".D.R", "....", ".G..", "...D"};
        System.out.println(solution(board2));
    }

    private static int ROW;
    private static int COL;

    public static int solution(String[] board) {
        ROW = board.length;
        COL = board[0].length();
        char[][] map = new char[ROW][COL];
        boolean[][] visited = new boolean[ROW][COL];
        Robot startRobot = null;
        int goal_r = 0; // 목적지 가로 값
        int goal_c = 0; // 목적이 세로 값
        for (int i = 0; i < ROW; i++) {
            String b = board[i];
            for (int j = 0; j < COL; j++) {
                char c = b.charAt(j);
                map[i][j] = c;
                if (c == 'R') {
                    startRobot = new Robot(i,j,0);
                } else if (c == 'G') {
                    goal_r = i;
                    goal_c = j;
                }
            }
        }

        Queue<Robot> queue = new LinkedList<>();
        visited[startRobot.r][startRobot.c] = true;
        queue.add(startRobot);

        while (!queue.isEmpty()) {
            Robot currentRobot = queue.poll();
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                Robot movedRobot = moveRobot(currentRobot, i, map);
                if (movedRobot.r == goal_r && movedRobot.c == goal_c) {
                    return movedRobot.moveCount;
                } else if (!visited[movedRobot.r][movedRobot.c]) {
                    visited[movedRobot.r][movedRobot.c] = true;
                    queue.add(movedRobot);
                }
            }
        }
        return -1;
    }

    private static Robot moveRobot(Robot robot, int direction, char[][] map) {
        int current_r = robot.r;
        int current_c = robot.c;
        switch (direction) {
            case 0: // 상
                for (int i = current_r; i > 0; i--) {
                    char movedMap = map[i-1][current_c];
                    if (movedMap == 'D') {
                        return new Robot(i, current_c, robot.moveCount + 1);
                    }
                }
                return new Robot(0, current_c, robot.moveCount + 1);
            case 1: // 하
                for (int i = current_r; i < ROW - 1; i++) {
                    char movedMap = map[i+1][current_c];
                    if (movedMap == 'D') {
                        return new Robot(i, current_c, robot.moveCount + 1);
                    }
                }
                return new Robot(ROW - 1, current_c, robot.moveCount + 1);
            case 2: // 좌
                for (int i = current_c; i > 0; i--) {
                    char movedMap = map[current_r][i-1];
                    if (movedMap == 'D') {
                        return new Robot(current_r, i, robot.moveCount + 1);
                    }
                }
                return new Robot(current_r, 0, robot.moveCount + 1);
            default: // 우
                for (int i = current_c; i < COL - 1; i++) {
                    char movedMap = map[current_r][i+1];
                    if (movedMap == 'D') {
                        return new Robot(current_r, i, robot.moveCount + 1);
                    }
                }
                return new Robot(current_r, COL - 1, robot.moveCount + 1);
        }
    }

    public static class Robot {
        int r;
        int c;
        int moveCount;

        Robot(int r, int c, int moveCount) {
            this.r = r;
            this.c = c;
            this.moveCount = moveCount;
        }
    }
}
