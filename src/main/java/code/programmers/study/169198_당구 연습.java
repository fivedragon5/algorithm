package code.programmers.study;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/169198
 *
 * 제한)
 *  3 ≤ m, n ≤ 1,000
 *  0 < startX < m
 *  0 < startY < n
 *  2 ≤ balls의 길이 ≤ 1,000
 *  balls의 원소는 [a, b] 형태입니다.
 *      a, b는 머쓱이가 맞춰야 할 공이 놓인 좌표를 의미합니다.
 *      0 < a < m, 0 < b < n
 *      (a, b) = ( startX, startY )인 입력은 들어오지 않습니다.
 *
 * 문제)
 *  1. m(가로) x n(세로) 크기의 직사각형 모양의 공간에 있다. | 좌측 하단 : 0,0
 *  2. 공이 startX, startY 좌표에 있다.
 *  3. 공을 벽에 최소한 1번 튕겨 다른 공을 맞춰야 한다.
 *  4. balls 배열에 있는 공들의 좌표를 맞추는 최소 거리의 제곱을 배열에 담아 반환
 *   - 단 친 공이 벽에 부딪힐 때 진행방향은 항상 입사각과 반사각이 동일
 *   - 만약 꼭지점에 부딪힐 경우, 진입 방향의 반대방향으로 공이 진행
 *   - 공이 목표에 맞기 전까지 멈추는 경우는 없다
 *   - 목표 공에 맞으면 바로 멈춘다고 가정
 *
 * 풀이)
 *  1. 공이 당구대 의 벽에 부딪히는 경우(상,하,좌,우)를 계산해서 4가지 경우의 수 중 최소값을 넣는다.
 *   - 가로 방향(좌,우)과 세로 방향(상,하)으로 각각 계산
 *   - 튕기는 지점을 기점으로 종이접기하듯이 대칭으로 계산해서 삼각형의 빗변(거리)의 길이를 구한다.
 *  2. 주의할점
 *   - x or y 좌표가 동일한 경우, 특정한 벽면에 쿠션을 못 하는 경우가 생김
 *    - 벽에 공을 부딪히기 전에 다른 공을 먼저 맞추면 안됨(이부분 예외 처리 추가 필요)
 *
 */
class Lesson169198 {
    public static void main(String[] args) {
        int m2 = 10; int n2 = 10; int startX2 = 5, startY2 = 9;
        int[][] balls2 = new int[][]{{5, 8}};
        System.out.println(solution(m2, n2, startX2, startY2, balls2));

        int m = 10; int n = 10; int startX = 3, startY = 7;
        int[][] balls = new int[][]{{7, 7}, {2, 7}, {7, 3}};
        System.out.println(solution(m, n, startX, startY, balls));
    }

    public static int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int ballsCount = balls.length;
        int[] answer = new int[ballsCount];
        for (int i = 0; i < ballsCount; i++) {
            int[] ball = balls[i];
            int x = ball[0], y = ball[1];
            int distanceCol = calculateDistanceByColumn(m, n, startX, startY, x, y);
            int distanceRow = calculateDistanceByRow(m, n, startX, startY, x, y);
            answer[i] = Math.min(distanceRow, distanceCol);
        }
        return answer;
    }

    private static int calculateDistanceByColumn(int m, int n, int startX, int startY, int x, int y) {
        int leftDistance = startX + x;
        int rightDistance = (m - startX) + (m - x);
        int distanceX = 0;
        if (leftDistance > rightDistance) {
            distanceX = rightDistance;
            if (startY == y && startX < x) {
                distanceX = leftDistance;
            }
        }
        else {
            distanceX = leftDistance;
            if (startY == y && startX > x) {
                distanceX = rightDistance;
            }
        }
        int distanceY = Math.abs(startY - y);
        return (distanceX * distanceX) + (distanceY * distanceY);
    }

    private static int calculateDistanceByRow(int m, int n, int startX, int startY, int x, int y) {
        int bottomDistance = startY + y;
        int topDistance = (n - startY) + (n - y);
        int distanceY = 0;
        if (bottomDistance > topDistance) {
            distanceY = topDistance;
            if (startX == x && startY < y) {
                distanceY = bottomDistance;
            }
        }
        else {
            distanceY = bottomDistance;
            if (startX == x && startY > y) {
                distanceY = topDistance;
            }
        }
        int distanceX = Math.abs(startX - x);
        return (distanceY * distanceY) + (distanceX * distanceX);
    }
}
